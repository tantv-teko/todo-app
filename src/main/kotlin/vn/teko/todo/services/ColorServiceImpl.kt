package vn.teko.todo.services

import org.springframework.stereotype.Service
import vn.teko.todo.exception.NotFoundException
import vn.teko.todo.repositories.*
import javax.transaction.Transactional

@Service
@Transactional
class ColorServiceImpl(
    private val colorRepository: ColorRepository,
) : ColorService {

    override fun getColors(): List<Color> {
        val colors = colorRepository.findAll().map { it.toColor() }
        return colors
    }

    override fun getColor(id: Long): Color {
        return colorRepository.findById(id)
                .orElseThrow { NotFoundException(message = "not found colorid = $id ") }
                .toColor()
    }

}