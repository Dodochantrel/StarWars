package com.dodo.starwars

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

const val URL_API_STARWARS = "https://swapi.dev/api/"

object RequestUtils {
    val client = OkHttpClient()
    val gson = Gson()

    fun loadPeople(people: Int): PeopleBean {
        //Controle
        //Requête web
        val json = sendGet(URL_API_STARWARS + "people/" + people)
        //Parsing
        return gson.fromJson(json, PeopleBean::class.java)
    }

    fun loadPlanet(planet: Int): PlanetBean {
        //Controle
        //Requête web
        val json = sendGet(URL_API_STARWARS + "planets/" + planet)
        //Parsing
        return gson.fromJson(json, PlanetBean::class.java)
    }

    fun loadStarship(starship: Int): StarshipBean {
        //Controle
        //Requête web
        val json = sendGet(URL_API_STARWARS + "starships/" + starship)
        //Parsing
        return gson.fromJson(json, StarshipBean::class.java)
    }

    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                //On regarde s'il n'y a pas un JSON de le message d'erreur
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }

}

