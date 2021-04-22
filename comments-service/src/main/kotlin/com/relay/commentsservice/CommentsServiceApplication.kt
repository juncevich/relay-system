package com.relay.commentsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommentsServiceApplication

fun main(args: Array<String>) {
    runApplication<CommentsServiceApplication>(*args)
}
