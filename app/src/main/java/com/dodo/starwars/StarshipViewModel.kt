package com.dodo.starwars

import androidx.lifecycle.ViewModel

class StarshipViewModel : ViewModel() {
    var errorMessage = ""
    var data : StarshipBean? = null

    fun loadData(starship: Int){
        errorMessage = ""
        data = null
        try {
            //Requêtes
            data = RequestUtils.loadStarship(starship)
        }
        catch (e: Exception) {
            //J'affiche le detail de l'erreur dans la console
            e.printStackTrace()
            errorMessage = e.message ?: "Une erreur est survenue"
        }
    }
}