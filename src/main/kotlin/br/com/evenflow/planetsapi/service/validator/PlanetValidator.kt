package br.com.evenflow.planetsapi.service.validator

import br.com.evenflow.planetsapi.model.Planet
import org.springframework.stereotype.Component
import org.springframework.util.Assert

@Component
class PlanetValidator {

    fun isValid(planet: Planet): Boolean {
        Assert.hasLength(planet.name, "[name] field cannot be empty")
        Assert.hasLength(planet.terrain, "[terrain] field cannot be empty")
        Assert.hasLength(planet.climate, "[climate] field cannot be empty")

        return true
    }
}