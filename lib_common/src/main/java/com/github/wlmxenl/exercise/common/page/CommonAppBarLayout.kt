package com.github.wlmxenl.exercise.common.page

import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.view.View
import android.widget.RelativeLayout
import com.blankj.utilcode.util.BarUtils
import com.github.wlmxenl.exercise.common.R
import com.github.wlmxenl.exercise.common.databinding.CommonAppbarLayoutBinding

/**
 *
 * @Author wangzhengfu
 * @Date 2022/2/13
 */
class CommonAppBarLayout(context: Context?) : RelativeLayout(context) {
    private val binding: CommonAppbarLayoutBinding

    init {
        setBackgroundColor(Color.parseColor("#455A64"))
        setPadding(0, BarUtils.getStatusBarHeight(), 0, 0)
        binding = inflate(context, R.layout.common_appbar_layout, this).let {
            CommonAppbarLayoutBinding.bind(it)
        }
    }

    fun setTitle(text: String?) {
        binding.tvAppBarTitle.text = text
    }

    fun setBackClickListener(listener: View.OnClickListener?) {
        binding.ivAppBarBack.setOnClickListener(listener)
    }

    fun setBackVisibility(visibility: Int) {
        binding.ivAppBarBack.visibility = visibility
    }
}