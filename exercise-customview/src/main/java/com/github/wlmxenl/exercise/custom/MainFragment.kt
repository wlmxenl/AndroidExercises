package com.github.wlmxenl.exercise.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder
import com.github.wlmxenl.exercise.common.navigation.navigate
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.github.wlmxenl.exercise.custom.databinding.MainFragmentBinding
import com.github.wlmxenl.exercise.custom.databinding.MainRecycleItemBinding

/**
 * MainFragment
 * @Author wangzhengfu
 * @Date 2022/2/13
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
            add(Triple("FlowTagLayout", R.id.flow_tag_fragment, "流式标签布局"))
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