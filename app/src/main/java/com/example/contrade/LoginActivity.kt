package com.example.contrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    lateinit var mAuth : FirebaseAuth
    private var sharedP = "SHARED_USER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        //verify if an account already exist
        if(mAuth.currentUser != null) {
            finish()
        }

        var backButton = findViewById<ImageView>(R.id.login_layout_back_button)
        backButton.setOnClickListener {
            var mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            finish()
        }

        val registerButton = findViewById<Button>(R.id.login_layout_register)
        registerButton.setOnClickListener {
            var signUpIntent = Intent(this, RegisterActivity::class.java)
            startActivity(signUpIntent)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            finish()
        }

        var loginButton = findViewById<Button>(R.id.login_layout_login)
        var userName = findViewById<EditText>(R.id.login_layout_edtName)
        var email = findViewById<EditText>(R.id.login_layout_edtEmail)
        var pass = findViewById<EditText>(R.id.login_layout_edtPassword)
        var error = findViewById<TextView>(R.id.login_layout_errorLogin)

        loginButton.setOnClickListener {
            if (userName.text.toString().isEmpty()) error.text = "You need to specify your name!"
            else if (email.text.toString().isEmpty()) error.text =
                "You need to specify your email address!"
            else if (pass.text.toString().isEmpty()) error.text = "Please specify your password"
            else {
                error.text = ""

                //signIn the user in firebase
                mAuth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener(
                        this
                    ) { task ->
                        if (task.isSuccessful) {
                            saveUser(username = userName.text.toString())
                            // Sign in success, update UI with the signed-in user's information
                            val user = mAuth.currentUser
                            Toast.makeText(
                                this, "Authentication successful.",
                                Toast.LENGTH_SHORT
                            ).show()
                            var mainIntent = Intent(this, MainActivity::class.java)
                            startActivity(mainIntent)
                            overridePendingTransition(
                                R.anim.slide_in_left,
                                R.anim.slide_out_right
                            )
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                this, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    //save the user in sharedPreferences
    fun saveUser(username : String){
        var sharedPreferences : SharedPreferences = getSharedPreferences(sharedP, Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("USERNAME", username)
        editor.apply()
    }
}