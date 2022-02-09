package vn.teko.todo.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import vn.teko.todo.resquest.AddLabelRequest
import vn.teko.todo.resquest.UpdateLabelRequest
import vn.teko.todo.resquest.toLabel
import vn.teko.todo.routers.LabelRouterConfiguration
import vn.teko.todo.services.ColorService
import vn.teko.todo.services.Label
import vn.teko.todo.services.LabelService

@ExtendWith(SpringExtension::class)
@WebFluxTest(LabelHandler::class, LabelRouterConfiguration::class)
internal class LabelHandlerTest {

    @Autowired
    private lateinit var client: WebTestClient
    @MockBean
    private lateinit var labelservice: LabelService
    @Autowired
    lateinit var objectMapper: ObjectMapper

    private val addRequest = AddLabelRequest(
        name = "test add label 1"
    )
    private val updateRequest = UpdateLabelRequest(
        id = 0,
        name = "test update label 1"
    )
    @BeforeEach
    fun setup() {
        val label1 = Label(
            id = 1,
            name = "name1",
        )
        val label2 = Label(
            id = 2,
            name = "name2",
        )
        val label3 = Label(
            id = 3,
            name = "name3",
        )
        val label4 = Label(
            id = 4,
            name = "name4",
        )
        val labels = listOf<Label>(label1, label2, label3, label4)
        runBlocking {
            given(labelservice.getLabels()).willReturn(labels)
            given(labelservice.getLabel(1)).willReturn(label1)
            given(labelservice.getLabel(2)).willReturn(label2)
            given(labelservice.createLabel(addRequest.toLabel())).willReturn(
                Label(
                    id = 5,
                    name = "test add label 1",
                )
            )
            given(labelservice.updateLabel(1, updateRequest.toLabel())).willReturn(
                Label(
                    id = 1,
                    name = "test update label 1",
                )
            )
            given(labelservice.deleteLabel(3)).willReturn(label3)
        }

    }


    @Test
    fun getLabels() {
        client.get().uri("/api/labels")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.length()").isEqualTo(4)
            .jsonPath("$.[1].name").isEqualTo("name2")
    }

    @Test
    fun getLabelById() {
        runBlocking {
            client.get().uri("/api/labels/1")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.name").isEqualTo(labelservice.getLabel(1).name)
            client.get().uri("/api/labels/2")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.name").isEqualTo(labelservice.getLabel(2).name)
        }
    }


    @Test
    fun createLabel() {
        val requestJson = objectMapper.writeValueAsString(addRequest)
        runBlocking {
            client.post().uri("/api/labels")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJson)
                .exchange()
                .expectStatus().isOk
            Mockito.verify(labelservice).createLabel(addRequest.toLabel())
        }
    }


    @Test
    fun updateLabel() {
           val requestJson = objectMapper.writeValueAsString(updateRequest)
           runBlocking {
               client.put().uri("/api/labels/1")
                   .accept(MediaType.APPLICATION_JSON)
                   .contentType(MediaType.APPLICATION_JSON)
                   .bodyValue(requestJson)
                   .exchange()
                   .expectStatus().isOk
               Mockito.verify(labelservice).updateLabel(1, updateRequest.toLabel())
           }
       }


    @Test
    fun deleteLabel() {
        runBlocking {
            client.delete().uri("/api/labels/3")
                .exchange()
                .expectStatus().isOk
            Mockito.verify(labelservice).deleteLabel(3)
        }
    }




}