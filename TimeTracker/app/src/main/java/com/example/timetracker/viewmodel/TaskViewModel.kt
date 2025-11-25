package com.example.timetracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.timetracker.data.Task
import com.example.timetracker.data.TaskDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    val tasks: StateFlow<List<Task>> = taskDao.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun addTask(name: String) {
        if (name.isBlank()) {
            _errorMessage.value = "Task name cannot be empty"
            return
        }

        viewModelScope.launch {
            try {
                taskDao.insert(Task(name = name.trim()))
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to add task: ${e.message}"
            }
        }
    }

    fun startTimer(task: Task) {
        viewModelScope.launch {
            try {
                taskDao.update(
                    task.copy(
                        isRunning = true,
                        startTime = System.currentTimeMillis()
                    )
                )
            } catch (e: Exception) {
                _errorMessage.value = "Failed to start timer"
            }
        }
    }

    fun stopTimer(task: Task) {
        viewModelScope.launch {
            try {
                val elapsed = (System.currentTimeMillis() - task.startTime) / 1000
                taskDao.update(
                    task.copy(
                        isRunning = false,
                        totalSeconds = task.totalSeconds + elapsed,
                        startTime = 0
                    )
                )
            } catch (e: Exception) {
                _errorMessage.value = "Failed to stop timer"
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                taskDao.delete(task)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete task"
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}

class TaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}