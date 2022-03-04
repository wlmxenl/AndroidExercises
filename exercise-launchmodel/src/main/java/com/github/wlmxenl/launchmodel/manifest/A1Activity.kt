package com.github.wlmxenl.launchmodel.manifest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.github.wlmxenl.launchmodel.Constants
import com.github.wlmxenl.launchmodel.R

/**
 *
 * @author wangzf
 * @date 2022/3/4
 */
class A1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a1_activity)


        val srLaunchModel = findViewById<Spinner>(R.id.sr_launch_model)
        srLaunchModel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Constants.current_a2_activity_clazz = when (position) {
                    0 -> A2StandardActivity::class.java
                    1 -> A2SingleTopActivity::class.java
                    2 -> A2SingleTaskActivity::class.java
                    else -> A2SingleTaskActivity::class.java
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun onClickStartActivity(view: View) {
        startActivity(Intent(this, Constants.current_a2_activity_clazz))
    }
}