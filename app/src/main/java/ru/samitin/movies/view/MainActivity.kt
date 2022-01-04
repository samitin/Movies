package ru.samitin.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.samitin.movies.R
import ru.samitin.movies.databinding.MainActivityBinding
import ru.samitin.movies.view.fragments.FavoriteFragment
import ru.samitin.movies.view.fragments.HomeFragment
import ru.samitin.movies.view.fragments.RatingsFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding:MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
          showFragment(HomeFragment.newInstance())
        }
        binding.buttonNavigationHome.setOnClickListener {
            showFragment(HomeFragment.newInstance())
        }
        binding.buttonNavigationFavorite.setOnClickListener {
            showFragment(FavoriteFragment.newInstance())
        }
        binding.buttonNavigationReting.setOnClickListener {
            showFragment(RatingsFragment.newInstance())
        }
    }

    fun showFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commitNow()
    }

}