package com.example.todoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Preview(showBackground = true)
@Composable
fun TasksScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        AddTaskDialog(show = true, onDissmiss = { }, onTaskAdded = {

        })
        FabDialog(modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
fun FabDialog(modifier: Modifier) {
    FloatingActionButton(
        onClick = {
            // Mostrar dialogo
        },
        modifier = modifier.padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun AddTaskDialog(show: Boolean, onDissmiss: () -> Unit, onTaskAdded: ( String ) -> Unit ) {
    var myTask by remember { mutableStateOf("") }
    if (show) {
        Dialog(
            onDismissRequest = { onDissmiss() }) {
            Column(Modifier.fillMaxWidth().background(Color.White).padding(16.dp)) {
                Text(
                    text = "Añade tu tarea",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                    )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onTaskAdded( myTask )
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Añadir Tarea")
                }
            }
        }
    }
}