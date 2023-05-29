package com.example.spotifyactividad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.commit {
            replace<InicioFragment>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navMenu)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.searchFragment -> {
                    val listasFragment = ListasFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameContainer, listasFragment)
                        .commit()
                    true

                }

                else -> false
            }
        }





    }
}