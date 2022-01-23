package com.mgs.mgsdashboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.AdapterFragmentRedminer
import kotlinx.android.synthetic.main.activity_red_miner.*

class RedMinerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_red_miner)

        var pager = findViewById<ViewPager2>(R.id.viewPagerRedMiner)
        pager.adapter = AdapterFragmentRedminer(supportFragmentManager,lifecycle)
        circleRedMiner.setViewPager(pager)


        toolbarRedMiner.title = ""
        setSupportActionBar(toolbarRedMiner)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
