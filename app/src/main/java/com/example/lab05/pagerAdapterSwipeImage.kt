package com.example.lab05
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class pagerAdapterSwipeImage(fa: FragmentActivity): FragmentStateAdapter(fa) {
    companion object {
        const val PAGE_COUNT = 3 // Replace this with the actual number of tabs/pages
    }

    override fun getItemCount(): Int {
        return PAGE_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        // Return the proper fragment for each position value
        return when (position) {
            0 -> secondTab.newInstance("f1","page 1")
            1 -> secondTab.newInstance("f1","page 2")
            2 -> secondTab.newInstance("f1","page 3")
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}