package com.example.primenumbers

open class Employee(protected val name: String, protected val baseSalary: Double) {
    open fun calculateSalary(): Double {
        return baseSalary
    }
}

class FullTimeEmployee(name: String, baseSalary: Double, private val bonus: Double) :
    Employee(name, baseSalary) {

    override fun calculateSalary(): Double {
        return baseSalary + bonus
    }
}

class PartTimeEmployee(name: String, baseSalary: Double, private val hourlyRate: Double, private val hoursWorked: Int) :
    Employee(name, baseSalary) {

    override fun calculateSalary(): Double {
        return baseSalary + (hourlyRate * hoursWorked)
    }
}

fun main() {
    val employees = mapOf(
        "Alice" to FullTimeEmployee("Alice", 3000.0, 500.0),
        "Bob" to PartTimeEmployee("Bob", 1000.0, 20.0, 60),
        "Charlie" to FullTimeEmployee("Charlie", 3500.0, 800.0),
        "Diana" to PartTimeEmployee("Diana", 800.0, 25.0, 40)
    )

    println("Employee Salaries:")
    for ((name, employee) in employees) {
        println("$name: ${employee.calculateSalary()}")
    }
}
