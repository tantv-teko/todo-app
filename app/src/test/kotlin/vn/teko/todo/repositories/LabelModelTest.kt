package vn.teko.todo.repositories

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import vn.teko.todo.services.Label

internal class LabelModelTest {

    @Test
    fun toLabel() {
        val labelModel = LabelModel(
            id = 0,
            name = "test",
        )
        val label = labelModel.toLabel()
        Assertions.assertThat(label.id).isEqualTo(labelModel.id)
        Assertions.assertThat(label.name).isEqualTo(labelModel.name)
    }

    @Test
    fun toLabelModel() {
        val label = Label(
            id = 0,
            name = "test",
        )
        val labelModel = label.toLabelModel()
        Assertions.assertThat(label.id).isEqualTo(labelModel.id)
        Assertions.assertThat(label.name).isEqualTo(labelModel.name)
    }
}