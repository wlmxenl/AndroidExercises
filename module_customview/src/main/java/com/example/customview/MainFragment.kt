package com.example.customview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cn.dripcloud.scaffold.arch.navigation.navigate
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.github.wlmxenl.exercise.custom.R
import com.github.wlmxenl.exercise.custom.databinding.MainFragmentBinding
import com.github.wlmxenl.exercise.custom.databinding.MainRecycleItemBinding

/**
 * MainFragment
 * @author wangzf
 * @date 2022/2/13
 */
@Suppress("UNCHECKED_CAST")
class MainFragment : BaseFragment<MainFragmentBinding>() {
    private val binderAdapter = BaseBinderAdapter()

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        appBarView?.run {
            setTitle("Custom View")
            setBackVisibility(View.GONE)
        }

        binding.rvList.run {
            layoutManager = LinearLayoutManager(requireActivity())
        }

        binderAdapter.addItemBinder(MainItemBinder())
        binderAdapter.setOnItemClickListener { adapter, _, position ->
            val itemData = adapter.getItem(position) as Triple<String, Int, String>
            navigate(itemData.second)
        }
        val demoList = mutableListOf<Any>().apply {
            add(Triple("TextView Span", R.id.textview_span_fragment, "TextView -> SpannableString"))
            add(Triple("PorterDuff.Mode", R.id.porter_duff_model_fragment, "Paint.setXfermode(xxx)"))
            add(Triple("VipCard", R.id.vip_card_list_fragment, "YXT VIP CARD"))
            add(Triple("FlowTagLayout", R.id.flow_tag_fragment, "流式标签布局"))
            add(Triple("HalfCircleView", R.id.half_circle_fragment, "半圆"))
            add(Triple("ScaleableImageView", R.id.scaleable_imageview_fragment, "可缩放滑动的 ImageView"))
            add(Triple("SingleTouchView", R.id.single_touch_sample_fragment, "单点触摸View示例"))
            add(Triple("NultiTouchView", R.id.single_touch_sample_fragment, "多点触摸View示例"))
        }
        binding.rvList.adapter = binderAdapter
        binderAdapter.setNewInstance(demoList)
    }

    internal class MainItemBinder : QuickViewBindingItemBinder<Triple<String, Int, String>, MainRecycleItemBinding>() {
        override fun convert(
            holder: BinderVBHolder<MainRecycleItemBinding>,
            data: Triple<String, Int, String>
        ) {
            holder.viewBinding.run {
                tvDemoName.text = data.first
                tvDemoDesc.text = data.third
            }
        }

        override fun onCreateViewBinding(
            layoutInflater: LayoutInflater,
            parent: ViewGroup,
            viewType: Int
        ): MainRecycleItemBinding {
            return MainRecycleItemBinding.inflate(layoutInflater, parent, false)
        }
    }

}