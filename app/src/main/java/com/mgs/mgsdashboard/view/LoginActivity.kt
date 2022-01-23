package com.mgs.mgsdashboard.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, MainPage::class.java)
            startActivity(intent)
            finish()
        }

        loginConstraintLayout.setOnClickListener {
            hideSoftKeyboard()
        }

        buttonLogin.setOnClickListener{

            val userEmail = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (userEmail.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        //Signed In
                        Toast.makeText(applicationContext,"Welcome: ${auth.currentUser?.email.toString()}",
                            Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, MainPage::class.java)
                        startActivity(intent)
                        finish()

                    }

                }.addOnFailureListener { exception ->
                    val sb =  Snackbar.make(it,exception.localizedMessage,Snackbar.LENGTH_LONG)
                    sb.setTextColor(Color.parseColor("#87001D"))//Mesaj Rengi
                    sb.setBackgroundTint(Color.WHITE)
                    sb.show()
                }
            }
            else{
                val sb =  Snackbar.make(it,"Enter email and password!",Snackbar.LENGTH_LONG)
                sb.setTextColor(Color.parseColor("#87001D"))//Mesaj Rengi
                sb.setBackgroundTint(Color.WHITE)
                sb.show()
            }
        }
    }


    fun Activity.hideSoftKeyboard() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}