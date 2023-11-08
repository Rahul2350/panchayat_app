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
class MahaYojanaFragment : Fragment() {
    var webView: WebView? = null
    var root: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_maha_yojana, container, false)
        webView = root!!.findViewById<View>(R.id.webview) as WebView
        val webSettings: WebSettings = webView!!.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webView!!.loadUrl("https://hrylabour.gov.in/welfare/users/schemeDetailFront/80#:~:text=%E0%A4%87%E0%A4%B8%20%E0%A4%AF%E0%A5%8B%E0%A4%9C%E0%A4%A8%E0%A4%BE%20%E0%A4%95%E0%A5%87%20%E0%A4%85%E0%A4%A8%E0%A5%8D%E0%A4%A4%E0%A4%B0%E0%A5%8D%E0%A4%97%E0%A4%A4%20%E0%A4%B9%E0%A4%B0%E0%A4%BF%E0%A4%AF%E0%A4%BE%E0%A4%A3%E0%A4%BE,%E0%A4%B8%E0%A4%B9%E0%A4%BE%E0%A4%AF%E0%A4%A4%E0%A4%BE%20%E0%A4%AA%E0%A5%8D%E0%A4%B0%E0%A4%A6%E0%A4%BE%E0%A4%A8%20%E0%A4%95%E0%A5%80%20%E0%A4%9C%E0%A4%BE%E0%A4%A4%E0%A5%80%20%E0%A4%B9%E0%A5%88%E0%A5%A4")
        return root
    }
}