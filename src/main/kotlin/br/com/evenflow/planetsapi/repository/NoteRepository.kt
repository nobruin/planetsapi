package br.com.evenflow.planetsapi.repository

import br.com.evenflow.planetsapi.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository : CrudRepository<Note, Long>