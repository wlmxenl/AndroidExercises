package com.example.customview.vipcard

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.github.wlmxenl.exercise.common.util.dp2pxFloat

/**
 *
 * @author wangzf
 * @date 2022/5/31
 */
class VipLevelBottomOverlayView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val cornerRadius = 5f.dp2pxFloat()

    init {
        paint.color = Color.DKGRAY
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        path.reset()
        path.moveTo(0f, cornerRadius)
        path.quadTo(0f, 0f, cornerRadius, 0f)
        path.quadTo(width / 2f, height.toFloat() * 2f, width.toFloat() - cornerRadius, 0f)
        path.quadTo(width.toFloat(), 0f, width.toFloat(), cornerRadius)
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())
        canvas.drawPath(path, paint)
    }
}