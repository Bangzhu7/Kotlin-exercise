// MainActivity.kt

package com.example.multicounterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multicounterapp.ui.theme.MultiCounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiCounterAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // This is our main screen layout function
                    MainScreen()
                }
            }
        }
    }
}

// Composable function for the main screen, which arranges our counters.
@Composable
fun MainScreen() {
    // Column arranges its children vertically.
    Column(
        modifier = Modifier.fillMaxSize(), // Take up the whole screen
        verticalArrangement = Arrangement.Center, // Center the whole block of counters vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center each counter horizontally
    ) {
        // We call our reusable Counter composable three times.
        // Each one will have its own independent state.
        Counter()
        Spacer(modifier = Modifier.height(24.dp)) // Add some space between counters
        Counter()
        Spacer(modifier = Modifier.height(24.dp))
        Counter()
    }
}

// This is our reusable Counter component.
@Composable
fun Counter() {
    // --- STATE MANAGEMENT ---
    // 'remember' tells Compose to keep this value even when the UI updates.
    // 'mutableStateOf' creates an observable state. When its value changes,
    // Compose will automatically "recompose" (redraw) any part of the UI that uses it.
    // We use a String because a TextField's value must be a String.
    var count by remember { mutableStateOf("0") }

    // Row arranges its children horizontally.
    Row(
        verticalAlignment = Alignment.CenterVertically // Align buttons and text field nicely
    ) {
        // 1. The DECREMENT (-) Button
        Button(onClick = {
            // Get the current number, subtract 1, and convert it back to a string.
            // toIntOrNull() is safe: if the text is not a number, it returns null and does nothing.
            val currentValue = count.toIntOrNull() ?: 0 // Default to 0 if text is invalid
            count = (currentValue - 1).toString()
        }) {
            Text("-")
        }

        Spacer(modifier = Modifier.width(16.dp))

        // 2. The Text Field for input and display
        TextField(
            value = count, // The text to display is our 'count' state variable.
            onValueChange = { newText ->
                // This is called every time the user types.
                // We update our 'count' state with the new text.
                count = newText
            },
            modifier = Modifier.width(100.dp), // Give it a fixed width
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center), // Center the text
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Show a number keyboard
        )

        Spacer(modifier = Modifier.width(16.dp))

        // 3. The INCREMENT (+) Button
        Button(onClick = {
            // Get the current number, add 1, and convert it back to a string.
            val currentValue = count.toIntOrNull() ?: 0
            count = (currentValue + 1).toString()
        }) {
            Text("+")
        }
    }
}

// The Preview function lets you see your design in Android Studio.
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MultiCounterAppTheme {
        MainScreen()
    }
}