package vn.teko.todo.services

import org.springframework.stereotype.Service
import vn.teko.todo.repositories.ColorRepository
import vn.teko.todo.repositories.toColor
import vn.teko.todo.repositories.toColorModel

@Service
class ColorServiceImpl(
    private val colorRepository: ColorRepository,
) : ColorService {

    override fun getColors(): List<Color> {
        val colorModelList = colorRepository.findAll();
        var colorsList = mutableListOf<Color>()
        colorModelList.forEach() {
            colorsList.add(it.toColor())
        }
        return colorsList
    }

    override fun addColor(color: Color): Color {
        return colorRepository.save(color.toColorModel()).toColor()
    }

}
