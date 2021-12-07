package vn.teko.todo.repositories

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime
import java.util.*


@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class ColorRepositoryTest {

    @Autowired
    private lateinit var colorRepository: ColorRepository
    @Autowired
    private lateinit var noteRepository: NoteRepository
    @Autowired
    private lateinit var noteColorRepository: NoteColorRepository
    @Autowired
    private lateinit var entityManager: TestEntityManager

    @AfterEach
    fun destroyAll() {
       entityManager.clear()
    }
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
        entityManager.persist(colorModel1)
        entityManager.persist(colorModel2)
        entityManager.persist(colorModel3)
        val colors = colorRepository.findAll().map { it.toColor() }
        println("aaaaaaa "+ colors.size)
        assertThat(colors.size).isNotNull()
    }

    @Test
    fun findById() {
        val colorModel1 = ColorModel(
            id = 0,
            name = "aaa",
            code = "111",
        )
        entityManager.persist(colorModel1)
        val color = colorRepository.findById(colorModel1.id).get()
        assertThat(color).isEqualTo(colorModel1)
    }

    @Test
    fun findByNoteId() {
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
        noteColorRepository.save(NoteColorModel(
            id = 0,
            noteId = note.id,
            colorId = color.id,
        ))

        val colorInNote = colorRepository.findByNoteId(note.id)
        assertThat(colorInNote).isEqualTo(color)
    }

    @Test
    fun findByDefault() {
        val color = colorRepository.findByDefault()
        assertThat(color.isDefault).isEqualTo(true)
    }
}

