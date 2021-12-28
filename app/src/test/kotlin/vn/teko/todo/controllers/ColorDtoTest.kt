package vn.teko.todo.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import vn.teko.todo.services.Color

internal class ColorDtoTest {

    @Test
    fun toColorDto() {
        val color = Color(
            id = 0,
            name = "test",
            code = "111",
            isDefault = false,
        )
        val colorDto = color.toColorDto()
        assertThat(colorDto.id).isEqualTo(color.id)
        assertThat(colorDto.name).isEqualTo(color.name)
        assertThat(colorDto.code).isEqualTo(color.code)
        assertThat(colorDto.isDefault).isEqualTo(color.isDefault)
    }
}