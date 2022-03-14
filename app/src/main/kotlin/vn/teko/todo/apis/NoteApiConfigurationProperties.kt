package vn.teko.todo.apis

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "api")
data class NoteApiConfigurationProperties(
    val baseUrl: String
)
