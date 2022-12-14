package com.challenge.restservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class RestServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(RestServiceApplication::class.java, *args)
}
