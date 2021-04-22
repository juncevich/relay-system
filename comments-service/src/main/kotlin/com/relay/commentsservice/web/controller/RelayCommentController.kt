package com.relay.commentsservice.web.controller

import com.relay.commentsservice.web.dto.RelayComment
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController
class RelayCommentController {


    fun createComment(comment: Any): Mono<RelayComment> {
        throw UnsupportedOperationException();
    }

    fun getComments(relayId: Long): Flux<RelayComment> {
        throw UnsupportedOperationException();
    }

    fun deleteComment(commentId: UUID): Void {
        throw UnsupportedOperationException();
    }

    fun updateComment(commentId: UUID): Void {
        throw UnsupportedOperationException();
    }
}