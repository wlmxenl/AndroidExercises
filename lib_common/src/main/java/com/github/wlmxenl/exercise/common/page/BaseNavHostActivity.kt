package com.github.wlmxenl.exercise.common.page

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import cn.dripcloud.scaffold.page.IPageStateView
import com.github.wlmxenl.exercise.common.R
import com.github.wlmxenl.exercise.common.databinding.CommonNavigationContainerBinding

/**
 * Navigation 宿主页面基类
 * @Author wangzhengfu
 * @Date 2022/2/13
 */
abstract class BaseNavHostActivity : BaseActivity<CommonNavigationContainerBinding>() {

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        val navHostFragment  = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController.apply {
            val navGraph = navInflater.inflate(getNavGraphResId()).apply {
                if (getCustomStartDestination() != 0) {
                    setStartDestination(getCustomStartDestination())
                }
            }
            setGraph(navGraph, getCustomStartDestinationArgs())
        }
    }

    abstract fun getNavGraphResId(): Int
    open fun getCustomStartDestination() = 0
    open fun getCustomStartDestinationArgs(): Bundle? = null

    override fun onCreateAppBarView(): View? = null
    override fun onCreatePageStateView(): IPageStateView? = null
}