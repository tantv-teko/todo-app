package vn.teko.todo.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter
import vn.teko.todo.handlers.ColorHandler

@Configuration
class ColorRouterConfiguration(
    private val colorHandler: ColorHandler,
) {
    @Bean
    fun colorRouter() = coRouter {
        "/api/colors".nest {
            accept(APPLICATION_JSON).nest {
                GET("", colorHandler::getColors)
                GET("/{id}", colorHandler::getColor)
            }
        }
    }
}