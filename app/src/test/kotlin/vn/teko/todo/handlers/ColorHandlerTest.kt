package vn.teko.todo.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import vn.teko.todo.controllers.toColorDto
import vn.teko.todo.routers.ColorRouterConfiguration
import vn.teko.todo.services.Color
import vn.teko.todo.services.ColorService

@ExtendWith(SpringExtension::class)
@WebFluxTest(ColorHandler::class, ColorRouterConfiguration::class)
internal class ColorHandlerTest {

    @Autowired
    private lateinit var client: WebTestClient
    @MockBean
    private lateinit var colorService: ColorService
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun getColors() {
        val color1 = Color(
            id = 1,
            name = "red",
            code = "111",
            isDefault = false
        )
        val color2 = Color(
            id = 2,
            name = "orange",
            code = "222",
            isDefault = false
        )
        val color3 = Color(
            id = 3,
            name = "yellow",
            code = "333",
            isDefault = true
        )
        val color4 = Color(
            id = 4,
            name = "blue",
            code = "444",
            isDefault = false
        )
        val color5 = Color(
            id = 5,
            name = "green",
            code = "333",
            isDefault = false
        )
        val colors = listOf<Color>(color1, color2, color3, color4, color5)
        val colorsJSON = objectMapper.writeValueAsString(colors.map { it.toColorDto() })
        runBlocking {
            given(colorService.getColors()).willReturn(colors)
            client.get().uri("/api/colors")
                .exchange()
                .expectStatus().isOk
                .expectBody().equals(colorsJSON)
        }

    }
}