/*
package vn.teko.todo.repositories

import org.springframework.stereotype.Component
import vn.teko.todo.Services.Label
import vn.teko.todo.controllers.LabelDto

@Component
class LabelModel {
    fun mapToDto(label: Label) : LabelDto {
        var labelDto =  LabelDto(label.labelId, label.name)
        return labelDto;
    }

    fun mapToEntity(labelDto: LabelDto) : Label {
        var label = Label(labelDto.labelId, labelDto.name)
        return  label;
    }
}


 */