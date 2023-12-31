package com.example.projectcse227

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity1 : AppCompatActivity() {
    private var inputEmail: EditText? = null
    private var btnReset: Button? = null
    private var btnBack: Button? = null
    private var auth: FirebaseAuth? = null
    private var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        inputEmail = findViewById<View>(R.id.email) as EditText
        btnReset = findViewById<View>(R.id.btn_reset_password) as Button
        btnBack = findViewById<View>(R.id.btn_back) as Button
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        auth = FirebaseAuth.getInstance()
        btnBack!!.setOnClickListener { finish() }
        btnReset!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(application, "Enter your registered email id", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            progressBar!!.visibility = View.VISIBLE
            auth!!.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@ResetPasswordActivity1,
                        "We have sent you instructions to reset your password!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@ResetPasswordActivity1,
                        "Failed to send reset email!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                progressBar!!.visibility = View.GONE
            }
        })
    }
}