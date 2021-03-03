package br.com.evenflow.planetsapi.repository

import br.com.evenflow.planetsapi.model.PlanetStarWars
import br.com.evenflow.planetsapi.model.SWModelList
import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsRepository {

    @GET("planets/")
    fun getAllPlanets(@Query("page") page: Int): Call<SWModelList<PlanetStarWars?>?>?

    @GET("planets/{id}/")
    fun getPlanet(
        @Path("id") planetId: Int,
        callback: Callback<PlanetStarWars?>?
    )

}