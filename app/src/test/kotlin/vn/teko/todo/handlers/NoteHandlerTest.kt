package vn.teko.todo.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import vn.teko.todo.resquest.AddNoteRequest
import vn.teko.todo.resquest.UpdateNoteRequest
import vn.teko.todo.resquest.toLabel
import vn.teko.todo.resquest.toNote
import vn.teko.todo.routers.NoteRouterConfiguration
import vn.teko.todo.services.Note
import vn.teko.todo.services.NoteService
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@WebFluxTest(NoteHandler::class, NoteRouterConfiguration::class)
internal class NoteHandlerTest {

    @Autowired
    private lateinit var client: WebTestClient

    @MockBean
    private lateinit var noteService: NoteService

    @Autowired
    lateinit var objectMapper: ObjectMapper

    private val addRequest = AddNoteRequest(
        title = "add note",
        content = "ssss",
        colorId = 1,
    )
    private val updateRequest = UpdateNoteRequest(
        id = 1,
        title = "update note",
        content = "ssss",
        colorId = 1,
    )


    @BeforeEach
    fun setUp() {
        val note1 = Note(
            id = 1,
            title = "111",
            content = "note 1",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        val note2 = Note(
            id = 2,
            title = "222",
            content = "note 2",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        val note3 = Note(
            id = 3,
            title = "333",
            content = "note 3",
            createAt = LocalDateTime.now(),
            editedAt = LocalDateTime.now(),
        )
        val notes = listOf<Note>(note1, note2, note3)
        runBlocking {
            given(noteService.getNotes()).willReturn(notes)
            given(noteService.getNote(1)).willReturn(note1)
            given(noteService.getNote(2)).willReturn(note2)
            given(noteService.createNote(addRequest.toNote())).willReturn(
                Note(
                    id = 4,
                    title = "add note",
                    content = "ssss",
                    createAt = LocalDateTime.now(),
                    editedAt = LocalDateTime.now(),
                )
            )
            given(noteService.updateNote(1, updateRequest.toNote())).willReturn(
                Note(
                    id = 1,
                    title = "update note",
                    content = "ssss",
                    createAt = note1.createAt,
                    editedAt = LocalDateTime.now(),
                )
            )
            given(noteService.deleteNote(3)).willReturn(note3)
        }
    }

    @Test
    fun getNotes() {
        client.get().uri("/api/notes")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.length()").isEqualTo(3)
            .jsonPath("$.[1].title").isEqualTo("222")
    }


    @Test
    fun getNote() {

        runBlocking {
            client.get().uri("/api/notes/1")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.title").isEqualTo(noteService.getNote(1).title)
            client.get().uri("/api/notes/2")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.title").isEqualTo(noteService.getNote(2).title)
        }
    }

    @Test
    fun createnote() {
        val requestJson = objectMapper.writeValueAsString(addRequest)
        runBlocking {
            client.post().uri("/api/notes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJson)
                .exchange()
                .expectStatus().isOk
            Mockito.verify(noteService).createNote(addRequest.toNote())
        }

    }

    @Test
    fun updatenote() {
        val requestJson = objectMapper.writeValueAsString(updateRequest)
        runBlocking {
            client.put().uri("/api/notes/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJson)
                .exchange()
                .expectStatus().isOk
            Mockito.verify(noteService).updateNote(1, updateRequest.toNote())
        }

    }

    @Test
    fun deletenote() {
        runBlocking {
            client.delete().uri("/api/notes/3")
                .exchange()
                .expectStatus().isOk
            Mockito.verify(noteService).deleteNote(3)
        }
    }

}