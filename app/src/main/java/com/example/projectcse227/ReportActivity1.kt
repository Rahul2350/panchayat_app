package com.example.projectcse227

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ReportActivity1 : AppCompatActivity() {
    var mtoolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        mtoolbar = findViewById<View>(R.id.reporttoolbar) as Toolbar
        mtoolbar!!.title = "Report"
        mtoolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        mtoolbar!!.setNavigationOnClickListener {
            startActivity(Intent(this@ReportActivity1, DashboardActivity1::class.java))
            finish()
        }
        val webView = findViewById<View>(R.id.webreport) as WebView
        webView.loadUrl("https://planningonline.gov.in/ReportData.do?ReportMethod=getAnnualPlanReport")
    }
}