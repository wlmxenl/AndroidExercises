package com.example.customview.vipcard

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.blankj.utilcode.util.ColorUtils
import com.github.wlmxenl.exercise.common.util.dp2pxFloat
import com.github.wlmxenl.exercise.common.util.dp2pxInt
import com.github.wlmxenl.exercise.common.util.sp2pxFloat
import com.github.wlmxenl.exercise.custom.R

/**
 *
 * @author wangzf
 * @date 2022/6/10
 */
class YxCouponView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var strokeWidth = 1.5f.dp2pxFloat()
    private var cornerRadius = 8f.dp2pxFloat()
    private var strokeColor = ColorUtils.string2Int("#FEC1C1")
    private var leftTextBgColor = ColorUtils.string2Int("#FFEEEE")

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var leftText = "送"
    private var rightText = "优惠券0张"
    private var textVerticalPadding = 14.5f.dp2pxInt() // 文本上下间距
    private var leftTextHorizontalPadding = 13f.dp2pxInt() // 左侧文本左右间距
    private var rightTextHorizontalPadding = 14.5f.dp2pxInt() // 右侧文本左右间距

    private var textSize = 24f.sp2pxFloat()
    private var textColor = ColorUtils.string2Int("#FC5758")
    private var leftTextWidth = 0
    private var rightTextWidth = 0

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textBoundRect = Rect()
    private var dashPathEffect: DashPathEffect? = null

    private var gapHalfHeight = 0f
    private var gapWidth = 0f
    private lateinit var outsideGapBitmap: Bitmap
    private lateinit var insideGapBitmap: Bitmap
    private val gapPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        // 关闭硬件加速, 解决 PorterDuff.Mode 透明区域变黑
        setLayerType(LAYER_TYPE_HARDWARE, null)

        context.withStyledAttributes(attrs, R.styleable.YxCouponView) {
            strokeWidth = getDimension(R.styleable.YxCouponView_yxc_stroke_width, strokeWidth)
            cornerRadius = getDimension(R.styleable.YxCouponView_yxc_corner_radius, cornerRadius)
            strokeColor = getColor(R.styleable.YxCouponView_yxc_stroke_color, strokeColor)
            leftTextBgColor = getColor(R.styleable.YxCouponView_yxc_left_bg_color, leftTextBgColor)
            leftText = getString(R.styleable.YxCouponView_yxc_left_text) ?: leftText
            rightText = getString(R.styleable.YxCouponView_yxc_right_text) ?: rightText
            leftTextHorizontalPadding = getDimensionPixelSize(R.styleable.YxCouponView_yxc_left_text_horizontal_padding, leftTextHorizontalPadding)
            rightTextHorizontalPadding = getDimensionPixelSize(R.styleable.YxCouponView_yxc_right_text_horizontal_padding, rightTextHorizontalPadding)
            textVerticalPadding = getDimensionPixelSize(R.styleable.YxCouponView_yxc_text_vertical_padding, textVerticalPadding)
            textSize = getDimension(R.styleable.YxCouponView_yxc_text_size, textSize)
        }

        linePaint.color = strokeColor
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = strokeWidth

        textPaint.color = textColor
        textPaint.typeface = Typeface.DEFAULT_BOLD
        textPaint.textSize = textSize
        textPaint.textAlign = Paint.Align.CENTER

        dashPathEffect = DashPathEffect(floatArrayOf(strokeWidth * 2, strokeWidth), 0f)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        textPaint.getTextBounds(leftText, 0, leftText.length, textBoundRect)
        val viewHeight = textBoundRect.height() + textVerticalPadding * 2 // 整体高度
        leftTextWidth = textBoundRect.width() + leftTextHorizontalPadding * 2 // 左侧文本宽度
        textPaint.getTextBounds(rightText, 0, rightText.length, textBoundRect)
        rightTextWidth = textBoundRect.width() + rightTextHorizontalPadding * 2 // 右侧文本高度
        setMeasuredDimension(leftTextWidth + rightTextWidth, viewHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // 缺口宽高
        gapWidth = h / 3.42857f
        gapHalfHeight = h / 8f

        outsideGapBitmap = createGapBitmap(gapWidth.toInt(), (gapHalfHeight * 2f).toInt(), linePaint.color)
        insideGapBitmap = createGapBitmap((gapWidth - strokeWidth * 2).toInt(), (gapHalfHeight * 2f - strokeWidth * 2).toInt(), linePaint.color)
    }

    override fun onDraw(canvas: Canvas) {
        // 左侧文字背景
        linePaint.pathEffect = null
        linePaint.style = Paint.Style.FILL
        linePaint.color = leftTextBgColor
        canvas.drawRoundRect(strokeWidth / 2f, strokeWidth / 2f, leftTextWidth.toFloat(), height.toFloat(),
            cornerRadius, cornerRadius, linePaint)

        // 整体背景圆角区域
        linePaint.style = Paint.Style.STROKE
        linePaint.color = strokeColor
        canvas.drawRoundRect(strokeWidth / 2f, strokeWidth / 2f, width.toFloat() - strokeWidth / 2f, height.toFloat() - strokeWidth / 2f,
            cornerRadius, cornerRadius, linePaint)

        // 左右文字之间的分割虚线
        linePaint.pathEffect = dashPathEffect
        canvas.drawLine(leftTextWidth.toFloat(), 0f, leftTextWidth.toFloat(), height.toFloat(), linePaint)

        // 绘制文本
        val textFontMetrics = textPaint.fontMetrics
        val textCenterY = (textBoundRect.height() + textVerticalPadding).toFloat() - (textFontMetrics.leading + textFontMetrics.descent) / 2f + strokeWidth / 3f
        canvas.drawText(leftText, leftTextWidth / 2f, textCenterY, textPaint)
        canvas.drawText(rightText, leftTextWidth + rightTextWidth / 2f, textCenterY, textPaint)

        // 上缺口
        drawGapBitmap(leftTextWidth.toFloat() - gapWidth / 2, -gapHalfHeight, canvas)
        // 下缺口
        drawGapBitmap(leftTextWidth.toFloat() - gapWidth / 2, height.toFloat() - gapHalfHeight, canvas)
    }

    private fun drawGapBitmap(left: Float, top: Float, canvas: Canvas) {
        gapPaint.xfermode = null
        canvas.drawBitmap(outsideGapBitmap, left, top, gapPaint)
        gapPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        canvas.drawBitmap(insideGapBitmap, left + strokeWidth, strokeWidth + top, gapPaint)
    }

    private fun createGapBitmap(w: Int, h: Int, color: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        canvas.drawOval(0f, 0f, w.toFloat(), h.toFloat(), paint)
        return bitmap
    }
}