package br.com.evenflow.planetsapi.repository

import br.com.evenflow.planetsapi.model.Planet
import org.springframework.data.repository.CrudRepository

interface PlanetRepository: CrudRepository<Planet, Long>