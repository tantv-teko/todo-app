package vn.teko.todo.repositories

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class NoteColorRepositoryTest {

    @Autowired
    private lateinit var noteColorRepository: NoteColorRepository

    @Autowired
    private lateinit var noteRepository: NoteRepository

    @Autowired
    private lateinit var colorRepository: ColorRepository

    private var noteModel = NoteModel(
        id = 0,
        title = "aa",
        content = "aaaa",
        createAt = LocalDateTime.now(),
        editedAt = LocalDateTime.now(),
    )


    @Test
    fun findAll() {
        runBlocking {
            val note = noteRepository.save(noteModel)
            val noteColor1 = NoteColorModel(
                id = 0,
                noteId = note.id,
                colorId = 1,
            )
            noteColorRepository.save(noteColor1)
            val noteColors = noteColorRepository.findAll().toList()
            assertThat(noteColors.size).isNotNull()
        }
    }

    @Test
    fun findById() {
        runBlocking {
            val note = noteRepository.save(noteModel)
            var noteColor1 = NoteColorModel(
                id = 0,
                noteId = note.id,
                colorId = 2,
            )
            noteColor1 = noteColorRepository.save(noteColor1)
            val noteColor = noteColorRepository.findById(noteColor1.id)
            assertThat(noteColor).isEqualTo(noteColor1)
        }
    }

    @Test
    fun save() {
        runBlocking {
            val note = noteRepository.save(noteModel)
            var noteColor1 = NoteColorModel(
                id = 0,
                noteId = note.id,
                colorId = 1,
            )
            noteColor1 = noteColorRepository.save(noteColor1)
            val noteColor = noteColorRepository.findById(noteColor1.id)
            assertThat(noteColor).isEqualTo(noteColor1)
        }
    }

    @Test
    fun deleteById() {
        runBlocking {
            val note = noteRepository.save(noteModel)
            val noteColor1 = NoteColorModel(
                id = 0,
                noteId = note.id,
                colorId = 1,
            )
            val noteColor = noteColorRepository.save(noteColor1)
            noteColorRepository.deleteById(noteColor.id)
            assertThat(noteColorRepository.findById(noteColor.id)).isNull()
        }
    }

    @Test
    fun deleteByNoteId() {
        runBlocking {
            val note = noteRepository.save(noteModel)
            var noteColor1 = NoteColorModel(
                id = 0,
                noteId = note.id,
                colorId = 1,
            )
            noteColor1 = noteColorRepository.save(noteColor1)
            noteColorRepository.deleteByNoteId(noteColor1.noteId)
            val noteColor = noteColorRepository.findById(note.id)
            assertThat(noteColor).isNull()
        }
    }

}

