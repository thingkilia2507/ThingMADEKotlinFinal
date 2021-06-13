package com.thing.bangkit.thingmadekotlinfinal.favorites.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.thing.bangkit.thingmadekotlinfinal.core.adapter.SectionPagerAdapter
import com.thing.bangkit.thingmadekotlinfinal.favorites.R
import com.thing.bangkit.thingmadekotlinfinal.favorites.databinding.ActivityFavoriteBinding
import com.thing.bangkit.thingmadekotlinfinal.favorites.di.favoritesModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoritesModule)

        supportActionBar?.title = "My Favorite"
        runActivity()


    }

    private fun runActivity() {
        supportActionBar?.elevation = 0f
        val sectionPagerAdapter = SectionPagerAdapter(this, 1)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(com.thing.bangkit.thingmadekotlinfinal.R.string.movies)
                1 -> tab.text = getString(com.thing.bangkit.thingmadekotlinfinal.R.string.tvshows)
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_fav -> {
                onBackPressed()

            }
        }
        return super.onOptionsItemSelected(item)
    }


}