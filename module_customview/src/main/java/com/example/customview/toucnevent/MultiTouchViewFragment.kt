package com.example.customview.toucnevent

import android.os.Bundle
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.example.customview.databinding.MultiTouchSampleFragmentBinding

/**
 *
 * @author wangzf
 * @date 2022/2/26
 */
class MultiTouchViewFragment : BaseFragment<MultiTouchSampleFragmentBinding>() {

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        appBarView?.setTitle("多点触摸示例")
    }
}