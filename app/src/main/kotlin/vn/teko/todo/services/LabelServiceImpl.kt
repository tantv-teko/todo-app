package vn.teko.todo.services

import kotlinx.coroutines.flow.toList
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

    override suspend fun getLabels(): List<Label> {
        val labels = labelRepositories.findAll().toList().map { it.toLabel() };
        return labels;

    }
    override suspend fun getLabel(id: Long): Label {
        val labelModel = labelRepositories.findById(id) ?: throw NotFoundException(message = "not found labelid = $id ")
        return labelModel.toLabel();
    }

    override suspend fun createLabel(label: Label): Label {
        return labelRepositories.save(label.toLabelModel()).toLabel()
    }

    override suspend fun updateLabel(id: Long, newLabel: Label): Label {
        val labelModel = labelRepositories.findById(id) ?: throw NotFoundException(message = "not found labelid = $id ")
        val label = labelModel.toLabel().apply {
            name = newLabel.name
        }
        return labelRepositories.save(label.toLabelModel()).toLabel()
    }

    override suspend fun deleteLabel(id: Long): Label {
        val labelModel = labelRepositories.findById(id) ?: throw NotFoundException(message = "not found labelid = $id ")
        labelRepositories.deleteById(id)
        return labelModel.toLabel()
    }
}

