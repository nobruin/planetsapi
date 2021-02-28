package br.com.evenflow.planetsapi.controller

import br.com.evenflow.planetsapi.model.Note
import br.com.evenflow.planetsapi.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notes")
class NoteController {

    @Autowired
    lateinit var noteRepository: NoteRepository

    @GetMapping
    fun list(): List<Note> {
        return noteRepository.findAll().toList()
    }

    @PostMapping
    fun add(@RequestBody note: Note): Note {
        return noteRepository.save(note)
    }

    @PutMapping("{id}")
    fun alter(@PathVariable id: Long, @RequestBody note: Note): Note {
        if (noteRepository.existsById(id)) {
            val safeNote = Note(id, note.title, note.description)
            return noteRepository.save(safeNote)
        }

        return Note()
    }


}