package com.example.projectcse227

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity1 : AppCompatActivity() {
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var btnSignIn: Button? = null
    private var btnSignUp: Button? = null
    private var btnResetPassword: Button? = null
    private var progressBar: ProgressBar? = null
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        btnSignIn = findViewById<View>(R.id.sign_in_button) as Button
        btnSignUp = findViewById<View>(R.id.sign_up_button) as Button
        inputEmail = findViewById<View>(R.id.email) as EditText
        inputPassword = findViewById<View>(R.id.password) as EditText
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        btnResetPassword = findViewById<View>(R.id.btn_reset_password) as Button
        btnResetPassword!!.setOnClickListener {
            startActivity(
                Intent(
                    this@SignupActivity1,
                    ResetPasswordActivity1::class.java
                )
            )
        }
        btnSignIn!!.setOnClickListener {
            startActivity(
                Intent(
                    this@SignupActivity1,
                    LoginActivity1::class.java
                )
            )
        }
        btnSignUp!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim { it <= ' ' }
            val password = inputPassword!!.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (password.length < 6) {
                Toast.makeText(
                    applicationContext,
                    "Password too short, enter minimum 6 characters!",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            progressBar!!.visibility = View.VISIBLE
            auth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@SignupActivity1) { task ->
                    Toast.makeText(
                        this@SignupActivity1,
                        "createUserWithEmail:onComplete:" + task.isSuccessful,
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBar!!.visibility = View.GONE
                    if (!task.isSuccessful) {
                        Toast.makeText(
                            this@SignupActivity1,
                            "Authentication failed." + task.exception,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        startActivity(Intent(this@SignupActivity1, LoginActivity1::class.java))
                        finish()
                    }
                }
        })
    }

    override fun onResume() {
        super.onResume()
        progressBar!!.visibility = View.GONE
    }
}