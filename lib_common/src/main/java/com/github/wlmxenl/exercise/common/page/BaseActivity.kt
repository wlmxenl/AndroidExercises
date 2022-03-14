package com.github.wlmxenl.exercise.common.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import cn.dripcloud.scaffold.page.BasePageActivity
import cn.dripcloud.scaffold.page.IPageStateView
import com.blankj.utilcode.util.BarUtils
import com.github.wlmxenl.exercise.common.util.ViewBindingUtil

/**
 * 支持默认顶部应用栏、多状态布局的 Activity 基类
 * @author wangzf
 * @date 2022/2/13
 */
abstract class BaseActivity<VB : ViewBinding> : BasePageActivity<VB, CommonAppBarLayout>() {

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): VB {
        return ViewBindingUtil.inflateWithGeneric(this, inflater)
    }

    override fun onBeforeSetContentView() {
        BarUtils.transparentStatusBar(this)
    }

    override fun onCreateAppBarView() : View? = CommonAppBarLayout(this).apply {
        setBackClickListener {
            onBackPressed()
        }
    }
    override fun onCreatePageStateView(): IPageStateView? = null

    override fun loadData() {
    }
}