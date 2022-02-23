package com.github.wlmxenl.exercise.custom.scaleableiv

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
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
    private var scaleFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val scaleAnimator: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f)
    }
    private val scroller = OverScroller(context)

    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onDoubleTap(e: MotionEvent?): Boolean {
                if (!scaleAnimator.isRunning && scroller.isFinished) {
                    if (scaleFraction == 0f) {
                        scaleAnimator.start()
                    } else {
                        offsetX = 0f
                        offsetY = 0f
                        scaleAnimator.reverse()
                    }
                }
                return true
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                if (scaleFraction != 1.0f) return false

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
                if (scaleFraction != 1.0f) return false
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
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(offsetX, offsetY)
        val scale = smallScale + (bigScale - smallScale) * scaleFraction
        canvas.scale(scale, scale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

}