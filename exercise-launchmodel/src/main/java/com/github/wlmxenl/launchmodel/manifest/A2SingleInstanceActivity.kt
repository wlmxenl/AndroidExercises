package com.github.wlmxenl.launchmodel.manifest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * launchMode = singleInstance
 * @author wangzf
 * @date 2022/3/4
 */
class A2SingleInstanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("disableStartActivity")) {
            return
        }
        startActivity(Intent(this, A3Activity::class.java))
    }
}