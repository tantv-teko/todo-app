package vn.teko.todo.Services

interface LabelService {
    fun getLables() : List<Label>
    fun addLabel(label: Label) : Label
    fun findLabelByid(id: Long) : Label
    fun updateLabel(id: Long, name: String?) : Label
    fun deleteLabel(id: Long) : Label
}

