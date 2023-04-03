package com.dodo.starwars

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.dodo.starwars.databinding.ActivityPeopleBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class PeopleActivity : AppCompatActivity() {

    val binding by lazy { ActivityPeopleBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(PeopleViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {
            //Préparation de la requete graphiquement
            binding.progressBar.isVisible = true

            val text = binding.editTextNumber.text.toString()
            val people = text.toInt()
            thread {
                model.loadData(people)

                //Mise à jour graphique obligatoirement sur le thread principale
                runOnUiThread {
                    refreshScreen()
                    binding.progressBar.isVisible = false
                }
            }
        }
        //Arriver sur l'écran je mets à jour ems données au cas ou elles sont présentes
        refreshScreen()

        binding.btnNext.setOnClickListener {
            binding.progressBar.isVisible = true
            val text = binding.editTextNumber.text.toString()
            var people = text.toInt()
            people += 1;
            binding.editTextNumber.setText(people.toString())
            thread {
                model.loadData(people)

                //Mise à jour graphique obligatoirement sur le thread principale
                runOnUiThread {
                    refreshScreen()
                    binding.progressBar.isVisible = false
                }
            }
        }

        binding.btnPrevious.setOnClickListener {
            binding.progressBar.isVisible = true
            val text = binding.editTextNumber.text.toString()
            var people = text.toInt()
            people -= 1;
            binding.editTextNumber.setText(people.toString())
            thread {
                model.loadData(people)

                //Mise à jour graphique obligatoirement sur le thread principale
                runOnUiThread {
                    refreshScreen()
                    binding.progressBar.isVisible = false
                }
            }
        }
        binding.buttonAccueil.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun refreshScreen() {
        //Affichage des données
        binding.name.text = model.data?.name ?: "-"
        binding.height.text = model.data?.height?: "-"
        binding.mass.text = model.data?.mass ?: "-"
        binding.haircolor.text = model.data?.hair_color ?: "-"
        binding.skincolor.text = model.data?.skin_color ?: "-"
        binding.eyecolor.text = model.data?.eye_color ?: "-"
        binding.birthyear.text = model.data?.birth_year ?: "-"
        binding.gender.text = model.data?.gender ?: "-"

        //Affichage de l'erreur
        if (model.errorMessage.isNotBlank()) {
            binding.tvError.text = "Ce numéro de personnage n'existe pas !"
            binding.tvError.isVisible = true
        }
        else {
            binding.tvError.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == 0) {
            //Changement d'écran
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        if(item.itemId == 1) {
            //Changement d'écran
            val intent = Intent(this, PeopleActivity::class.java)
            startActivity(intent)
        }

        if(item.itemId == 2) {
            //Changement d'écran
            val intent = Intent(this, PlanetActivity::class.java)
            startActivity(intent)
        }

        if(item.itemId == 3) {
            //Changement d'écran
            val intent = Intent(this, StarshipActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

}