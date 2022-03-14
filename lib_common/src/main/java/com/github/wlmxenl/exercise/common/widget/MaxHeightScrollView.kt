package com.github.wlmxenl.exercise.common.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

/**
 * @author wangzf
 * @date 2022/3/14
 */
class MaxHeightScrollView(context: Context?, attrs: AttributeSet?) : ScrollView(context, attrs) {
    var maxHeight = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            widthMeasureSpec,
            if (maxHeight > 0)
                MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)
            else
                heightMeasureSpec
        )
    }
}