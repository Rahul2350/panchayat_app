package com.example.projectcse227

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ETaxActivity1 : AppCompatActivity() {
    var mtoolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etax)
        mtoolbar = findViewById<View>(R.id.etaxtoolbar) as Toolbar
        mtoolbar!!.title = "E-Tax"
        mtoolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        mtoolbar!!.setNavigationOnClickListener {
            startActivity(Intent(this@ETaxActivity1, DashboardActivity1::class.java))
            finish()
        }
    }
}