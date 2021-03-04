package br.com.evenflow.planetsapi.controller

import br.com.evenflow.planetsapi.model.Planet
import br.com.evenflow.planetsapi.model.StarWarsResult
import br.com.evenflow.planetsapi.service.PlanetService
import br.com.evenflow.planetsapi.service.api.StarKttpClient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("planets")
class PlanetController(val service: PlanetService) {


    @GetMapping
    fun list(
        @RequestParam(defaultValue = "") name: String,
        pageable: Pageable
    ): Page<Planet>?  {
        return service.search(name, pageable)
    }

    @GetMapping("{id}")
    fun show(@PathVariable id: Long): ResponseEntity<Planet>? = service.findById(id).map {
        ResponseEntity.ok(it)
    }.orElse(ResponseEntity.notFound().build())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody planet: Planet): ResponseEntity<Planet> {
        service.add(planet)
        return ResponseEntity.status(HttpStatus.CREATED).body(planet)
    }

    @PutMapping("{id}")
    fun alter(@PathVariable id: Long,@RequestBody planet: Planet): ResponseEntity<Planet> {
        if(!service.existsById(id)){
            return ResponseEntity.notFound().build()
        }

        val safePlanet = service.update(id, planet)
        return ResponseEntity.ok(safePlanet)
    }

    @DeleteMapping( "{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        if(!service.existsById(id)){
            return ResponseEntity.notFound().build()
        }
        service.deleteById(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/starwars")
    fun listStarWarsPlanets(@RequestParam(defaultValue = "0") page: Int): StarWarsResult {
        return StarKttpClient.getStarWarsPlanet(page)
    }
}