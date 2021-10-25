package vn.teko.todo.controllers

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

    @PostMapping
    fun addLabel(
        @RequestBody labelDto: LabelDto,
    ): ResponseEntity<LabelDto> {
        println("post Label")
        return ResponseEntity.ok(labelService.addLabel(labelDto.toLabel()).toLabelDto())

    }
}

