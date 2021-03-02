package br.com.evenflow.planetsapi.integration

import br.com.evenflow.planetsapi.model.Planet
import br.com.evenflow.planetsapi.repository.PlanetRepository
import br.com.evenflow.planetsapi.service.PlanetService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class PlanetControllerTest {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var planetService: PlanetService
    @Autowired lateinit var planetRepository: PlanetRepository

    @Test
    fun `test get all planets`(){

        planetService.add(Planet(name = "Terra", terrain = "arenoso", climate = "quente"))

        mockMvc.perform(MockMvcRequestBuilders.get("/planets"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].name").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].terrain").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].climate").isString)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `test get planet by id`(){

        val planet = planetService.add(Planet(name = "Terra", terrain = "arenoso", climate = "quente"))

        mockMvc.perform(MockMvcRequestBuilders.get("/planets/${planet.id}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.id").value(planet.id))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.name").value(planet.name))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.terrain").value(planet.terrain))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.climate").value(planet.climate))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `test create new planet`(){

        val planet = Planet(name = "Terra", terrain = "arenoso", climate = "quente")
        val json = ObjectMapper().writeValueAsString(planet)

        mockMvc.perform(MockMvcRequestBuilders.post("/planets/")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.name").value(planet.name))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.terrain").value(planet.terrain))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.climate").value(planet.climate))
            .andDo(MockMvcResultHandlers.print())
    }

    //@Test
    fun `test create new planet with error empty name`(){

        val planet = Planet(name = "", terrain = "arenoso", climate = "quente")
        val json = ObjectMapper().writeValueAsString(planet)

        mockMvc.perform(MockMvcRequestBuilders.post("/planets/")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.name").value(planet.name))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.terrain").value(planet.terrain))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.climate").value(planet.climate))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `test update planet`(){

        val planet = planetService
            .add(Planet(name = "Terra", terrain = "arenoso", climate = "quente"))
            .copy(name = "Marte",terrain = "argiloso")
        val json = ObjectMapper().writeValueAsString(planet)

        mockMvc.perform(MockMvcRequestBuilders.put("/planets/${planet.id}")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.name").value(planet.name))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.terrain").value(planet.terrain))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.climate").value(planet.climate))
            .andDo(MockMvcResultHandlers.print())

        val alteredPlanet = planetService.findById(planet.id!!)
        Assertions.assertTrue(alteredPlanet.isPresent)
        Assertions.assertEquals(planet.name, alteredPlanet.get().name)
        Assertions.assertEquals(planet.terrain, alteredPlanet.get().terrain)
    }

    @Test
    fun `test delete planet`(){

        val planet = planetService.add(Planet(name = "Terra", terrain = "arenoso", climate = "quente"))

        mockMvc.perform(MockMvcRequestBuilders.delete("/planets/${planet.id}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())

        val removedPlanet = planetService.findById(planet.id!!)

        Assertions.assertFalse(removedPlanet.isPresent)
    }
}