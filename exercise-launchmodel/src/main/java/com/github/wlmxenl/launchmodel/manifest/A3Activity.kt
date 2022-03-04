package com.github.wlmxenl.launchmodel.manifest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author wangzf
 * @date 2022/3/4
 */
class A3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, A4Activity::class.java))
    }
}