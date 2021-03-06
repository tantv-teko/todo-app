package vn.teko.todo.resquest

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import vn.teko.todo.controllers.toLabelDto
import vn.teko.todo.services.Label

internal class AddLabelRequestTest {

    @Test
    fun toLabel() {
        val addLabelRequest = AddLabelRequest(
            name = "test"
        )
        val label = addLabelRequest.toLabel()
        Assertions.assertThat(label.id).isEqualTo(0)
        Assertions.assertThat(label.name).isEqualTo(addLabelRequest.name)
    }
}