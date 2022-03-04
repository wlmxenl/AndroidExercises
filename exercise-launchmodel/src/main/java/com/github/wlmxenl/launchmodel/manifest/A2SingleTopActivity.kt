package com.github.wlmxenl.launchmodel.manifest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils

/**
 * launchMode = singleTop
 * @author wangzf
 * @date 2022/3/4
 */
class A2SingleTopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.hasExtra("disableStartActivity")) {
            return
        }
        startActivity(Intent(this, A3Activity::class.java))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.d("onNewIntent")
    }
}