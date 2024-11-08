package com.example.androidstudio_koala_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IconSelector(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun IconSelector(modifier: Modifier = Modifier) {
    var selectedIcon by remember { mutableStateOf("Add") }
    val iconOptions = listOf("Add", "Call", "Email")
    var sliderPosition by remember { mutableStateOf(3f) }
    var selectedIconResource by remember { mutableStateOf(R.drawable.ic_add) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Menú desplegable para seleccionar el ícono
        IconDropdownMenu(
            selectedItem = selectedIcon,
            items = iconOptions,
            onItemSelected = { icon ->
                selectedIcon = icon
                selectedIconResource = when (icon) {
                    "Add" -> R.drawable.ic_add
                    "Call" -> R.drawable.ic_call
                    "Email" -> R.drawable.ic_email
                    else -> R.drawable.ic_add
                }
            }
        )

        // Slider para ajustar el valor
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Min: 1, Max: 10")
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 1f..10f,
            steps = 9
        )
        Text(text = sliderPosition.toInt().toString())

        // Muestra el icono seleccionado
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(onClick = {}) {
            Image(
                painter = painterResource(id = selectedIconResource),
                contentDescription = selectedIcon,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
fun IconDropdownMenu(selectedItem: String, items: List<String>, onItemSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        TextButton(onClick = { expanded = true }) {
            Text(selectedItem)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    },
                    text = TODO(),
                    modifier = TODO(),
                    leadingIcon = TODO(),
                    trailingIcon = TODO(),
                    enabled = TODO(),
                    colors = TODO(),
                    contentPadding = TODO()
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IconSelectorPreview() {
    AndroidStudioKoalaTemplateTheme {
        IconSelector()
    }
}