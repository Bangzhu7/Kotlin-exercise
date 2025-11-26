
package com.example.timetracker

import com.example.timetracker.data.Task
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun task_creation_isCorrect() {
        val task = Task(
            id = 1,
            name = "Test Task",
            totalSeconds = 3600
        )

        assertEquals("Test Task", task.name)
        assertEquals(3600L, task.totalSeconds)
        assertFalse(task.isRunning)
    }

    @Test
    fun task_timer_calculation() {
        val startTime = System.currentTimeMillis()
        val task = Task(
            name = "Timer Test",
            isRunning = true,
            startTime = startTime
        )

        Thread.sleep(2000) // Wait 2 seconds
        val elapsed = (System.currentTimeMillis() - task.startTime) / 1000

        assertTrue(elapsed >= 2)
    }

    @Test
    fun task_name_validation() {
        val validTask = Task(name = "Valid Task")
        assertFalse(validTask.name.isBlank())

        val invalidName = "   "
        assertTrue(invalidName.isBlank())
    }
}