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
internal class LabelRepositoryTest {

    @Autowired
    private lateinit var labelRepository: LabelRepository
    @Autowired
    private lateinit var noteRepository: NoteRepository
    @Autowired
    private lateinit var noteLabelRepository: NoteLabelRepository

    @Test
    fun findAll() {
        val label1 = LabelModel(
            id = 0,
            name = "name1",
        )
        val label2 = LabelModel(
            id = 0,
            name = "name2",
        )
        val label3 = LabelModel(
            id = 0,
            name = "name3",
        )
        val label4 = LabelModel(
            id = 0,
            name = "name4",
        )
        runBlocking {
            labelRepository.save(label1)
            labelRepository.save(label2)
            labelRepository.save(label3)
            labelRepository.save(label4)
            val labels = labelRepository.findAll().toList().map { it.toLabel() }
            assertThat(labels.size).isNotNull()
        }
    }

    @Test
    fun findById() {
        var label1 = LabelModel(
            id = 0,
            name = "sss",
        )
        runBlocking {
            label1 = labelRepository.save(label1)
            val label = labelRepository.findById(label1.id)
            assertThat(label).isEqualTo(label1)
        }
    }

    @Test
    fun save() {
        val label1 = LabelModel(
            id = 0,
            name = "sss",
        )
        runBlocking {
            val label1 = labelRepository.save(label1)
            val label = labelRepository.findById(label1.id)
            assertThat(label).isEqualTo(label1)
        }
    }

    @Test
    fun deleteById() {
        val label1 = LabelModel(
            id = 0,
            name = "sss",
        )
        runBlocking {
            val label = labelRepository.save(label1)
            labelRepository.deleteById(label.id)
            val xx = labelRepository.findById(label.id)
            assertThat(labelRepository.findById(label.id)).isNull()
        }
    }

    @Test
    fun findByNoteId() {
        runBlocking {
            val noteModel = NoteModel(
                id = 0,
                title = "aa",
                content = "aaaa",
                createAt = LocalDateTime.now(),
                editedAt = LocalDateTime.now(),
            )
            val note = noteRepository.save(noteModel)
            val label1 = labelRepository.save(
                LabelModel(
                    id = 0,
                    name = "sss",
                )
            )
            val label2 = labelRepository.save(
                LabelModel(
                    id = 0,
                    name = "aaa",
                )
            )
            noteLabelRepository.save(
                NoteLabelModel(
                    id = 0,
                    noteId = note.id,
                    labelId = label1.id,
                )
            )
            noteLabelRepository.save(
                NoteLabelModel(
                    id = 0,
                    noteId = note.id,
                    labelId = label2.id,
                )
            )
            val labels = labelRepository.findByNoteId(note.id)
            assertThat(labels.size).isEqualTo(2)
            assertThat(labels.get(0)).isEqualTo(label1)
        }
    }
}

