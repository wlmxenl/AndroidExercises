package com.example.customview.xfermode

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.ColorUtils
import com.github.wlmxenl.exercise.common.util.dp2pxFloat

/**
 * @author wangzf
 * @date 2022/6/13
 *
 * https://developer.android.com/reference/android/graphics/PorterDuff.Mode
 */
class PorterDuffModeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    lateinit var dstBitmap: Bitmap
    lateinit var srcBitmap: Bitmap

    private var dstWidth = 140f.dp2pxFloat()
    private var dstHeight = dstWidth * 0.6f
    private var srcWidth = dstWidth * 0.9
    private var srcHeight = dstHeight * 0.9f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var contentHeight = 0f

    init {
        // 关闭硬件加速, 修复 PorterDuff.Mode 透明区域变黑
        setLayerType(LAYER_TYPE_HARDWARE,null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        dstBitmap = createBitmap(dstWidth.toInt(), dstHeight.toInt(), ColorUtils.string2Int("#2196f3"))
        srcBitmap = createBitmap(srcWidth.toInt(), srcHeight.toInt(), ColorUtils.string2Int("#e91e63"))
    }

    private fun createBitmap(w: Int, h: Int, color: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        paint.color = color
        canvas.drawOval(0f, 0f, w.toFloat(), h.toFloat(), paint)
        return bitmap
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        paint.xfermode = null
        canvas.drawBitmap(dstBitmap, 10f, 10f, paint)
        canvas.drawBitmap(srcBitmap, width / 2f, 10f, paint)
        contentHeight += dstHeight * 1.5f

        canvas.drawBitmap(dstBitmap, 10f, contentHeight, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        canvas.drawBitmap(srcBitmap, 10f + (dstBitmap.width - srcBitmap.width) / 2f, contentHeight + (dstBitmap.height - srcBitmap.height) / 2f, paint)
    }
}