package com.dodo.starwars

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.dodo.starwars.databinding.ActivityPlanetBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread


class PlanetActivity : AppCompatActivity() {
    val binding by lazy { ActivityPlanetBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(PlanetViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {
            //Préparation de la requete graphiquement
            binding.progressBar.isVisible = true

            val text = binding.editTextNumber.text.toString()
            val planet = text.toInt()
            thread {
                model.loadData(planet)

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
            var planet = text.toInt()
            planet += 1;
            binding.editTextNumber.setText(planet.toString())
            thread {
                model.loadData(planet)

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
            var planet = text.toInt()
            planet -= 1;
            binding.editTextNumber.setText(planet.toString())
            thread {
                model.loadData(planet)

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
        binding.name.text = model.data?.name?: "-"
        binding.rotationperiod.text = model.data?.rotation_period?: "-"
        binding.orbitalperiod.text = model.data?.orbital_period ?: "-"
        binding.diameter.text = model.data?.diameter ?: "-"
        binding.climate.text = model.data?.climate ?: "-"
        binding.gravity.text = model.data?.gravity ?: "-"
        binding.terrain.text = model.data?.terrain ?: "-"
        binding.surfacewater.text = model.data?.surface_water ?: "-"
        binding.population.text = model.data?.population ?: "-"

        //Affichage de l'erreur
        if (model.errorMessage.isNotBlank()) {
            binding.tvError.text = model.errorMessage
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