package com.github.wlmxenl.exercise.common.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import cn.dripcloud.scaffold.page.BasePageFragment
import cn.dripcloud.scaffold.page.IPageStateView
import com.github.wlmxenl.exercise.common.util.ViewBindingUtil

/**
 * 支持默认顶部应用栏、多状态布局的 Fragment 基类
 * @author wangzf
 * @date 2022/2/13
 */
abstract class BaseFragment<VB : ViewBinding> : BasePageFragment<VB, CommonAppBarLayout>() {

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): VB {
        return ViewBindingUtil.inflateWithGeneric(this, inflater, container, attachToRoot)
    }

    override fun onCreateAppBarView() : View? = CommonAppBarLayout(requireActivity()).apply {
        setBackClickListener {
            requireActivity().onBackPressed()
        }
    }
    override fun onCreatePageStateView(): IPageStateView? = null

    override fun loadData() {
    }
}