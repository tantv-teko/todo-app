package vn.teko.todo.apis

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import vn.teko.todo.services.Label

class NoteApi(
    private val baseUrl: String
){
    private val webClient = WebClient.builder().baseUrl(baseUrl).build()

    suspend fun getLableDefaul() : Label {
        return webClient.get()
            .uri("")
            .retrieve()
            .bodyToMono(Label::class.java)
            .awaitSingle()
    }

}