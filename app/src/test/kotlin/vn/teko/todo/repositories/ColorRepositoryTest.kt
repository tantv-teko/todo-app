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
import vn.teko.todo.exception.NotFoundException
import java.time.LocalDateTime
import java.util.*


@ExtendWith(SpringExtension::class)
@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class ColorRepositoryTest {

    @Autowired
    private lateinit var colorRepository: ColorRepository
    @Autowired
    private lateinit var noteRepository: NoteRepository
    @Autowired
    private lateinit var noteColorRepository: NoteColorRepository

    @Test
    fun findAll() {

        val colorModel1 = ColorModel(
            id = 0,
            name = "aaa",
            code = "111",
        )
        val colorModel2 = ColorModel(
            id = 0,
            name = "bbb",
            code = "222",
        )
        val colorModel3 = ColorModel(
            id = 0,
            name = "ccc",
            code = "333",
        )
        runBlocking {
            colorRepository.save(colorModel1)
            colorRepository.save(colorModel2)
            colorRepository.save(colorModel3)
            val colors = colorRepository.findAll().toList().map { it.toColor() }
            assertThat(colors.size).isNotNull()
        }
    }

    @Test
    fun findById() {
        val colorModel1 = ColorModel(
            id = 0,
            name = "aaa",
            code = "111",
        )
        runBlocking {
            val colorModel = colorRepository.save(colorModel1)
            val color = colorRepository.findById(colorModel.id) ?: throw NotFoundException(message = "not found colorid = $colorModel.id ")
            assertThat(color).isEqualTo(colorModel)
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
            val color1 = ColorModel(
                id = 0,
                name = "test",
                code = "111",
                isDefault = false,
            )
            val color = colorRepository.save(color1)
            noteColorRepository.save(
                NoteColorModel(
                    id = 0,
                    noteId = note.id,
                    colorId = color.id,
                )
            )

            val colorInNote = colorRepository.findByNoteId(note.id)
            assertThat(colorInNote).isEqualTo(color)
        }
    }

    @Test
    fun findByDefault() {
        runBlocking {
            val color = colorRepository.findByDefault()
            assertThat(color.isDefault).isEqualTo(true)
        }
    }

}

