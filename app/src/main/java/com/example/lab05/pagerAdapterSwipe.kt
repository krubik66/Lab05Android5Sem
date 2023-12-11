package com.example.lab05
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class pagerAdapterSwipe(fa: FragmentActivity): FragmentStateAdapter(fa) {
    companion object {
        const val PAGE_COUNT = 1 // Replace this with the actual number of tabs/pages
    }

    override fun getItemCount(): Int {
        return PAGE_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        // Return the proper fragment for each position value
        return when (position) {
            0 -> firstTab.newInstance("f1","page 1")
//            1 -> secondTab.newInstance("f2","page 2")
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}