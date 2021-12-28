package vn.teko.todo.repositories

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import vn.teko.todo.services.Color

internal class ColorModelTest {

    @Test
    fun toColor() {
        val colorModel = ColorModel(
            id = 0,
            name = "test",
            code = "111",
        )
        val color = colorModel.toColor()
        assertThat(color.id).isEqualTo(colorModel.id)
        assertThat(color.name).isEqualTo(colorModel.name)
        assertThat(color.code).isEqualTo(colorModel.code)
        assertThat(color.isDefault).isEqualTo(colorModel.isDefault)
    }

    @Test
    fun toColorModel() {
        val color = Color(
            id = 0,
            name = "test",
            code = "111",
        )
        val colorModel = color.toColorModel()
        assertThat(color.id).isEqualTo(colorModel.id)
        assertThat(color.name).isEqualTo(colorModel.name)
        assertThat(color.code).isEqualTo(colorModel.code)
        assertThat(color.isDefault).isEqualTo(colorModel.isDefault)

    }

}