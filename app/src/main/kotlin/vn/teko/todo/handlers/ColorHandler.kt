package vn.teko.todo.handlers


import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import vn.teko.todo.controllers.toColorDto
import vn.teko.todo.services.ColorService

@Component
class ColorHandler(
    private val colorService: ColorService
) {
    suspend fun getColors(
        req: ServerRequest,
    ): ServerResponse {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(colorService.getColors().map { it.toColorDto() })
    }

    suspend fun getColor(
        req: ServerRequest
    ): ServerResponse {
        val id = req.pathVariable("id").toLong()
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(colorService.getColor(id).toColorDto())
    }
}