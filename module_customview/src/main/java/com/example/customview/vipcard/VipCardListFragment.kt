package com.example.customview.vipcard

import android.os.Bundle
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.example.customview.databinding.MultiTouchSampleFragmentBinding
import com.example.customview.databinding.VipCardListFragmentBinding

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