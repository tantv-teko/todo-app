package vn.teko.todo.Services

import org.springframework.stereotype.Service
import vn.teko.todo.repositories.LabelRepository
import vn.teko.todo.repositories.toLabel
import vn.teko.todo.repositories.toLabelModel

@Service
class LabelServiceImpl(
    private var labelRepositories: LabelRepository,
) : LabelService {

    override fun getLables(): List<Label> {
        val labeModellList = labelRepositories.findAll()
        var laybeList = mutableListOf<Label>()
        labeModellList.forEach() {
            laybeList.add(it.toLabel())
        }
        return laybeList
    }

    override fun addLabel(label: Label): Label {
        return labelRepositories.save(label.toLabelModel()).toLabel()
    }

    override fun findLabelByid(id: Long): Label {
        return labelRepositories.getLabelModelById(id).toLabel()
    }

    override fun updateLaBel(id: Long): Label {

    }
}

