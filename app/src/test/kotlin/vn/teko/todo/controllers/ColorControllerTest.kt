package vn.teko.todo.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import vn.teko.todo.services.Color
import vn.teko.todo.services.ColorService


@ExtendWith(SpringExtension::class)
@WebFluxTest(ColorController::class)
internal class ColorControllerTest {

    @Autowired
    private lateinit var webClient: WebTestClient
    @MockBean
    private lateinit var colorService: ColorService
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    suspend fun getColors() {
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
        given(colorService.getColors()).willReturn(colors)
        webClient.get().uri("api/colors")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody().equals(colorsJSON)

        /*
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(colorsJSON))
            .andReturn()

             */

    }

}