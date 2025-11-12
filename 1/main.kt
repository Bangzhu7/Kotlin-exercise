package com.example.primenumbers
fun main() {
    val primes = mutableListOf<Int>()
    var num = 2
    while (primes.size < 50) {
        if (isPrime(num)) primes.add(num)
        num++
    }

    println("First 50 prime numbers:")
    println(primes)

    val evens = (2..100 step 2).take(50).toList()
    println("\nFirst 50 even numbers:")
    println(evens)

    val combined = primes + evens
    println("\nCombined list:")
    println(combined)
}

fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}
