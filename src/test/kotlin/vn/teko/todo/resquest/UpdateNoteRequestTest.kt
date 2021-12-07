package vn.teko.todo.resquest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import vn.teko.todo.services.Color

internal class UpdateNoteRequestTest {

    @Test
    fun toNote() {
        val updateLabelRequest = UpdateNoteRequest(
            id = 0,
            title = "test",
            content = "aaaaaa",
            colorId = 2,
        )
        val color = Color(
            id = updateLabelRequest.colorId,
            name = "11",
            code = "1111",
        )
        val note = updateLabelRequest.toNote()
        assertThat(note.id).isEqualTo(updateLabelRequest.id)
        assertThat(note.title).isEqualTo(updateLabelRequest.title)
        assertThat(note.content).isEqualTo(updateLabelRequest.content)
        assertThat(note.color).isEqualTo(color)
        assertThat(note.createAt).isEqualTo(updateLabelRequest.createAt)
        assertThat(note.editedAt).isEqualTo(updateLabelRequest.editedAt)

    }
}