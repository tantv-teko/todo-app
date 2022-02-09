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
internal class NoteLabelRepositoryTest {

    @Autowired
    private lateinit var noteLabelRepository: NoteLabelRepository
    @Autowired
    private lateinit var labelRepository: LabelRepository
    @Autowired
    private lateinit var noteRepository: NoteRepository

    private val noteModel = NoteModel(
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
            val noteLabels = noteLabelRepository.findAll().toList()
            assertThat(noteLabels.size).isNotNull()
        }
    }

    @Test
    fun findById() {
        runBlocking {
            val note = noteRepository.save(noteModel)
            var noteLabel1 = NoteLabelModel(
                id = 0,
                noteId = note.id,
                labelId = 2,
            )
            noteLabel1 = noteLabelRepository.save(noteLabel1)
            val noteLabel = noteLabelRepository.findById(noteLabel1.id)
            assertThat(noteLabel).isEqualTo(noteLabel1)
        }
    }

    @Test
    fun save() {
        runBlocking {
            val note = noteRepository.save(noteModel)
            var noteLabel1 = NoteLabelModel(
                id = 0,
                noteId = note.id,
                labelId = 1,
            )
            noteLabel1 = noteLabelRepository.save(noteLabel1)
            val noteLabel = noteLabelRepository.save(noteLabel1)
            assertThat(noteLabel).isEqualTo(noteLabel1)
        }
    }

    @Test
    fun deleteById() {
        runBlocking {
            val note = noteRepository.save(noteModel)
            val noteLabel1 = NoteLabelModel(
                id = 0,
                noteId = note.id,
                labelId = 1,
            )
            val noteLabel = noteLabelRepository.save(noteLabel1)
            noteLabelRepository.deleteById(noteLabel.id)
            assertThat(noteLabelRepository.findById(noteLabel.id)).isNull()
        }
    }

    @Test
    fun deleteNoteLabelModelByNoteId() {
        runBlocking {
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

}

