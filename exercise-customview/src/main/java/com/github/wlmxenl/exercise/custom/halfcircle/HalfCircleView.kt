package com.github.wlmxenl.exercise.custom.halfcircle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.withStyledAttributes
import com.github.wlmxenl.exercise.custom.R

/**
 * 半圆
 * @Author wangzhengfu
 * @Date 2022/2/16
 */
class HalfCircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    companion object {
        private const val UP = 1
        private const val DOWN = 2
        private const val LEFT = 3
        private const val RIGHT = 4
    }

    private var mWidth = 0f
    private var mHeight = 0f
    private var mDirection = 1
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mSolidColor = 0

    private var mStrokeWidth = 0f
    private var mStrokeColor = 0

    private var mEdgeOffset = 2f // 圆弧与边框重合位置向内偏移值，为0时圆弧与边框位置显示区效果在视觉上不够圆滑

    init {
        // 默认宽高
        val defaultWH = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            10f,
            context.resources.displayMetrics
        )

        context.withStyledAttributes(attrs, R.styleable.HalfCircleView) {
            mWidth = getDimension(R.styleable.HalfCircleView_hcv_width, defaultWH)
            mHeight = getDimension(R.styleable.HalfCircleView_hcv_height, defaultWH)
            mDirection = getInt(R.styleable.HalfCircleView_hcv_direction, UP)

            mStrokeWidth = getDimension(R.styleable.HalfCircleView_hcv_stroke_width, mStrokeWidth)
            mSolidColor = getColor(R.styleable.HalfCircleView_hcv_solid_color, Color.BLACK)
            mStrokeColor = getColor(R.styleable.HalfCircleView_hcv_stroke_color, Color.BLACK)
        }

        mPaint.isDither = true
        mPaint.color = mSolidColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mWidth.toInt(), mHeight.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        mPaint.strokeWidth = 0f
        mPaint.color = mSolidColor
        mPaint.style = Paint.Style.FILL

        when (mDirection) {
            UP, DOWN -> {
                canvas.translate(0f, if (mDirection == UP) -mHeight else 0f)
                canvas.drawOval(mEdgeOffset, mEdgeOffset, mWidth - mEdgeOffset, mHeight * 2 - mEdgeOffset, mPaint)
                if (mStrokeWidth > 0) {
                    mPaint.strokeWidth = mStrokeWidth
                    mPaint.color = mStrokeColor
                    mPaint.style = Paint.Style.STROKE
                    canvas.drawOval(mEdgeOffset, mEdgeOffset, mWidth - mEdgeOffset, mHeight * 2 - mEdgeOffset, mPaint)
                }
            }
            LEFT, RIGHT -> {
                canvas.translate(if (mDirection == LEFT) -mWidth else 0f, 0f)
                canvas.drawOval(mEdgeOffset, mEdgeOffset, mWidth * 2 - mEdgeOffset, mHeight - mEdgeOffset, mPaint)
                if (mStrokeWidth > 0) {
                    mPaint.strokeWidth = mStrokeWidth
                    mPaint.color = mStrokeColor
                    mPaint.style = Paint.Style.STROKE
                    canvas.drawOval(mEdgeOffset, mEdgeOffset, mWidth * 2 - mEdgeOffset, mHeight - mEdgeOffset, mPaint)
                }
            }
        }
    }
}