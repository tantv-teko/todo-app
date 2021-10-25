package vn.teko.todo.controllers

import javassist.bytecode.annotation.LongMemberValue
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import vn.teko.todo.Services.LabelService

@RestController
@RequestMapping("/api/labels")
class LabelController(
    private val labelService: LabelService,
) {

    @GetMapping
    fun getAllLabels(): ResponseEntity<List<LabelDto>> {
        println("getAllLaybel")
        val listLabels = labelService.getLables()
        var listLabelDto = mutableListOf<LabelDto>()
        listLabels.forEach() {
            listLabelDto.add(it.toLabelDto())
        }
        return ResponseEntity.ok(listLabelDto)
    }

    @GetMapping("/{id}")
    fun getLabelById(
        @PathVariable id: Long,
    ): ResponseEntity<LabelDto> {
        return ResponseEntity.ok(labelService.findLabelByid(id).toLabelDto())
    }

    @PostMapping
    fun addLabel(
        @RequestBody labelDto: LabelDto,
    ): ResponseEntity<LabelDto> {
        println("post Label")
        return ResponseEntity.ok(labelService.addLabel(labelDto.toLabel()).toLabelDto())

    }

    @PutMapping
    fun updateLabel(
        @PathVariable id: Long,
        @RequestBody labelDto: LabelDto,
    ): ResponseEntity<LabelDto> {
        return  ResponseEntity.ok(labelService.updateLabel(id, labelDto.toLabel()).toLabelDto())
    }

    @DeleteMapping("/{id}")
    fun  deleteLabel(
        @PathVariable id: Long,
     ): ResponseEntity<LabelDto> {
        return ResponseEntity.ok(labelService.deleteLabel(id).toLabelDto())
    }

}

