package com.mgs.mgsdashboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.AdapterFragmentRedminer
import com.mgs.mgsdashboard.databinding.ActivityRedMinerBinding
import kotlinx.android.synthetic.main.activity_red_miner.*

class RedMinerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRedMinerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedMinerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pager = findViewById<ViewPager2>(R.id.viewPagerRedMiner)
        binding.viewPagerRedMiner.adapter = AdapterFragmentRedminer(supportFragmentManager,lifecycle)
        binding.circleRedMiner.setViewPager(pager)


        binding.toolbarRedMiner.title = ""
        setSupportActionBar(toolbarRedMiner)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
