package br.com.evenflow.planetsapi.model

data class StarWarsResult(
    var count: Int? = 0,
    var next: String? = "",
    var previous: String? = "",
    var results: List<PlanetStarWars?> = ArrayList<PlanetStarWars>()
)