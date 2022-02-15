package com.github.wlmxenl.exercise.common.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.github.wlmxenl.exercise.common.R

/**
 * Navigation 扩展, 添加默认动画支持
 * @Author wangzhengfu
 * @Date 2022/2/13
 */

fun Fragment.navigate(@IdRes actionId: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
    val navController = findNavController()
    var destNavOptions = navOptions
    // 从 navigation 配置中
    if (destNavOptions == null) {
        destNavOptions = navController.currentDestination?.getAction(actionId)?.navOptions
    }
    if (destNavOptions == null) {
        destNavOptions = NavOptions.Builder().build()
    }
    // 修改动画配置
    val newNavOptions = destNavOptions.let {
        navOptions {
            // 添加默认跳转动画
            if (it.enterAnim == -1 && it.exitAnim == -1 && it.popEnterAnim == -1 && it.popExitAnim == -1) {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }
            launchSingleTop = it.shouldLaunchSingleTop()
            popUpTo(it.popUpToId) {
                inclusive = it.isPopUpToInclusive()
            }
        }
    }
    navController.navigate(actionId, bundle, newNavOptions)
}
