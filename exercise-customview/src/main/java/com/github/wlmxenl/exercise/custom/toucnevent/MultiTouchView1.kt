package com.github.wlmxenl.exercise.custom.toucnevent

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
 * 多点触摸 View 示例
 *
 * 1. 最新按下的手指具有控制权
 *
 * @author wangzf
 * @date 2022/2/26
 */
class MultiTouchView1(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = BitmapUtil.decodeResource(context, R.mipmap.test_avatar,
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, context.resources.displayMetrics).toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var trackingPointId = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointId = event.getPointerId(0)
                downX = event.x
                downY = event.y
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                // 非第一根手指按下的下标
                val actionIndex = event.actionIndex
                trackingPointId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }

            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                // 当抬起的手指是最后一次按下的手指，移交控制权给指定手指
                if (pointerId == trackingPointId) {
                    // 排除自己 (此时获取的事件还包括正在抬起的手指事件)
                    val newIndex = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    trackingPointId = event.getPointerId(newIndex)
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)
                    originalOffsetX = offsetX
                    originalOffsetY = offsetY
                }
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