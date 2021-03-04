package br.com.evenflow.planetsapi.service.api

import br.com.evenflow.planetsapi.model.PlanetStarWars
import br.com.evenflow.planetsapi.model.StarWarsResult
import com.beust.klaxon.Klaxon
import khttp.responses.Response
import org.springframework.beans.factory.annotation.Autowired

object StarKttpClient {

    @Autowired
    lateinit var response: Response

    fun getCountFilmByPlanetName(name: String): Int {
        this.response = khttp.get(PlanetStarWars.BASE_URL + "?search=" + name)

        if (!isResponseEmpty()) {
            val result = this.response.jsonObject.getJSONArray("results").getJSONObject(0)
            val planetWtz = convertJsonToPlanet(result.toString())

            return planetWtz?.films!!.size
        }

        return 0
    }

    fun getStarWarsPlanet(page: Int): StarWarsResult {
        val pageString = if (page > 0) "?page=" + page else ""
        var starWarsResult = StarWarsResult()
        this.response = khttp.get(PlanetStarWars.BASE_URL + pageString)

        if (!isResponseEmpty()) {
            val jsonObject = this.response.jsonObject
            starWarsResult = convertJsonToResultStarWars(jsonObject.toString())
        }

        return starWarsResult
    }



//    fun getStarWarsPlanet(page: Int): ArrayList<PlanetStarWars> {
//        var pageString = if (page > 0) "?page=" + page else ""
//        val listPlanetStarWars = ArrayList<PlanetStarWars>()
//
//        this.response = khttp.get(PlanetStarWars.BASE_URL + pageString)
//
//        if (isResponseEmpty()) {
//            return listPlanetStarWars;
//        }
//
//        val listObjs = this.response.jsonObject.getJSONArray("results").toList()
//
//        listObjs.forEach {
//            var planetTemp = this.convertJsonToPlanet(it.toString())
//            if (planetTemp is PlanetStarWars) {
//                listPlanetStarWars.add(planetTemp)
//            }
//        }
//
//        return listPlanetStarWars
//    }

    private fun convertJsonToPlanet(jsonString: String): PlanetStarWars?{
        return Klaxon().parse<PlanetStarWars>(jsonString)
    }

    private fun convertJsonToResultStarWars(jsonString: String): StarWarsResult {
        val starWarsResult = Klaxon().parse<StarWarsResult>(jsonString)

        if(starWarsResult is StarWarsResult)
            return  starWarsResult

        return StarWarsResult()
    }

    private fun isResponseEmpty(): Boolean {
        if (this.response.statusCode != 200 || this.response.jsonObject.get("count").toString().toInt() == 0)
        return true

        return false
    }
}