package com.example.lazymulticounterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazymulticounterapp.ui.theme.LazyMultiCounterAppTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyMultiCounterAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MultiCounterScreen()
                }
            }
        }
    }
}

@Composable
fun MultiCounterScreen() {
    // Maintains a list of counters, each holding an Int count
    var counters by remember { mutableStateOf(List(5) { 0 }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Multi Counter List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // BUTTONS: Add or remove counters
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = { counters = counters + 0 }) {
                Text("Add Counter")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = {
                    if (counters.isNotEmpty()) counters = counters.dropLast(1)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text("Remove Counter")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn for scrollable counters
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(counters) { index, count ->
                CounterCard(
                    name = "Counter_${index + 1}",
                    countValue = count,
                    onIncrement = {
                        counters = counters.toMutableList().also { it[index] = it[index] + 1 }
                    },
                    onDecrement = {
                        counters = counters.toMutableList().also { it[index] = it[index] - 1 }
                    }
                )
            }
        }
    }
}

// Single counter item (reusable composable)
@Composable
fun CounterCard(
    name: String,
    countValue: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = onDecrement) {
                    Text(text = "-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "$countValue",
                    fontSize = 18.sp,
                    modifier = Modifier.width(40.dp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onIncrement) {
                    Text(text = "+")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    LazyMultiCounterAppTheme {
        MultiCounterScreen()
    }
}