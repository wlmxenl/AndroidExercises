package com.example.customview.scaleableiv

import android.os.Bundle
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.example.customview.databinding.ScaleableImageviewFragmentBinding

/**
 *
 * @author wangzf
 * @date 2022/2/23
 */class ScableImageViewFragment : BaseFragment<ScaleableImageviewFragmentBinding>() {

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        appBarView?.setTitle("ScaleableImageView")
    }
}