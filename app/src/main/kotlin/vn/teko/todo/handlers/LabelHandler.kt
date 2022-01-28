package vn.teko.todo.handlers

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import vn.teko.todo.controllers.toLabelDto
import vn.teko.todo.resquest.AddLabelRequest
import vn.teko.todo.resquest.UpdateLabelRequest
import vn.teko.todo.resquest.toLabel
import vn.teko.todo.services.LabelService

@Component
class LabelHandler(
    private val labelService: LabelService,
) {

    suspend fun getLabels(
        req: ServerRequest,
    ): ServerResponse {
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(labelService.getLabels().map { it.toLabelDto() })
    }

    suspend fun getLabel(
        req: ServerRequest,
    ): ServerResponse {
        val id = req.pathVariable("id").toLong()
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(labelService.getLabel(id).toLabelDto())
    }

    suspend fun createLabel(
        req: ServerRequest,
    ): ServerResponse{
        val request = req.awaitBody(AddLabelRequest::class)
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(labelService.createLabel(request.toLabel()).toLabelDto())
    }

    suspend fun updateLabel(
        req: ServerRequest,
    ): ServerResponse{
        val id = req.pathVariable("id").toLong()
        val request = req.awaitBody(UpdateLabelRequest::class)
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(labelService.updateLabel(id, request.toLabel()).toLabelDto())
    }

    suspend fun deleteLabel(
        req: ServerRequest,
    ): ServerResponse {
        val id = req.pathVariable("id").toLong()
        return ServerResponse.ok()
            .json()
            .bodyValueAndAwait(labelService.deleteLabel(id).toLabelDto())
     }

}