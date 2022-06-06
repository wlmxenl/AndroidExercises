package com.github.wlmxenl.exercise.custom.vipcard

import android.os.Bundle
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.github.wlmxenl.exercise.custom.databinding.MultiTouchSampleFragmentBinding
import com.github.wlmxenl.exercise.custom.databinding.VipCardListFragmentBinding

/**
 *
 * @author wangzf
 * @date 2022/2/26
 */
class VipCardListFragment : BaseFragment<VipCardListFragmentBinding>() {

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        appBarView?.setTitle("VipCardList")
    }
}