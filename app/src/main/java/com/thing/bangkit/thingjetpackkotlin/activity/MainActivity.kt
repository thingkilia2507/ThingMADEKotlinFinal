package com.thing.bangkit.thingjetpackkotlin.activity

import android.content.Intent
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.core.adapter.SectionPagerAdapter
import com.thing.bangkit.thingjetpackkotlin.core.helper.Utility.checkNetworkConnection
import com.thing.bangkit.thingjetpackkotlin.databinding.ActivityMainBinding
import com.thing.bangkit.thingjetpackkotlin.databinding.NetworkLostViewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var networkLostBinding: NetworkLostViewBinding
    private var checkNetworkCapabilities: NetworkCapabilities? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkNetworkCapabilities = checkNetworkConnection(this)
        if (checkNetworkCapabilities == null) {
            networkLostBinding = NetworkLostViewBinding.inflate(layoutInflater)
            setContentView(networkLostBinding.root)
            networkLostBinding.btnReload.setOnClickListener {
                checkNetworkCapabilities = checkNetworkConnection(this)
                if (checkNetworkCapabilities != null) {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        } else {
            runMainAct()
        }

    }

    private fun runMainAct() {
        supportActionBar?.elevation = 0f
        val sectionPagerAdapter = SectionPagerAdapter(this,  0)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.movies)
                1 -> tab.text = getString(R.string.tvshows)
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
                val uri = Uri.parse("favoriteapp://favorites")
                startActivity(Intent(Intent.ACTION_VIEW, uri))

            }
        }
        return super.onOptionsItemSelected(item)
    }
}