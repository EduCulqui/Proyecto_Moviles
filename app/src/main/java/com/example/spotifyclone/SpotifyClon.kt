package com.example.spotifyclone
import coil.compose.AsyncImage
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.PasswordVisualTransformation
import android.util.Log
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.graphics.Color

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
class SpotifyClon : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SpotifyCloneApp()
            }
        }
    }
}

private enum class Screen { Login, Register, Home }

@Composable
private fun SpotifyCloneApp() {
    var current by rememberSaveable { mutableStateOf(Screen.Login) }

    when (current) {
        Screen.Login -> LoginScreen(
            onLogin = { current = Screen.Home },
            onGoRegister = { current = Screen.Register }
        )
        Screen.Register -> RegisterScreen(
            onRegistered = { current = Screen.Login }
        )
        Screen.Home -> HomeScreen()
    }
}



/* ---------------- LOGIN ---------------- */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreen(
    onLogin: () -> Unit,
    onGoRegister: () -> Unit
) {
    val ctx = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Iniciar sesión") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    // 👉 Aquí verificamos que sí se llama
                    Toast.makeText(ctx, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                    Log.d("LoginScreen", "Login con email: $email")
                    onLogin() // esto cambia la pantalla a Home
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Iniciar sesión")
            }

            Spacer(Modifier.height(12.dp))

            TextButton(
                onClick = onGoRegister,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("¿No tienes cuenta? Crear cuenta")
            }
        }
    }
}


/* ---------------- REGISTRO ---------------- */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegistered: () -> Unit) {
    val ctx = LocalContext.current

    // Campos de entrada
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Crear cuenta") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Campo Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            // Campo Apellido
            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            // Campo Teléfono (opcional)
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono (opcional)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            // Campo Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            // Campo Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(20.dp))

            // Botón de registro
            Button(
                onClick = {
                    // 🔹 Validación básica
                    if (nombre.isBlank() || apellido.isBlank() || email.isBlank() || password.isBlank()) {
                        Toast.makeText(ctx, "Completa todos los campos obligatorios", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(ctx, "Usuario registrado: $nombre $apellido", Toast.LENGTH_SHORT).show()
                        Log.d("RegisterScreen", "Registrado -> Nombre: $nombre, Apellido: $apellido, Teléfono: $telefono, Email: $email, Password: $password")

                        // 🔹 Regresar al login
                        onRegistered()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Registrar")
            }
        }
    }
}
/* ---------------- HOME ---------------- */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val featuredPlaylists = listOf(
        "Top Perú" to "https://i.imgur.com/gkScsMz.jpeg",
        "Descubrimiento Semanal" to "URL",
        "Éxitos Globales" to "URL",
        "Lo mejor del Rock" to "URL ",
        "Novedades Latinas" to "URL"
    )

    val recommendedPlaylists = listOf(
        "DJ mix" to "URL",
        "Baladas" to "URL",
        "Reggaeton 2023" to "URL",
        "Pop " to "URL",
        "Cumbia" to "URL"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // 👈 Fondo blanco fijo
            .padding(16.dp)
    ) {
        Text(
            text = "Listas destacadas",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(featuredPlaylists) { (title, url) ->
                PlaylistCard(name = title, imageUrl = url)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Recomendados para ti",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(recommendedPlaylists) { (title, url) ->
                PlaylistCard(name = title, imageUrl = url)
            }
        }
    }
}

@Composable
fun PlaylistCard(name: String, imageUrl: String) {
    Card(
        modifier = Modifier
            .size(160.dp)
            .clip(RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

