package vn.teko.todo.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException) : ResponseEntity<ErrorDetails> {
        val error = ErrorDetails(
            httpStatus = exception.httpStatus,
            message = exception.message
        )
        return ResponseEntity(error, error.httpStatus)
    }
}