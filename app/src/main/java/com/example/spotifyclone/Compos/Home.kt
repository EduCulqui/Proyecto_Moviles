package com.example.spotifyclone.Compos

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


/* ---------------- HOME ---------------- */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val featuredPlaylists = listOf(
        "Top Perú" to "https://i.imgur.com/gkScsMz.jpeg",
        "Descubrimiento Semanal" to "https://i.imgur.com/gkScsMz.jpeg",
        "Éxitos Globales" to "https://i.imgur.com/gkScsMz.jpeg",
        "Lo mejor del Rock" to "https://i.imgur.com/gkScsMz.jpeg",
        "Novedades Latinas" to "https://i.imgur.com/gkScsMz.jpeg"
    )

    val recommendedPlaylists = listOf(
        "DJ mix" to "https://i.imgur.com/gkScsMz.jpeg",
        "Baladas" to "https://i.imgur.com/gkScsMz.jpeg",
        "Reggaeton 2023" to "https://i.imgur.com/gkScsMz.jpeg",
        "Pop" to "https://i.imgur.com/gkScsMz.jpeg",
        "Cumbia" to "https://i.imgur.com/gkScsMz.jpeg"
    )

    val morePlaylists = listOf(
        "Clásicos" to "https://i.imgur.com/gkScsMz.jpeg",
        "Salsa" to "https://i.imgur.com/gkScsMz.jpeg",
        "Pachanga" to "https://i.imgur.com/gkScsMz.jpeg",
        "Perreo" to "https://i.imgur.com/gkScsMz.jpeg",
        "Rap" to "https://i.imgur.com/gkScsMz.jpeg"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inicio") },
                navigationIcon = {
                    IconButton(onClick = {
                        Log.d("HomeScreen", "Configuración presionada")
                    }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Log.d("HomeScreen", "Perfil presionado")
                    }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Perfil")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Section(title = "Listas destacadas", playlists = featuredPlaylists)
            }
            item {
                Section(title = "Recomendados para ti", playlists = recommendedPlaylists)
            }
            item {
                Section(title = "Lo que escuchas seguido", playlists = morePlaylists)
            }
        }
    }
}

/* ---------------- SECCIÓN REUTILIZABLE ---------------- */
@Composable
fun Section(title: String, playlists: List<Pair<String, String>>) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(playlists) { (name, url) ->
                PlaylistCard(name = name, imageUrl = url)
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

