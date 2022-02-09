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
import vn.teko.todo.services.Color
import java.time.LocalDateTime
import java.util.*

@ExtendWith(SpringExtension::class)
@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class NoteRepositoryTest {

    @Autowired
    private lateinit var noteRepository: NoteRepository

    private val color = Color(
        id = 1,
        name = "red",
        code = "111",
        isDefault = false,
    )

    @Test
    fun findAll() {
        runBlocking {
            val note1 = NoteModel(
                id = 0,
                title = "title1",
                content = "conten1",
                createAt = LocalDateTime.now(),
                editedAt = LocalDateTime.now(),
            )
            val note2 = NoteModel(
                id = 0,
                title = "title2",
                content = "conten1",
                createAt = LocalDateTime.now(),
                editedAt = LocalDateTime.now(),
            )
            val note3 = NoteModel(
                id = 0,
                title = "title3",
                content = "conten1",
                createAt = LocalDateTime.now(),
                editedAt = LocalDateTime.now(),
            )
            val note4 = NoteModel(
                id = 0,
                title = "title4",
                content = "conten1",
                createAt = LocalDateTime.now(),
                editedAt = LocalDateTime.now(),
            )
            noteRepository.save(note1)
            noteRepository.save(note2)
            noteRepository.save(note3)
            noteRepository.save(note4)
            val notes = noteRepository.findAll().toList()
            assertThat(notes.size).isNotNull()
        }
    }

    @Test
    fun findById() {

        runBlocking {
            var note1 = NoteModel(
                id = 0,
                title = "sss",
                content = "conten1",
                createAt = LocalDateTime.now(),
                editedAt = LocalDateTime.now(),
            )
            note1 = noteRepository.save(note1)
            val note = noteRepository.findById(note1.id)
            assertThat(note?.id).isEqualTo(note1.id)
            assertThat(note?.title).isEqualTo(note1.title)
            assertThat(note?.content).isEqualTo(note1.content)
        }
    }

    @Test
    fun save() {
        runBlocking {
            var note1 = NoteModel(
                id = 0,
                title = "sss",
                content = "conten1",
                createAt = LocalDateTime.now(),
                editedAt = LocalDateTime.now(),
            )
            note1 = noteRepository.save(note1)
            val note = noteRepository.findById(note1.id)
            assertThat(note?.id).isEqualTo(note1.id)
            assertThat(note?.title).isEqualTo(note1.title)
            assertThat(note?.content).isEqualTo(note1.content)        }
    }

    @Test
    fun deleteById() {
        runBlocking {
            val note1 = NoteModel(
                id = 0,
                title = "sss",
                content = "conten1",
                createAt = LocalDateTime.now(),
                editedAt = LocalDateTime.now(),
            )
            val note = noteRepository.save(note1)
            noteRepository.deleteById(note.id)
            assertThat(noteRepository.findById(note.id)).isNull()
        }
    }

}
