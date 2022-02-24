package com.github.wlmxenl.exercise.custom.scaleableiv

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.github.wlmxenl.exercise.common.util.BitmapUtil
import com.github.wlmxenl.exercise.custom.R
import kotlin.math.max
import kotlin.math.min

/**
 *
 * @Author wangzhengfu
 * @Date 2022/2/23
 */
class ScaleableImageView(context: Context, attrs: AttributeSet?) : View(context, attrs), Runnable {
    private val bitmap = BitmapUtil.decodeResource(
        context, R.mipmap.test_avatar,
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f, context.resources.displayMetrics).toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
    private val scroller = OverScroller(context)

    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (currentScale == smallScale) {
                    offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
                    offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
                    scaleAnimator.start()
                } else {
                    scaleAnimator.reverse()
                }
                return true
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                if (currentScale == smallScale) return false

                // 放大状态下左右可滑动距离，不能大于或小于 (图片宽度 - 屏幕宽度) / 2
                offsetX -= distanceX
                offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2f)
                offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2f)

                offsetY -= distanceY
                offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2f)
                offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2f)
                invalidate()
                return true
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                if (currentScale == smallScale) return false

                scroller.fling(
                    offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                    (-(bitmap.width * bigScale - width) / 2f).toInt(),
                    ((bitmap.width * bigScale - width) / 2f).toInt(),
                    (-(bitmap.height * bigScale - height) / 2f).toInt(),
                    ((bitmap.height * bigScale - height) / 2f).toInt()
                )

                postOnAnimation(this@ScaleableImageView)
                return true
            }
        })


    private val scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            // true 返回与上一次比值，false 返回与初始状态比值
            val tmpCurrentScale = currentScale * detector.scaleFactor
            if (tmpCurrentScale < smallScale || tmpCurrentScale > bigScale) {
                return false
            }
            currentScale *= detector.scaleFactor
            return true
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {

        }
    })

    override fun run() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.currX.toFloat()
            offsetY = scroller.currY.toFloat()
            invalidate()
            postOnAnimation(this)
            // ViewCompat.postOnAnimation(this, this)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        originalOffsetX = (w - bitmap.width) / 2f
        originalOffsetY = (h - bitmap.height) / 2f

        // 水平方向平铺
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * 2f
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * 2f
        }

        currentScale = smallScale
        scaleAnimator.setFloatValues(smallScale, bigScale)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        // val scale = smallScale + (bigScale - smallScale) * scaleFraction
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
        // return gestureDetector.onTouchEvent(event)
    }

}