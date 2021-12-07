package vn.teko.todo.resquest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import vn.teko.todo.services.Color

internal class AddNoteRequestTest {

    @Test
    fun toNote() {
        val addNoteRequest = AddNoteRequest(
            title = "test",
            content = "aaaaaa",
            colorId = 2,
        )
        val color1 = Color(
            id = 2,
            name = "11",
            code = "11",
        )
        val note = addNoteRequest.toNote()
        assertThat(note.title).isEqualTo(addNoteRequest.title)
        assertThat(note.content).isEqualTo(addNoteRequest.content)
        assertThat(note.color).isEqualTo(color1)
        assertThat(note.createAt).isEqualTo(addNoteRequest.createAt)
        val addNoteRequest2 = AddNoteRequest(
            title = "test",
            content = "aaaaaa",
            colorId = null,
        )
        val color2 = Color(
            id = 3,
            name = "222",
            code = "11",
        )
        val note2 = addNoteRequest2.toNote()
        assertThat(note2.title).isEqualTo(addNoteRequest2.title)
        assertThat(note2.content).isEqualTo(addNoteRequest2.content)
        assertThat(note2.color).isEqualTo(color2)
        assertThat(note2.createAt).isEqualTo(addNoteRequest2.createAt)
    }
}