package vn.teko.todo.resquest

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UpdateLabelRequestTest {

    @Test
    fun toLabel() {
        val updateLabelRequest = UpdateLabelRequest(
            id = 0,
            name = "test"
        )
        val label = updateLabelRequest.toLabel()
        Assertions.assertThat(label.id).isEqualTo(updateLabelRequest.id)
        Assertions.assertThat(label.name).isEqualTo(updateLabelRequest.name)
    }
}