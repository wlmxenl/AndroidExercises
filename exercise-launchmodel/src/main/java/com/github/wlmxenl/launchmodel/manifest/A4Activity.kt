package com.github.wlmxenl.launchmodel.manifest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.wlmxenl.launchmodel.Constants
import com.github.wlmxenl.launchmodel.R

/**
 *
 * @author wangzf
 * @date 2022/3/4
 */
class A4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a4_activity)
    }

    fun onClickStartA2Activity(view: View) {
        startActivity(Intent(this, Constants.current_a2_activity_clazz).apply {
            putExtra("disableStartActivity", true)
        })
    }
}