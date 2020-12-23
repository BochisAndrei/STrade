package com.example.contrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private var sharedP = "SHARED_USER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        //verify if an account already exist
        if (mAuth.currentUser != null) {
            finish()
        }

        var backButton = findViewById<ImageView>(R.id.register_layout_back_button)
        backButton.setOnClickListener {
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            finish()
        }

        var loginButton = findViewById<Button>(R.id.register_layout_login)
        loginButton.setOnClickListener {
            var signUpIntent = Intent(this, LoginActivity::class.java)
            startActivity(signUpIntent)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            finish()
        }
        var registerButton = findViewById<Button>(R.id.register_layout_register)
        var userName = findViewById<EditText>(R.id.register_layout_edtName)
        var email = findViewById<EditText>(R.id.register_layout_edtEmail)
        var pass = findViewById<EditText>(R.id.register_layout_edtPassword)
        var passConfirm = findViewById<EditText>(R.id.register_layout_edtConfirmPassword)
        var error = findViewById<TextView>(R.id.register_layout_errorLogin)

        //sign up with email and password
        registerButton.setOnClickListener {
            if (userName.text.toString().isEmpty()) error.text = "You need to specify your name!"
            else if (email.text.toString().isEmpty()) error.text =
                "You need to specify your email address!"
            else if (pass.text.toString().isEmpty()) error.text = "Please specify your password"
            else if (pass.text.toString() != passConfirm.text.toString()) error.text =
                "Your confirmation password does not corespond with your password!"
            else if (pass.text.toString().length < 6 || pass.text.toString().length > 30) error.text =
                "Your password must be between 6 and 30 characters"
            else {
                error.text = ""
                mAuth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            saveUser(username = userName.text.toString())
                            // Sign in success, update UI with the signed-in user's information
                            val user = mAuth.currentUser
                            Toast.makeText(this, "Authentication successful.", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                this, "Authentication failed."+task.exception+""+email.text.toString(), Toast.LENGTH_SHORT
                            ).show()
                        }

                        // ...
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