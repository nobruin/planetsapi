package br.com.evenflow.planetsapi.controller

import br.com.evenflow.planetsapi.model.Planet
import br.com.evenflow.planetsapi.service.PlanetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("planets")
class PlanetController(val service: PlanetService) {


    @GetMapping
    fun list(): ResponseEntity<List<Planet>> {
        val planets = service.search().toList()
        return ResponseEntity.ok(planets)

    }

    @GetMapping("{id}")
    fun show(@PathVariable id: Long): ResponseEntity<Planet>? = service.findById(id).map {
        ResponseEntity.ok(it)
    }.orElse(ResponseEntity.notFound().build())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody planet: Planet): ResponseEntity<Planet> {
        val safePlanet = service.add(planet)
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
}