package cn.dripcloud.exercise.other

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.github.wlmxenl.exercise.common.widget.MaxHeightScrollView
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val maxHeightScrollView = findViewById<MaxHeightScrollView>(R.id.max_height_scrollview)
        maxHeightScrollView.maxHeight = (ScreenUtils.getAppScreenHeight() * 0.6f).toInt()
    }
}