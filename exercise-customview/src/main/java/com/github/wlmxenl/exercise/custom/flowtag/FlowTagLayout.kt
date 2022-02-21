package com.github.wlmxenl.exercise.custom.flowtag

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import com.github.wlmxenl.exercise.custom.R
import kotlin.math.max

/**
 *
 * @Author wangzhengfu
 * @Date 2022/2/12
 */
class FlowTagLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val childrenBounds = mutableListOf<Rect>()
    private var itemSpacing = 0

    init {
        context.withStyledAttributes(attrs, R.styleable.FlowTagLayout) {
            itemSpacing = getDimensionPixelSize(R.styleable.FlowTagLayout_ftl_item_spacing, 0)
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = itemSpacing

        var lineWidthUsed = itemSpacing
        var lineMaxHeight = 0

        val widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec)

        for ((index, child) in children.withIndex()) {
            // measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            // 测量模式限制宽高时设置换行
            if (widthMeasureMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth + itemSpacing * 2 > widthMeasureSize) {
                lineWidthUsed = itemSpacing
                heightUsed += (lineMaxHeight + itemSpacing)
                lineMaxHeight = 0
                // 换行后高度变化在测量一次
                // measureChild(child, widthMeasureSpec, heightMeasureSpec)
            }
            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            val childBounds = childrenBounds[index]

            // 子 View 左间距
            val childLeftSpacing = if (lineWidthUsed == itemSpacing) 0 else itemSpacing

            childBounds.set(
                lineWidthUsed + childLeftSpacing,
                heightUsed,
                lineWidthUsed + child.measuredWidth + childLeftSpacing,
                heightUsed + child.measuredHeight)
            lineWidthUsed += child.measuredWidth + childLeftSpacing

            widthUsed = max(widthUsed, lineWidthUsed)
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
        }
        setMeasuredDimension(widthUsed, heightUsed + lineMaxHeight + itemSpacing)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val childBounds = childrenBounds[index]
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
        }
    }

    /**
     * 从布局文件中添加
     * @param attrs
     * @return
     */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    /**
     * 通过 addView 方式添加
     *
     * @return
     */
    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }
}