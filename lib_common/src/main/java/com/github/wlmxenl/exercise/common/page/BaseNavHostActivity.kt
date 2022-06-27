package com.github.wlmxenl.exercise.common.page

import cn.dripcloud.scaffold.arch.navigation.SimpleNavHostActivity
import com.blankj.utilcode.util.BarUtils

/**
 * Navigation 宿主页面基类
 * @author wangzf
 * @date 2022/2/13
 */
abstract class BaseNavHostActivity : SimpleNavHostActivity() {

    override fun onBeforeSetContentView() {
        super.onBeforeSetContentView()
        BarUtils.transparentStatusBar(this)
    }
}