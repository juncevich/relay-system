package ru.relay.infrastructure.rest.exception.handler

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.ConstraintViolationException
import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ResponseBody
    @ExceptionHandler(value = [Exception::class])
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): ErrorDTO {
        logger.error(e) { "Unexpected error:" }
        return ErrorDTO(
            HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            "Unexpected error: ${e.message}"
        )
    }

    @ResponseBody
    @ExceptionHandler(value = [ValidationException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(exception: ValidationException): ErrorDTO {
        return if (exception is ConstraintViolationException) {
            val violations = extractViolationFromException(exception)
            logger.error(exception) { "Validation error: $violations" }
            ErrorDTO(
                HttpStatus.BAD_REQUEST.reasonPhrase,
                violations
            )
        } else {
            val exceptionMessage = exception.message ?: "Unknown error"
            logger.error(exception) { "Validation error: $exceptionMessage" }
            ErrorDTO(
                HttpStatus.BAD_REQUEST.reasonPhrase,
                exceptionMessage
            )
        }
    }

    fun extractViolationFromException(validationException: ConstraintViolationException): String {
        return validationException.constraintViolations.map { it.message }.joinToString { "--" }
    }

}