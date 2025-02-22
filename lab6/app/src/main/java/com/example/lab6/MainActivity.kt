package com.example.lab6

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var urlInput: EditText
    private lateinit var buttonGo: Button
    private lateinit var buttonBack: Button
    private lateinit var buttonForward: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        urlInput = findViewById(R.id.urlInput)
        buttonGo = findViewById(R.id.buttonGo)
        buttonBack = findViewById(R.id.buttonBack)
        buttonForward = findViewById(R.id.buttonForward)

        setupWebView()

        buttonGo.setOnClickListener {
            val url = urlInput.text.toString().trim()
            if (url.isNotEmpty()) {
                webView.loadUrl(if (url.startsWith("http")) url else "https://$url")
            }
        }

        buttonBack.setOnClickListener {
            if (webView.canGoBack()) webView.goBack()
        }

        buttonForward.setOnClickListener {
            if (webView.canGoForward()) webView.goForward()
        }
    }

    private fun setupWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView.settings.domStorageEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }

        webView.webChromeClient = WebChromeClient()

        // Завантажити останню відвідану сторінку
        val lastUrl = getSharedPreferences("WebBrowser", MODE_PRIVATE).getString("last_url", "https://google.com")
        webView.loadUrl(lastUrl ?: "https://google.com")
    }

    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences("WebBrowser", MODE_PRIVATE).edit()
        prefs.putString("last_url", webView.url)
        prefs.apply()
    }
}