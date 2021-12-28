package vn.teko.todo.repositories

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
internal class NoteColorRepositoryTest {

    @Autowired
    private lateinit var noteColorRepository: NoteColorRepository
    @Autowired
    private lateinit var noteRepository: NoteRepository
    @Autowired
    private lateinit var colorRepository: ColorRepository
    @Autowired
    private lateinit var entityManager: TestEntityManager

    private var noteModel = NoteModel(
        id = 0,
        title = "aa",
        content = "aaaa",
        createAt = LocalDateTime.now(),
        editedAt = LocalDateTime.now(),
    )


    @Test
    fun findAll() {
        val note = noteRepository.save(noteModel)
        val noteColor1 = NoteColorModel(
            id = 0,
            noteId = note.id,
            colorId = 1,
        )
        noteColorRepository.save(noteColor1)
        val noteColors = noteColorRepository.findAll()as List<NoteColorModel>
        assertThat(noteColors.size).isNotNull()
    }

   @Test
   fun findById() {
       val note = noteRepository.save(noteModel)
       val noteColor1 = NoteColorModel(
           id = 0,
           noteId = note.id,
           colorId = 2,
       )
       noteColorRepository.save(noteColor1)
       val noteColor = noteColorRepository.findById(noteColor1.id).get()
       assertThat(noteColor).isEqualTo(noteColor1)
   }

   @Test
   fun save() {
       val note = noteRepository.save(noteModel)
       val noteColor1 = NoteColorModel(
           id = 0,
           noteId = note.id,
           colorId = 1,
       )
       val noteColor = noteColorRepository.save(noteColor1)
       assertThat(noteColor).isEqualTo(noteColor1)
   }

   @Test
   fun deleteById() {
       val note = noteRepository.save(noteModel)
       val noteColor1 = NoteColorModel(
           id = 0,
           noteId = note.id,
           colorId = 1,
       )
       val noteColor = noteColorRepository.save(noteColor1)
       noteColorRepository.deleteById(noteColor.id)
       entityManager.flush();
       assertThat(noteColorRepository.findById(noteColor.id).isPresent).isEqualTo(false)
   }

   @Test
   fun deleteByNoteId() {
       val note = noteRepository.save(noteModel)
       val noteColor1 = NoteColorModel(
           id = 0,
           noteId = note.id,
           colorId = 1,
       )

       val noteColor = noteColorRepository.save(noteColor1)
       noteColorRepository.deleteByNoteId(noteColor.noteId)
       val optionalnoteColor = noteColorRepository.findById(note.id)
       assertThat(optionalnoteColor.isPresent).isEqualTo(false)
   }

}