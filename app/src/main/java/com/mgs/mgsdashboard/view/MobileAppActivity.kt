package com.mgs.mgsdashboard.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mgs.mgsdashboard.adapter.AdapterFragmentApp
import android.widget.LinearLayout
import android.graphics.drawable.GradientDrawable
import android.view.View
import com.mgs.mgsdashboard.R
import kotlinx.android.synthetic.main.activity_mobile_app.*
import kotlinx.android.synthetic.main.activity_red_miner.toolbarRedMiner


class MobileAppActivity : AppCompatActivity() {


    var tabText = arrayOf("AvFast","Petner")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_app)

        toolbarMobileApp.title = ""
        setSupportActionBar(toolbarMobileApp)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        var pager = findViewById<ViewPager2>(R.id.viewPagerMain)
        var tabLayout  = findViewById<TabLayout>(R.id.tabLayout)
        pager.adapter = AdapterFragmentApp(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout,pager){
            tab,position ->
            tab.text = tabText[position]
        }.attach()

        pager.setUserInputEnabled(false)


        // TabLayout Divider
        val root: View = tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            (root as LinearLayout).showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.dividerColor))
            drawable.setSize(2, 1)
            (root as LinearLayout).dividerPadding = 10
            (root as LinearLayout).dividerDrawable = drawable
        }


        //TabLayout Splash
        pager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0)
                {
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#EEA320"))
                    tabLayout.setTabTextColors((Color.parseColor("#C2C2C2")), (Color.parseColor("#404041")))

                }
                else if (position == 1)
                {
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#691296"))
                    tabLayout.setTabTextColors((Color.parseColor("#C3C3C3")), (Color.parseColor("#691296")))
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}