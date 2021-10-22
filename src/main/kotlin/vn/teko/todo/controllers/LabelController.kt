/*
package vn.teko.todo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import vn.teko.todo.Services.LabelService

@RestController
class LabelController(
    @Autowired private val labelService: LabelService) {

    @GetMapping("/GET/api/labels")
    fun getAllLabels(): ResponseEntity<MutableList<LabelDto>> {
        return ResponseEntity.ok(labelService.getAllLable())
    }

    @PostMapping("/Post/api/labels")
    fun createLabel(@RequestBody labelDto: LabelDto): ResponseEntity<LabelDto> {
        return ResponseEntity.ok(labelService.createLable(labelDto))
    }


}
*/
