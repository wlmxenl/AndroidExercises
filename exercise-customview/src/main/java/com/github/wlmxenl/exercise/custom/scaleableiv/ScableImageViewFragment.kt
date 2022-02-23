package com.github.wlmxenl.exercise.custom.scaleableiv

import android.os.Bundle
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.github.wlmxenl.exercise.custom.databinding.ScaleableImageviewFragmentBinding

/**
 *
 * @Author wangzhengfu
 * @Date 2022/2/23
 */class ScableImageViewFragment : BaseFragment<ScaleableImageviewFragmentBinding>() {

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        appBarView?.setTitle("ScaleableImageView")
    }
}