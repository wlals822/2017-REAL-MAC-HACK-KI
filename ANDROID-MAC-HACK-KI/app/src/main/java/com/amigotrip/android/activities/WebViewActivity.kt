package com.amigotrip.android.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.amigotrip.anroid.R
import kotlinx.android.synthetic.main.layout_webview.*

/**
 * Created by Zimincom on 2017. 10. 29..
 */
class WebViewActivity : AppCompatActivity(){

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_webview)

        //check internet connection
        webview.loadUrl("http://www.amigotrip.co.kr")
        val webSettings = webview.settings
        webSettings.javaScriptEnabled = true
    }
}