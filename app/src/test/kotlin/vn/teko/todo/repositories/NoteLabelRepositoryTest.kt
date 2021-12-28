package vn.teko.todo.repositories

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class NoteLabelRepositoryTest {

    @Autowired
    private lateinit var noteLabelRepository: NoteLabelRepository
    @Autowired
    private lateinit var labelRepository: LabelRepository
    @Autowired
    private lateinit var noteRepository: NoteRepository
    @Autowired
    private lateinit var entityManager: TestEntityManager

    private val noteModel = NoteModel(
        id = 0,
        title = "aa",
        content = "aaaa",
        createAt = LocalDateTime.now(),
        editedAt = LocalDateTime.now(),
    )

    @Test
    fun findAll() {
        val note = noteRepository.save(noteModel)
        val noteLabel1 = NoteLabelModel(
            id = 0,
            noteId = note.id,
            labelId = 1,
        )
        val noteLabel2 = NoteLabelModel(
            id = 0,
            noteId = note.id,
            labelId = 2,
        )
        noteLabelRepository.save(noteLabel1)
        noteLabelRepository.save(noteLabel2)
        val noteLabels = noteLabelRepository.findAll() as List<NoteLabelModel>
        assertThat(noteLabels.size).isNotNull()
    }

    @Test
    fun findById() {
        val note = noteRepository.save(noteModel)
        val noteLabel1 = NoteLabelModel(
            id = 0,
            noteId = note.id,
            labelId = 2,
        )
        entityManager.persist(noteLabel1)
        val noteLabel = noteLabelRepository.findById(noteLabel1.id).get()
        assertThat(noteLabel).isEqualTo(noteLabel1)
    }

    @Test
    fun save() {
        val note = noteRepository.save(noteModel)
        val noteLabel1 = NoteLabelModel(
            id = 0,
            noteId = note.id,
            labelId = 1,
        )
        val noteLabel = noteLabelRepository.save(noteLabel1)
        assertThat(noteLabel).isEqualTo(noteLabel1)
    }

    @Test
    fun deleteById() {
        val note = noteRepository.save(noteModel)
        val noteLabel1 = NoteLabelModel(
            id = 0,
            noteId = note.id,
            labelId = 1,
        )
        val noteLabel = noteLabelRepository.save(noteLabel1)
        noteLabelRepository.deleteById(noteLabel.id)
        assertThat(noteLabelRepository.findById(noteLabel.id).isPresent).isEqualTo(false)
    }

    @Test
    fun deleteNoteLabelModelByNoteId() {
        val note = noteRepository.save(noteModel)
        val noteLabel1 = NoteLabelModel(
            id = 0,
            noteId = note.id,
            labelId = 1,
        )
        val noteLabel2 = NoteLabelModel(
            id = 0,
            noteId = note.id,
            labelId = 2,
        )
        noteLabelRepository.save(noteLabel1)
        noteLabelRepository.save(noteLabel2)
        noteLabelRepository.deleteNoteLabelModelByNoteId(note.id)
        val Labels = labelRepository.findByNoteId(note.id)
        assertThat(Labels.size).isEqualTo(0)
    }


}