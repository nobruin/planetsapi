package br.com.evenflow.planetsapi.service

import br.com.evenflow.planetsapi.model.Planet
import br.com.evenflow.planetsapi.repository.PlanetRepository
import br.com.evenflow.planetsapi.service.validator.PlanetValidator
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.util.*

@Service
class PlanetService(val planetRepository: PlanetRepository, val validator: PlanetValidator) {

    fun search(): List<Planet> {
        return planetRepository.findAll().toList()
    }

    fun add(planet: Planet): Planet {
        validator.isValid(planet)
        return planetRepository.save(planet)
    }

    fun update(id: Long, planet: Planet): Planet{
            validator.isValid(planet)
            val safePlanet = planet.copy(id)
            return planetRepository.save(safePlanet)
    }

    fun existsById(id: Long): Boolean {
        return planetRepository.existsById(id)
    }

    fun findById(id: Long): Optional<Planet> {
        return planetRepository.findById(id)
    }

    fun deleteById(id: Long){
        if (planetRepository.existsById(id)) {
            planetRepository.deleteById(id)
        }
    }
}