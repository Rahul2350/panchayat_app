package com.example.projectcse227.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.projectcse227.R

/**
 * A simple [Fragment] subclass.
 */
class YojanaFragment : Fragment() {
    lateinit var webView: WebView
    var root: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_yojana, container, false)
        webView = root!!.findViewById<View>(R.id.webview1) as WebView
        val webSettings: WebSettings = webView!!.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webView.loadUrl("https://haryana.gov.in/hi/%E0%A4%AF%E0%A5%8B%E0%A4%9C%E0%A4%A8%E0%A4%BE%E0%A4%8F%E0%A4%82/")
        return root
    }
}