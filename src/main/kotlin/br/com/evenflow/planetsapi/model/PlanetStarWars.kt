package br.com.evenflow.planetsapi.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PlanetStarWars(
    val name: String? = null,
    val diameter: String? = null,
    val gravity: String? = null,
    val population: String? = null,
    val climate: String? = null,
    val terrain: String? = null,
    val created: String? = null,
    val edited: String? = null,
    val url: String? = null,
    @field:JsonProperty("rotation_period")
    val rotation_period: String? = null,
    @field:JsonProperty("orbital_period")
    val orbitalPeriod: String? = null,
    @field:JsonProperty("surface_water")
    val surfaceWater: String? = null,
    val residents: List<String>? = null,
    val films: List<String>? = null
){
    companion object{
        const val BASE_URL = "https://swapi.dev/api/planets"
        const val APPLICATION_NAME = "PLANETSAPI"
    }
}