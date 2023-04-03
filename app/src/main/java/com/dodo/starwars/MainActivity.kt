package com.dodo.starwars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0,0,0,"Accueil")
        menu.add(0,1,0,"Personnages")
        menu.add(0,2,0,"Planète")
        menu.add(0,3,0,"Vaisseau")
        return super.onCreateOptionsMenu(menu)
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