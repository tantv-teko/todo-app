package vn.teko.todo.services

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import vn.teko.todo.repositories.ColorModel
import vn.teko.todo.repositories.ColorRepository
import vn.teko.todo.repositories.toColor
import java.util.*


@ExtendWith(SpringExtension::class)
internal class ColorServiceTest {

    lateinit var colorService: ColorService

    @Mock
    private lateinit var colorRepository: ColorRepository

    @BeforeEach
    fun setup() {
        colorService = ColorServiceImpl(colorRepository)
        val color1 = ColorModel(
            id = 1,
            name = "red",
            code = "111",
            isDefault = false
        )
        val color2 = ColorModel(
            id = 2,
            name = "orange",
            code = "222",
            isDefault = false
        )
        val color3 = ColorModel(
            id = 3,
            name = "yellow",
            code = "333",
            isDefault = true
        )
        val colorModels = listOf<ColorModel>(color1, color2, color3).asFlow()
        runBlocking {
            given(colorRepository.findAll()).willReturn(colorModels)
            given(colorRepository.findById(1)).willReturn(color1)
            given(colorRepository.findById(2)).willReturn(color2)
            given(colorRepository.findById(3)).willReturn(color3)
        }
    }


    @Test
    fun getColors() {
        runBlocking {
            val colors = colorService.getColors()
            assertThat(colors).isEqualTo(colorRepository.findAll().toList().map { it.toColor() })

        }
    }

    @Test
    fun getColor() {
        runBlocking {
            var color = colorService.getColor(1)
            assertThat(color.id).isEqualTo(1)
            assertThat(color.name).isEqualTo("red")
            assertThat(colorService.getColor(3).code).isEqualTo("333")
        }
    }

}