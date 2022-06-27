package com.example.customview.xfermode

import android.os.Bundle
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.example.customview.databinding.MultiTouchSampleFragmentBinding
import com.example.customview.databinding.PorterDuffModelFragmentBinding
import com.example.customview.databinding.VipCardListFragmentBinding

/**
 *
 * @author wangzf
 * @date 2022/2/26
 */
class PorterDuffModelFragment : BaseFragment<PorterDuffModelFragmentBinding>() {

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        appBarView?.setTitle("PorterDuff.Mode")
    }
}