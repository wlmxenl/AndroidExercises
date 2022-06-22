package com.example.customview.toucnevent

import android.os.Bundle
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.github.wlmxenl.exercise.custom.databinding.SingleTouchSampleFragmentBinding

/**
 *
 * @author wangzf
 * @date 2022/2/26
 */
class SingleTouchViewFragment : BaseFragment<SingleTouchSampleFragmentBinding>() {

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        appBarView?.setTitle("单点触摸示例")
    }
}