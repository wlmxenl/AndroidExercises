package cn.dripcloud.exercise.other

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 *
 * @author wangzf
 * @date 2022/4/26
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}