package com.mgs.mgsdashboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mgs.mgsdashboard.view.fragment.AvfastFragment
import com.mgs.mgsdashboard.view.fragment.PetnerFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return AvfastFragment ()
            1 -> return PetnerFragment ()
            else -> return AvfastFragment ()
        }
    }


}