package com.santos.marvel

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.santos.marvel.fragment.FavoritesFragment
import com.santos.marvel.fragment.MarvelHomeFragment

class HomeActivity : AppCompatActivity() {

    val marvelhomefragment = MarvelHomeFragment()

        @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout,marvelhomefragment)
                .commitNow()
        }

        val favoritesButton = findViewById<Button>(R.id.buttonFavorites)
        val returnButton = findViewById<Button>(R.id.buttonReturn)

        favoritesButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, FavoritesFragment.newInstance(marvelhomefragment.homeViewModel))
                .addToBackStack(null)
                .commit()
            }

            returnButton.setOnClickListener {
                supportFragmentManager.popBackStack()
            }

            supportFragmentManager.addOnBackStackChangedListener {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    favoritesButton.visibility = Button.GONE
                    returnButton.visibility = Button.VISIBLE
                } else {
                    favoritesButton.visibility = Button.VISIBLE
                    returnButton.visibility = Button.GONE
            }
       }
    }
}
