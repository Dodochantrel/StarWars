package com.dodo.starwars

import androidx.lifecycle.ViewModel

class PlanetViewModel : ViewModel() {
    var errorMessage = ""
    var data : PlanetBean? = null

    fun loadData(planet: Int){
        errorMessage = ""
        data = null
        try {
            //Requêtes
            data = RequestUtils.loadPlanet(planet)
        }
        catch (e: Exception) {
            //J'affiche le detail de l'erreur dans la console
            e.printStackTrace()
            errorMessage = e.message ?: "Une erreur est survenue"
        }
    }
}