package vn.teko.todo.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import vn.teko.todo.services.Note
import java.time.LocalDateTime

internal class NoteDtoTest {

    @Test
    fun toNoteDto() {
        val note = Note(
            id = 1,
            title = "111",
            content = "note 1",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        val noteDto = note.toNoteDto()
        assertThat(noteDto.id).isEqualTo(note.id)
        assertThat(noteDto.title).isEqualTo(note.title)
        assertThat(noteDto.content).isEqualTo(note.content)
        assertThat(noteDto.color).isEqualTo(note.color)
        assertThat(noteDto.labels).isEqualTo(note.labels)
        assertThat(noteDto.createAt).isEqualTo(note.createAt)
        assertThat(noteDto.editedAt).isEqualTo(note.editedAt)
    }
}