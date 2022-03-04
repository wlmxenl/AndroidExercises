package com.github.wlmxenl.launchmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.*
import com.github.wlmxenl.launchmodel.manifest.A1Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickTypeManifest(view: View) {
        startActivity(Intent(this, A1Activity::class.java))
    }

    fun onClickTypeActivityFlags(view: View) {
        LogUtils.d("--")

    }
}