package vn.teko.todo.services

import kotlinx.coroutines.flow.toList
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vn.teko.todo.exception.NotFoundException
import vn.teko.todo.repositories.*

@Service
@Transactional
class ColorServiceImpl(
    private val colorRepository: ColorRepository,
) : ColorService {

    @Cacheable("color")
    override suspend fun getColors(): List<Color> {
        return colorRepository.findAll().toList().map { it.toColor() }
    }

    @Cacheable("color", key = "#id")
    override suspend fun getColor(id: Long): Color {
        val colorModel = colorRepository.findById(id) ?: throw NotFoundException(message = "not found colorid = $id ")
        return colorModel.toColor()
    }

}