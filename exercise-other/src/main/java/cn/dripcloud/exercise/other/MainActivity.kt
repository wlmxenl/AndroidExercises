package cn.dripcloud.exercise.other

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.dripcloud.exercise.other.lottie.LottieSampleActivity
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.github.wlmxenl.exercise.common.widget.MaxHeightScrollView
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, LottieSampleActivity::class.java))
    }
}