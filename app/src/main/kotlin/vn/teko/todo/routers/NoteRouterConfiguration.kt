package vn.teko.todo.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter
import vn.teko.todo.handlers.NoteHandler

@Configuration
class NoteRouterConfiguration(
    private val noteHandler: NoteHandler,
) {
    @Bean
    fun noteRounter() = coRouter {
        "/api/notes".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("", noteHandler::getNotes)
                GET("/{id}", noteHandler::getNote)
                POST("", noteHandler::createNote)
                PUT("/{id}", noteHandler::updateNote)
                DELETE("/{id}", noteHandler::deleteNote)
            }
        }
    }
}