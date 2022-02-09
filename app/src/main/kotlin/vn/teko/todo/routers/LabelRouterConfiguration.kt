package vn.teko.todo.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter
import vn.teko.todo.handlers.LabelHandler

@Configuration
class LabelRouterConfiguration(
    private val labelHandler: LabelHandler,
) {

    @Bean
    fun labelRounter() = coRouter {
        "/api/labels".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("", labelHandler::getLabels)
                GET("/{id}", labelHandler::getLabel)
                POST("", labelHandler::createLabel)
                PUT("/{id}", labelHandler::updateLabel)
                DELETE("/{id}", labelHandler::deleteLabel)
            }
        }
    }

}