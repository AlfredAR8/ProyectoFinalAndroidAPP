// CreateEventScreen.kt
package com.example.eventapp.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eventapp.viewmodel.EventViewModel
import com.example.eventapp.data.PreferencesManager
import androidx.compose.ui.platform.LocalContext
import com.example.eventapp.network.EventCreateRequest

@Composable
fun CreateEventScreen(navController: NavController) {
    val eventViewModel: EventViewModel = viewModel()
    val eventActionResponse by eventViewModel.eventActionResponse.collectAsState()
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val token = preferencesManager.getToken() ?: ""

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    // Manejar posibles mensajes de error de validación
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Crear Evento", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Fecha (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Hora (HH:MM)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Ubicación") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = image,
            onValueChange = { image = it },
            label = { Text("URL de la Imagen (Opcional)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Validaciones básicas antes de crear el evento
                if (title.isBlank() || description.isBlank() || date.isBlank() || time.isBlank() || location.isBlank()) {
                    errorMessage = "Por favor, completa todos los campos obligatorios."
                    return@Button
                }
                eventViewModel.createEvent(
                    token = token,
                    eventRequest = EventCreateRequest(
                        title = title,
                        description = description,
                        date = date,
                        time = time,
                        location = location,
                        image = if (image.isEmpty()) null else image
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Evento")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje de éxito o error para crear/actualizar eventos
        eventActionResponse?.let { response ->
            if (response.isSuccessful) {
                Text(
                    text = "Evento creado exitosamente",
                    color = MaterialTheme.colorScheme.primary
                )
                // Navegar de vuelta a la lista de eventos
                LaunchedEffect(Unit) {
                    navController.navigate("eventList") {
                        popUpTo("createEvent") { inclusive = true }
                    }
                }
            } else {
                Text(
                    text = "Error al crear evento: ${response.message()}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        // Mostrar mensaje de error de validación
        errorMessage?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}