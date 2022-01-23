package com.mgs.mgsdashboard.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.databinding.ActivityMainPageBinding
import kotlinx.android.synthetic.main.activity_main_page.*
import java.text.SimpleDateFormat
import java.util.*

class MainPage : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)



        auth = FirebaseAuth.getInstance()

        binding.buttonLogOut.setOnClickListener{
            Toast.makeText(applicationContext,"Logged Out: ${auth.currentUser?.email.toString()}",
                Toast.LENGTH_LONG).show()
            auth.signOut()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.frameRedMiner.setOnClickListener {
            val intent = Intent(baseContext, RedMinerActivity::class.java)
            //finish()
            startActivity(intent)
        }

        binding.frameMobileApp.setOnClickListener {
            val intent = Intent(baseContext, MobileAppActivity::class.java)
            //finish()
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        val date = SimpleDateFormat("dd / MM / yyyy")
        val currentDate = date.format(Date())

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        binding.textViewDate.text = currentDate+", $hour:$minute"

    }

}