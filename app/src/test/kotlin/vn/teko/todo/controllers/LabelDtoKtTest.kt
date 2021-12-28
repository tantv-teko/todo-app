package vn.teko.todo.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import vn.teko.todo.services.Label

internal class LabelDtoTest {

    @Test
    fun toLabelDto() {
        val label = Label(
            id = 0,
            name = "test"
        )
        val labelDto = label.toLabelDto()
        assertThat(labelDto.id).isEqualTo(label.id)
        assertThat(labelDto.name).isEqualTo(label.name)
    }
}