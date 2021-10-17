package vn.teko.todo.Services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import vn.teko.todo.repositories.NoteRepository

@Service
class NoteServiceImpl(@Autowired private val noterepository: NoteRepository) : NoteService{

    override fun getAllnotes(): MutableIterable<Note> {
       return noterepository.findAll();
    }

}