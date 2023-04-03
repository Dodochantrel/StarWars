package com.dodo.starwars

import androidx.lifecycle.ViewModel

class PeopleViewModel : ViewModel() {
    var errorMessage = ""
    var data : PeopleBean? = null

    fun loadData(people: Int){
        errorMessage = ""
        data = null
        try {
            //Requêtes
            data = RequestUtils.loadPeople(people)
        }
        catch (e: Exception) {
            //J'affiche le detail de l'erreur dans la console
            e.printStackTrace()
            errorMessage = e.message ?: "Une erreur est survenue"
        }
    }
}