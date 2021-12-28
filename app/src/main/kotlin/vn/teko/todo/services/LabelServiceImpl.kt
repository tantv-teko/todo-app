package vn.teko.todo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vn.teko.todo.exception.NotFoundException
import vn.teko.todo.repositories.LabelRepository
import vn.teko.todo.repositories.toLabel
import vn.teko.todo.repositories.toLabelModel

@Service
@Transactional
class LabelServiceImpl(
    private val labelRepositories: LabelRepository,
) : LabelService {

    override fun getLabels(): List<Label> {
        println("sfsfsfsfsfsfs")
        val labels = labelRepositories.findAll().map { it.toLabel() }
        return labels
    }

    override fun getLabel(id: Long): Label {
        return labelRepositories.findById(id)
                .orElseThrow { NotFoundException(message = "not found labelid = $id ") }
                .toLabel()
    }

    override fun createLabel(label: Label): Label {
        return labelRepositories.save(label.toLabelModel()).toLabel()
    }

    override fun updateLabel(id: Long, newLabel: Label): Label {
        val labelModel = labelRepositories.findById(id)
                .orElseThrow { NotFoundException(message = "not found labelid = $id ") }
        val label = labelModel.toLabel().apply {
            name = newLabel.name
        }
        return labelRepositories.save(label.toLabelModel()).toLabel()
    }

    override fun deleteLabel(id: Long): Label {
        val labelModel = labelRepositories.findById(id)
                .orElseThrow { NotFoundException(message = "not found labelid = $id ") }
        labelRepositories.deleteById(id)
        return labelModel.toLabel()
    }
}

