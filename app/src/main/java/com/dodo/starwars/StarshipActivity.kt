package com.dodo.starwars

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.dodo.starwars.databinding.ActivityStarshipBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class StarshipActivity : AppCompatActivity() {

    val binding by lazy { ActivityStarshipBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(StarshipViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {
            //Préparation de la requete graphiquement
            binding.progressBar.isVisible = true

            val text = binding.editTextNumber.text.toString()
            val starship = text.toInt()
            thread {
                model.loadData(starship)

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
            var starship = text.toInt()
            starship += 1;
            binding.editTextNumber.setText(starship.toString())
            thread {
                model.loadData(starship)

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
            var starship = text.toInt()
            starship -= 1;
            binding.editTextNumber.setText(starship.toString())
            thread {
                model.loadData(starship)

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
        binding.name.text = model.data?.model ?: "-"
        binding.name.text = model.data?.manufacturer ?: "-"
        binding.name.text = model.data?.cost_in_credits ?: "-"
        binding.name.text = model.data?.length ?: "-"
        binding.name.text = model.data?.max_atmosphering_speed ?: "-"
        binding.name.text = model.data?.crew ?: "-"
        binding.name.text = model.data?.passengers ?: "-"
        binding.name.text = model.data?.cargo_capacity ?: "-"
        binding.name.text = model.data?.consumables ?: "-"
        binding.name.text = model.data?.MGLT ?: "-"
        binding.name.text = model.data?.starship_class ?: "-"

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