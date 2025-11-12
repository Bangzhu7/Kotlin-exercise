package com.example.primenumbers

fun main() {
    // Create a list of integers from 1 to 100
    val numbers = (1..100).toList()

    // 1. Filter even numbers
    val evens = numbers.filter { it % 2 == 0 }

    // 2. Map to squares
    val squares = numbers.map { it * it }

    // 3. Reduce to find the sum of all numbers
    val sum = numbers.reduce { acc, n -> acc + n }

    // Print results
    println("Evens: $evens")
    println("Squares: $squares")
    println("Sum: $sum")
}
