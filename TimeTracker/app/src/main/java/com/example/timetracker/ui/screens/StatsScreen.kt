package com.example.timetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.timetracker.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()

    val totalSeconds = tasks.sumOf { it.totalSeconds }
    val totalTasks = tasks.size
    val activeTasks = tasks.count { it.isRunning }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Statistics") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatCard(
                title = "Total Time Tracked",
                value = formatTime(totalSeconds),
                color = MaterialTheme.colorScheme.primary
            )

            StatCard(
                title = "Total Tasks",
                value = totalTasks.toString(),
                color = MaterialTheme.colorScheme.secondary
            )

            StatCard(
                title = "Active Tasks",
                value = activeTasks.toString(),
                color = MaterialTheme.colorScheme.tertiary
            )

            if (tasks.isNotEmpty()) {
                Divider(Modifier.padding(vertical = 8.dp))

                Text(
                    "Task Breakdown",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                tasks.forEach { task ->
                    TaskStatItem(
                        name = task.name,
                        time = formatTime(task.totalSeconds),
                        percentage = if (totalSeconds > 0)
                            (task.totalSeconds.toFloat() / totalSeconds * 100).toInt()
                        else 0
                    )
                }
            }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, color: androidx.compose.ui.graphics.Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = color
            )
        }
    }
}

@Composable
fun TaskStatItem(name: String, time: String, percentage: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
            LinearProgressIndicator(
                progress = percentage / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp),
            textAlign = TextAlign.End
        )
    }
}