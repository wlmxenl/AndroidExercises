package com.example.customview.flowtag

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.random.Random

/**
 *
 * @author wangzf
 * @date 2022/2/12
 */
class FlowTagView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var text = ""
    private val textBounds = Rect()
    private val bgCorners: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, context.resources.displayMetrics)

    init {
        paint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18f, context.resources.displayMetrics)
        context.withStyledAttributes(attrs, intArrayOf(android.R.attr.text)) {
            text = getString(0) ?: "Hello World"
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 测量文本宽高
        paint.getTextBounds(text, 0, text.length, textBounds)
        setMeasuredDimension(
            textBounds.width() + paddingStart + paddingRight,
            textBounds.height() + paddingTop + paddingBottom)
    }

    override fun onDraw(canvas: Canvas) {
        // 随机颜色背景
        paint.setARGB(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        paint.style = Paint.Style.FILL
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), bgCorners, bgCorners, paint)
        // 绘制文本
        paint.color = Color.WHITE
        canvas.drawText(text, paddingStart.toFloat(), height.toFloat() - paddingTop, paint)
    }

    fun setText(text: String) {
        this.text = text
        invalidate()
    }
}