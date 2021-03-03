package br.com.evenflow.planetsapi.service.validator

import br.com.evenflow.planetsapi.model.Planet
import br.com.evenflow.planetsapi.repository.PlanetRepository
import org.springframework.stereotype.Component
import org.springframework.util.Assert

@Component
class PlanetValidator(
   val planetRepository: PlanetRepository
) {

    fun isValid(planet: Planet): Boolean {
        Assert.hasLength(planet.name, "[name] field cannot be empty")
        Assert.hasLength(planet.terrain, "[terrain] field cannot be empty")
        Assert.hasLength(planet.climate, "[climate] field cannot be empty")

        return true
    }

    fun isNameUnique(planet: Planet):Boolean{
        val boolean = !planetRepository.existsByName(planet.name)
        Assert.isTrue(boolean, "[name] field cannot be the same others planets")
        return true
    }
}