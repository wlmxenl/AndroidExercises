package com.example.customview.halfcircle

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
 * @author wangzf
 * @date 2022/2/16
 */
class HalfCircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    companion object {
        private const val UP = 1
        private const val DOWN = 2
        private const val LEFT = 3
        private const val RIGHT = 4
    }

    private var mDirection = 1
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mSolidColor = 0
    private var mStrokeWidth = 0f
    private var mStrokeColor = 0
    private var mEdgeOffset = 2f // 圆弧与边框重合位置向内偏移值，为0时圆弧与边框位置显示区效果在视觉上不够圆滑

    private val mDefaultWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        56f, context.resources.displayMetrics).toInt()

    init {
        context.withStyledAttributes(attrs, R.styleable.HalfCircleView) {
            mDirection = getInt(R.styleable.HalfCircleView_hcv_direction, UP)
            mStrokeWidth = getDimension(R.styleable.HalfCircleView_hcv_stroke_width, mStrokeWidth)
            mSolidColor = getColor(R.styleable.HalfCircleView_hcv_solid_color, Color.BLACK)
            mStrokeColor = getColor(R.styleable.HalfCircleView_hcv_stroke_color, Color.BLACK)
        }

        mPaint.isDither = true
        mPaint.color = mSolidColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var finalWidth = MeasureSpec.getSize(widthMeasureSpec)
        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
            finalWidth = mDefaultWidth
        }
        var finalHeight = MeasureSpec.getSize(heightMeasureSpec)
        if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
            finalHeight = finalWidth / 2
        }
        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onDraw(canvas: Canvas) {
        mPaint.strokeWidth = 0f
        mPaint.color = mSolidColor
        mPaint.style = Paint.Style.FILL

        when (mDirection) {
            UP, DOWN -> {
                canvas.translate(0f, if (mDirection == UP) -height.toFloat() else 0f)
                canvas.drawOval(mEdgeOffset, mEdgeOffset, width.toFloat() - mEdgeOffset, height * 2f - mEdgeOffset, mPaint)

                if (mStrokeWidth > 0) {
                    mPaint.strokeWidth = mStrokeWidth
                    mPaint.color = mStrokeColor
                    mPaint.style = Paint.Style.STROKE

                    canvas.drawOval(
                        mEdgeOffset + mStrokeWidth / 2,
                        mEdgeOffset + mStrokeWidth / 2,
                        width.toFloat() - mStrokeWidth / 2,
                        height * 2f - mEdgeOffset - mStrokeWidth / 2,
                        mPaint)
                }
            }
            LEFT, RIGHT -> {
                canvas.translate(if (mDirection == LEFT) -width.toFloat() else 0f, 0f)
                canvas.drawOval(mEdgeOffset, mEdgeOffset, width * 2f - mEdgeOffset, height.toFloat() - mEdgeOffset, mPaint)

                if (mStrokeWidth > 0) {
                    mPaint.strokeWidth = mStrokeWidth
                    mPaint.color = mStrokeColor
                    mPaint.style = Paint.Style.STROKE

                    canvas.drawOval(
                        mEdgeOffset + mStrokeWidth / 2,
                        mEdgeOffset + mStrokeWidth / 2,
                        width * 2f - mEdgeOffset - mStrokeWidth / 2f,
                        height.toFloat() - mEdgeOffset - mStrokeWidth / 2f,
                        mPaint)
                }
            }
        }
    }
}