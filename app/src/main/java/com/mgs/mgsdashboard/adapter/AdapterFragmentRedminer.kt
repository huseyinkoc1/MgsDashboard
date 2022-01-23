package com.mgs.mgsdashboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mgs.mgsdashboard.view.fragment.redmine.FragmentCompletedTeam
import com.mgs.mgsdashboard.view.fragment.redmine.FragmentTaskCompleted
import com.mgs.mgsdashboard.view.fragment.redmine.FragmentOpenedTask

class AdapterFragmentRedminer(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       when(position){
           0 -> return FragmentTaskCompleted ()
           1 -> return FragmentOpenedTask ()
           2 -> return FragmentCompletedTeam ()
           else -> return FragmentTaskCompleted ()
       }
    }


}