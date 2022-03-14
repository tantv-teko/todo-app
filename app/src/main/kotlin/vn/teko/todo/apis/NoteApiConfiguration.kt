package vn.teko.todo.apis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NoteApiConfiguration(
    private val config: NoteApiConfigurationProperties
) {
    @Bean
    fun noteApiConfigure() = NoteApi(config.baseUrl)
}

