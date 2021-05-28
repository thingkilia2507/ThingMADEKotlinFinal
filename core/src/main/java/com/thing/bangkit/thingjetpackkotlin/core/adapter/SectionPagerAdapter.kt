package com.thing.bangkit.thingjetpackkotlin.core.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: AppCompatActivity, private var pageType : Int= 0) : FragmentStateAdapter(activity) {

    companion object {
        private const val ARG_POSITION_BUNDLE = "BUNDLE_POSITION"
        private const val ARG_PAGE_TYPE = "TYPE_PAGE_IS_FAVORITE"
    }
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment : Fragment = if(pageType == 0) {
            Class.forName("com.thing.bangkit.thingjetpackkotlin.fragment.FilmFragment")
                .newInstance() as Fragment
        }else{
            Class.forName("com.thing.bangkit.thingjetpackkotlin.favorites.fragment.FilmFavFragment")
                .newInstance() as Fragment
        }
        fragment.arguments =  Bundle().apply {
            putInt(ARG_PAGE_TYPE, pageType)
            putInt(ARG_POSITION_BUNDLE, position)
        }
        return fragment
    }

}