package com.example.customview.toucnevent

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import com.github.wlmxenl.exercise.common.util.BitmapUtil
import com.github.wlmxenl.exercise.custom.R

/**
 * 单点触摸 View 示例
 * @author wangzf
 * @date 2022/2/26
 */
class SingleTouchView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = BitmapUtil.decodeResource(context, R.mipmap.test_avatar,
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, context.resources.displayMetrics).toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = (event.x - downX + originalOffsetX)
                    .coerceAtLeast(0f)
                    .coerceAtMost((width - bitmap.width).toFloat())
                offsetY = (event.y - downY + originalOffsetY)
                    .coerceAtLeast(0f)
                    .coerceAtMost((height - bitmap.height).toFloat())
                invalidate()
            }
        }
        return true
    }
}