package io.home.expensestracker.web.controller

import io.home.expensestracker.web.response.Message
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class ControllerAdvice: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(EntityNotFoundException::class)])
    fun handleNotFound(exception: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(exception, Message(exception.message?:"Entity not found"),
                HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}