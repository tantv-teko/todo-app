package vn.teko.todo.repositories

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension
import vn.teko.todo.services.Color
import java.time.LocalDateTime
import java.util.*
import javax.persistence.PersistenceContext

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class NoteRepositoryTest {

    @Autowired
    private lateinit var noteRepository: NoteRepository
    @Autowired
    private lateinit var entityManager: TestEntityManager
    private val color = Color(
        id = 1,
        name = "red",
        code = "111",
        isDefault = false,
    )

    @Test
    fun findAll() {
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
        entityManager.persist(note1)
        entityManager.persist(note2)
        entityManager.persist(note3)
        entityManager.persist(note4)
        val notes = noteRepository.findAll().map { it.toNote(color) }
        assertThat(notes.size).isNotNull()

    }

    @Test
    fun findById() {
        val note1 = NoteModel(
            id = 0,
            title = "sss",
            content = "conten1",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        entityManager.persist(note1)
        val note = noteRepository.findById(note1.id).get()
        assertThat(note).isEqualTo(note1)
    }

    @Test
    fun save() {
        val note1 = NoteModel(
            id = 0,
            title = "sss",
            content = "conten1",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        val note = noteRepository.save(note1)
        entityManager.flush()
        assertThat(note).isEqualTo(note1)
    }

    @Test
    fun deleteById() {
        val note1 = NoteModel(
            id = 0,
            title = "sss",
            content = "conten1",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        val note = noteRepository.save(note1)
        noteRepository.deleteById(note.id)
        entityManager.flush();
        assertThat(noteRepository.findById(note.id).isPresent).isEqualTo(false)
    }

}