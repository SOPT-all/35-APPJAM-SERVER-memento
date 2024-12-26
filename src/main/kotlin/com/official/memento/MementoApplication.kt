package com.official.memento

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MementoApplication

fun main(args: Array<String>) {
    runApplication<MementoApplication>(*args)
}
