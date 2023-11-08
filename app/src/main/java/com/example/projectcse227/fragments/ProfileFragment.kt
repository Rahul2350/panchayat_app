package com.example.projectcse227.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectcse227.LoginActivity1
import com.example.projectcse227.R
import com.example.projectcse227.SignupActivity1
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private var btnChangeEmail: Button? = null
    private var btnChangePassword: Button? = null
    private var btnSendResetEmail: Button? = null
    private var btnRemoveUser: Button? = null
    private var changeEmail: Button? = null
    private var changePassword: Button? = null
    private var sendEmail: Button? = null
    private var remove: Button? = null
    private var signOut: Button? = null
    private lateinit var oldEmail: EditText
    private lateinit var newEmail: EditText
    private lateinit var password: EditText
    private lateinit var newPassword: EditText
    private var progressBar: ProgressBar? = null
    private var authListener: FirebaseAuth.AuthStateListener? = null
    private var auth: FirebaseAuth? = null
    var Root: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Root = inflater.inflate(R.layout.fragment_profile, container, false)
        auth = FirebaseAuth.getInstance()

        //get current user
        val user: FirebaseUser? = FirebaseAuth.getInstance().getCurrentUser()
        authListener = object : FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
                val user: FirebaseUser? = firebaseAuth.getCurrentUser()
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(Intent(activity, LoginActivity1::class.java))
                }
            }
        }
        btnChangeEmail = Root!!.findViewById<View>(R.id.change_email_button) as Button
        btnChangePassword = Root!!.findViewById<View>(R.id.change_password_button) as Button
        btnSendResetEmail = Root!!.findViewById<View>(R.id.sending_pass_reset_button) as Button
        btnRemoveUser = Root!!.findViewById<View>(R.id.remove_user_button) as Button
        changeEmail = Root!!.findViewById<View>(R.id.changeEmail) as Button
        changePassword = Root!!.findViewById<View>(R.id.changePass) as Button
        sendEmail = Root!!.findViewById<View>(R.id.send) as Button
        remove = Root!!.findViewById<View>(R.id.remove) as Button
        signOut = Root!!.findViewById<View>(R.id.sign_out) as Button
        oldEmail = Root!!.findViewById<View>(R.id.old_email) as EditText
        newEmail = Root!!.findViewById<View>(R.id.new_email) as EditText
        password = Root!!.findViewById<View>(R.id.password) as EditText
        newPassword = Root!!.findViewById<View>(R.id.newPassword) as EditText
        oldEmail!!.setVisibility(View.GONE)
        newEmail!!.setVisibility(View.GONE)
        password!!.setVisibility(View.GONE)
        newPassword!!.setVisibility(View.GONE)
        changeEmail!!.visibility = View.GONE
        changePassword!!.visibility = View.GONE
        sendEmail!!.visibility = View.GONE
        remove!!.visibility = View.GONE
        progressBar = Root!!.findViewById<View>(R.id.progressBar) as ProgressBar
        if (progressBar != null) {
            progressBar!!.visibility = View.GONE
        }
        btnChangeEmail!!.setOnClickListener {
            oldEmail.setVisibility(View.GONE)
            newEmail.setVisibility(View.VISIBLE)
            password.setVisibility(View.GONE)
            newPassword.setVisibility(View.GONE)
            changeEmail!!.visibility = View.VISIBLE
            changePassword!!.visibility = View.GONE
            sendEmail!!.visibility = View.GONE
            remove!!.visibility = View.GONE
        }
        changeEmail!!.setOnClickListener {
            progressBar!!.visibility = View.VISIBLE
            if (user != null && newEmail!!.getText().toString().trim { it <= ' ' } != "") {
                user.updateEmail(newEmail!!.getText().toString().trim { it <= ' ' })
                    .addOnCompleteListener(object : OnCompleteListener<Void?> {
                        override fun onComplete(task: Task<Void?>) {
                            if (task.isSuccessful()) {
                                Toast.makeText(
                                    activity,
                                    "Email address is updated. Please sign in with new email id!",
                                    Toast.LENGTH_LONG
                                ).show()
                                signOut()
                                progressBar!!.visibility = View.GONE
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Your EMAIL has been changed",
                                    Toast.LENGTH_LONG
                                ).show()
                                progressBar!!.visibility = View.GONE
                            }
                        }
                    })
            } else if (newEmail.getText().toString().trim { it <= ' ' } == "") {
                newEmail.setError("Enter email")
                progressBar!!.visibility = View.GONE
            }
        }
        btnChangePassword!!.setOnClickListener {
            oldEmail.setVisibility(View.GONE)
            newEmail.setVisibility(View.GONE)
            password.setVisibility(View.GONE)
            newPassword.setVisibility(View.VISIBLE)
            changeEmail!!.visibility = View.GONE
            changePassword!!.visibility = View.VISIBLE
            sendEmail!!.visibility = View.GONE
            remove!!.visibility = View.GONE
        }
        changePassword!!.setOnClickListener {
            progressBar!!.visibility = View.VISIBLE
            if (user != null && newPassword.getText().toString().trim { it <= ' ' } != "") {
                if (newPassword.getText().toString().trim { it <= ' ' }.length < 6) {
                    newPassword.setError("Password too short, enter minimum 6 characters")
                    progressBar!!.visibility = View.GONE
                } else {
                    user.updatePassword(newPassword.getText().toString().trim { it <= ' ' })
                        .addOnCompleteListener(object : OnCompleteListener<Void?> {
                            override fun onComplete(task: Task<Void?>) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(
                                        activity,
                                        "Password is updated, sign in with new password!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    signOut()
                                    progressBar!!.visibility = View.GONE
                                } else {
                                    Toast.makeText(
                                        activity,
                                        "Failed to update password!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    progressBar!!.visibility = View.GONE
                                }
                            }
                        })
                }
            } else if (newPassword.getText().toString().trim { it <= ' ' } == "") {
                newPassword.setError("Enter password")
                progressBar!!.visibility = View.GONE
            }
        }
        btnSendResetEmail!!.setOnClickListener {
            oldEmail.setVisibility(View.VISIBLE)
            newEmail.setVisibility(View.GONE)
            password.setVisibility(View.GONE)
            newPassword.setVisibility(View.GONE)
            changeEmail!!.visibility = View.GONE
            changePassword!!.visibility = View.GONE
            sendEmail!!.visibility = View.VISIBLE
            remove!!.visibility = View.GONE
        }
        sendEmail!!.setOnClickListener {
            progressBar!!.visibility = View.VISIBLE
            if (oldEmail.getText().toString().trim { it <= ' ' } != "") {
                auth!!.sendPasswordResetEmail(oldEmail.getText().toString().trim { it <= ' ' })
                    .addOnCompleteListener(object : OnCompleteListener<Void?> {
                        override fun onComplete(task: Task<Void?>) {
                            if (task.isSuccessful()) {
                                Toast.makeText(
                                    activity,
                                    "Reset password email is sent!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressBar!!.visibility = View.GONE
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Failed to send reset email!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressBar!!.visibility = View.GONE
                            }
                        }
                    })
            } else {
                oldEmail.setError("Enter email")
                progressBar!!.visibility = View.GONE
            }
        }
        btnRemoveUser!!.setOnClickListener {
            progressBar!!.visibility = View.VISIBLE
            if (user != null) {
                user.delete()
                    .addOnCompleteListener(object : OnCompleteListener<Void?> {
                        override fun onComplete(task: Task<Void?>) {
                            if (task.isSuccessful()) {
                                Toast.makeText(
                                    activity,
                                    "Your profile is deleted:( Create a account now!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(activity, SignupActivity1::class.java))
                                progressBar!!.visibility = View.GONE
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Failed to delete your account!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressBar!!.visibility = View.GONE
                            }
                        }
                    })
            }
        }
        signOut!!.setOnClickListener { signOut() }
        return Root
    }

    //sign out method
    fun signOut() {
        auth?.signOut()
    }

    override fun onResume() {
        super.onResume()
        progressBar!!.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        authListener?.let { auth?.addAuthStateListener(it) }
    }

    override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth?.removeAuthStateListener(authListener!!)
        }
    }
}