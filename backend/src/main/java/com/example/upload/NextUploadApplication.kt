package com.example.upload

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class NextUploadApplication

fun main(args: Array<String>) {
    SpringApplication.run(NextUploadApplication::class.java, *args)
}
