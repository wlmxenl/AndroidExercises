package com.github.wlmxenl.exercise.custom.scaleableiv

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import com.blankj.utilcode.util.ToastUtils
import com.github.wlmxenl.exercise.common.util.BitmapUtil
import com.github.wlmxenl.exercise.custom.R

/**
 *
 * @Author wangzhengfu
 * @Date 2022/2/23
 */
class ScaleableImageView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap = BitmapUtil.decodeResource(context, R.mipmap.test_avatar,
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f, context.resources.displayMetrics).toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var offsetX = 0f
    private var offsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f

    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            Toast.makeText(context, "tap", Toast.LENGTH_LONG).show()
            return true
        }

        override fun onDown(e: MotionEvent): Boolean {
            return false
        }
    })

    init {
        // 禁用长按
        gestureDetector.setIsLongpressEnabled(false)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        offsetX = (w - bitmap.width) / 2f
        offsetY = (h - bitmap.height) / 2f

        // 水平方向平铺
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat()
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val scale = smallScale
        canvas.scale(scale, scale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }
}