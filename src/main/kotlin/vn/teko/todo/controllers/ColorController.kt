package vn.teko.todo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import vn.teko.todo.services.ColorService

@RestController
@RequestMapping("/api/colors")
class ColorController(
    private val colorService: ColorService,

) {

    @GetMapping
    fun getAllColors(): ResponseEntity<List<ColorDto>> {
        println("getAllColor")
        val listColor = colorService.getColors()
        var listColorDto = mutableListOf<ColorDto>()
        listColor.forEach() {
            listColorDto.add(it.toDto())
        }
        return ResponseEntity.ok(listColorDto)
    }

    @PostMapping
    fun addColor(
        @RequestBody colorDto: ColorDto,
    ): ResponseEntity<ColorDto> {
        println("post Color")
        return ResponseEntity.ok(colorService.addColor(colorDto.toColor()).toDto())

    }

}