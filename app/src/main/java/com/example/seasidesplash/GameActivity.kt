package com.example.seasidesplash

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.seasidesplash.ui.theme.SeasideSplashTheme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeasideSplashTheme {
                GameScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameScreen() {
    val gridSize = 5
    var grid by remember { mutableStateOf(createGrid(gridSize)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seaside Splash",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Se asegura de usar LazyVerticalGrid adecuadamente
            LazyVerticalGrid(
                columns = GridCells.Fixed(gridSize), // Esto es correcto, asegura que 'gridSize' sea un número entero
                modifier = Modifier.weight(1f)
            ) {
                // Aquí 'grid.flatten()' es la lista a iterar (usando un número entero, no una lista)
                items(grid.flatten()) { cell ->  // Usamos 'grid.flatten()' correctamente
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(cell.color)
                            .padding(4.dp)
                            .clickable {
                                grid = handleCellClick(cell, gridSize, grid)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cell.value.toString(),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun handleCellClick(
    clickedCell: GridCell,
    gridSize: Int,
    currentGrid: List<List<GridCell>>
): List<List<GridCell>> {
    val updatedGrid = checkMatchesAndClear(currentGrid, gridSize)
    Log.d("GameActivity", "Grid updated: $updatedGrid") // Log para verificar si se actualiza correctamente
    return updatedGrid
}

// Clase GridCell con un valor y color, asegurándonos que están correctamente configuradas
data class GridCell(val value: Int, val color: Color)

// Crear la cuadrícula inicial con valores aleatorios
fun createGrid(size: Int): List<List<GridCell>> {
    return List(size) {
        List(size) {
            GridCell((1..3).random(), randomColor()) // Generamos valor aleatorio entre 1 y 3
        }
    }
}

// Función para asignar colores aleatorios a las celdas
fun randomColor(): Color {
    return when ((1..4).random()) {
        1 -> Color.Blue
        2 -> Color.Green
        3 -> Color.Red
        4 -> Color.Yellow
        else -> Color.Gray
    }
}

// Función que verifica las coincidencias y elimina las celdas correspondientes
fun checkMatchesAndClear(grid: List<List<GridCell>>, size: Int): List<List<GridCell>> {
    val newGrid = grid.map { it.toMutableList() }.toMutableList()

    // Verificamos las filas
    for (row in 0 until size) {
        for (col in 0 until size - 2) {
            if (newGrid[row][col].value == newGrid[row][col + 1].value &&
                newGrid[row][col].value == newGrid[row][col + 2].value
            ) {
                newGrid[row][col] = GridCell(0, Color.Transparent)
                newGrid[row][col + 1] = GridCell(0, Color.Transparent)
                newGrid[row][col + 2] = GridCell(0, Color.Transparent)
            }
        }
    }

    // Verificamos las columnas
    for (col in 0 until size) {
        for (row in 0 until size - 2) {
            if (newGrid[row][col].value == newGrid[row + 1][col].value &&
                newGrid[row][col].value == newGrid[row + 2][col].value
            ) {
                newGrid[row][col] = GridCell(0, Color.Transparent)
                newGrid[row + 1][col] = GridCell(0, Color.Transparent)
                newGrid[row + 2][col] = GridCell(0, Color.Transparent)
            }
        }
    }

    // Dejar que las celdas "caigan" al eliminarse
    for (col in 0 until size) {
        val column = newGrid.map { it[col] }.filter { it.value != 0 }
        val emptyCells = size - column.size
        for (row in 0 until size) {
            newGrid[row][col] = if (row < emptyCells) {
                GridCell(0, Color.Transparent)
            } else {
                column[row - emptyCells]
            }
        }
    }

    return newGrid
}
