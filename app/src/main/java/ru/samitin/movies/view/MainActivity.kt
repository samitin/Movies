package ru.samitin.movies.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.samitin.movies.R
import ru.samitin.movies.databinding.MainActivityBinding
import ru.samitin.movies.view.fragments.FavoriteFragment
import ru.samitin.movies.view.fragments.HomeFragment
import ru.samitin.movies.view.fragments.RatingsFragment
const val IS_ADULT_CONTENT="IS_ADULT_CONTENT"
class MainActivity : AppCompatActivity() {

    lateinit var binding:MainActivityBinding
    private var isAdultContent=false
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
        isAdultContent=getPreferences(Context.MODE_PRIVATE).getBoolean(IS_ADULT_CONTENT,false)

    }
    private fun saveAdultContent() {
            with(getPreferences(Context.MODE_PRIVATE).edit()){
                putBoolean(IS_ADULT_CONTENT,isAdultContent)
                apply()
            }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.add(1,1,1,"взрослый контент")
            ?.setCheckable(true)?.setChecked(isAdultContent)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            1 -> {
                if (item.isChecked){
                    Toast.makeText(this,"checked",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"not checked",Toast.LENGTH_SHORT).show()
                }
                item.setChecked(!item.isChecked)
                isAdultContent=item.isChecked
                saveAdultContent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commitNow()
    }

}