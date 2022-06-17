package com.github.wlmxenl.exercise.custom

import com.github.wlmxenl.exercise.common.page.BaseNavHostActivity

class MainActivity : BaseNavHostActivity() {

    override fun getNavGraphResId(): Int {
        return R.navigation.nav_graph
    }

//    override fun getCustomStartDestination(): Int {
//        return R.id.vip_card_list_fragment
//    }

}