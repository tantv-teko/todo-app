/*
package vn.teko.todo.Services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import vn.teko.todo.controllers.LabelDto
import vn.teko.todo.repositories.LabelModel
import vn.teko.todo.repositories.LabelRepository

@Service
class LabelServiceImpl(
    @Autowired private var labelRepositories: LabelRepository,
    @Autowired private var labelModel: LabelModel) : LabelService {

    override fun getAllLable(): MutableList<LabelDto> {
        val labelList = labelRepositories.findAll()
        var laybeDtoList : MutableList<LabelDto> = mutableListOf()
        labelList.forEach() {
            laybeDtoList.add(labelModel.mapToDto(it))
        }
        return laybeDtoList
    }

    override fun createLable(labelDto: LabelDto): LabelDto {
        labelRepositories.save(labelModel.mapToEntity(labelDto))
        return labelDto
    }

}

 */