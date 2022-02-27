package com.github.wlmxenl.exercise.custom.toucnevent

import android.os.Bundle
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.github.wlmxenl.exercise.custom.databinding.MultiTouchSampleFragmentBinding

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