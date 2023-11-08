package com.example.projectcse227

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class CerActivity : AppCompatActivity() {
    var webView: WebView? = null
    var mtoolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cer)
        mtoolbar = findViewById<View>(R.id.certoolbar) as Toolbar
        mtoolbar!!.title = "Certificate"
        mtoolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        mtoolbar!!.setNavigationOnClickListener {
            startActivity(Intent(this@CerActivity, CerificateActivity1::class.java))
            finish()
        }
        val data = intent.getStringExtra("certificate_url")
        webView = findViewById<View>(R.id.web) as WebView
        webView!!.loadUrl(data!!)
    }
}