package vn.teko.todo.services

interface LabelService {
    suspend fun getLabels() : List<Label>
    suspend fun getLabel(id: Long) : Label
    suspend fun createLabel(label: Label) : Label
    suspend fun updateLabel(id: Long, newLabel: Label) : Label
    suspend fun deleteLabel(id: Long) : Label
}

