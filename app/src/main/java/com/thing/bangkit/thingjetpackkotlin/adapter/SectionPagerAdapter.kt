package com.thing.bangkit.thingjetpackkotlin.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thing.bangkit.thingjetpackkotlin.fragment.FilmFragment

class SectionPagerAdapter(activity: AppCompatActivity, private var pageType : Int= 0) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return FilmFragment.newInstance(position, pageType)
    }


}