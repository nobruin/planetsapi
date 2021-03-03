package br.com.evenflow.planetsapi.service.api

import br.com.evenflow.planetsapi.model.PlanetStarWars
import com.beust.klaxon.Klaxon

object StarKttpClient {


    fun getCountFilmByPlanetName(name: String): Int {
        val response = khttp.get(PlanetStarWars.BASE_URL.toString()+"?search="+name)

        if(response.statusCode == 200 && response.jsonObject.get("count").toString().toInt() > 0){
            val result  = response.jsonObject.getJSONArray("results").getJSONObject(0)
            val planetWtz = Klaxon().parse<PlanetStarWars>(result.toString())

            return planetWtz?.films!!.size
        }

        return 0
    }
}