package vn.teko.todo.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter
import vn.teko.todo.handlers.ColorHandler
import vn.teko.todo.handlers.LabelHandler
import vn.teko.todo.handlers.NoteHandler

@Configuration
class routers(
    private val colorHandler: ColorHandler,
    private val labelHandler: LabelHandler,
    private val noteHandler: NoteHandler,
) {
    @Bean
    fun colorRouter() = coRouter {
        "/api/Colors".nest {
            accept(APPLICATION_JSON).nest {
                GET("", colorHandler::getColors)
                GET("/{id}", colorHandler::getColor)
            }
        }
    }

    @Bean
    fun labelRounter() = coRouter {
        "/api/labels".nest {
            accept(APPLICATION_JSON).nest {
                GET("", labelHandler::getLabels)
                GET("/{id}", labelHandler::getLabel)
                POST("", labelHandler::createLabel)
                PUT("/{id}", labelHandler::updateLabel)
                DELETE("/{id}", labelHandler::deleteLabel)
            }
        }
    }

    @Bean
    fun noteRounter() = coRouter {
        "/api/notes".nest {
            accept(APPLICATION_JSON).nest {
                GET("", noteHandler::getNotes)
                GET("/{id}", noteHandler::getNote)
                POST("", noteHandler::createNote)
                PUT("/{id}", noteHandler::updateNote)
                DELETE("/{id}", noteHandler::deleteNote)
            }
        }
    }

}