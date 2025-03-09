package com.example.seasidesplash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.seasidesplash.ui.theme.SeasideSplashTheme

// Asegurando que MainActivity sea de tipo ComponentActivity para usar Compose
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Usando setContent con el theme y pantalla principal
        setContent {
            SeasideSplashTheme {
                HomeScreen(onStart = { navigateToGame() })
            }
        }
    }

    // Función para navegar a la actividad del juego
    private fun navigateToGame() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
}

// Composable que construye la pantalla principal con un botón de inicio
@Composable
fun HomeScreen(onStart: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Título principal de la aplicación
                Text(
                    text = "Seaside Splash",
                    style = MaterialTheme.typography.displayMedium
                )
                // Botón que al hacer clic, inicia el juego
                Button(onClick = { onStart() }) {
                    Text(text = "Start Game")
                }
            }
        }
    }
}
