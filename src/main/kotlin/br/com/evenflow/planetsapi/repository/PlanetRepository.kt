package br.com.evenflow.planetsapi.repository

import br.com.evenflow.planetsapi.model.Planet
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PlanetRepository: JpaRepository<Planet, Long>{
    fun findAllByNameContains(name: String, pageable: Pageable): Page<Planet>?

    fun findByName(name: String): Planet?

    fun existsByName(name: String):Boolean
}