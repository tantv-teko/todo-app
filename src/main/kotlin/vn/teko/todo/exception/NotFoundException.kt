package vn.teko.todo.exception

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

class NotFoundException(
    val httpStatus: HttpStatus = HttpStatus.NOT_FOUND,
    message: String?,
) : Throwable(message)
