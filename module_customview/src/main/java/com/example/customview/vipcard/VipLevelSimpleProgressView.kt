package com.example.customview.vipcard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.github.wlmxenl.exercise.common.util.dp2pxFloat
import com.github.wlmxenl.exercise.common.util.sp2pxFloat


/**
 *
 * @author wangzf
 * @date 2022/5/30
 */
class VipLevelSimpleProgressView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val levelLineHeight = 5f.dp2pxFloat()
    private val levelLineBgPath = Path()
    private val levelPointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val levelPointRadius = 5f.dp2pxFloat()
    private val preLevelPercent = 0.14f
    private val nextLevelPercent = 0.83f

    init {
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = levelLineHeight
        linePaint.color = Color.parseColor("#D4933F")
        linePaint.strokeCap = Paint.Cap.ROUND

        levelPointPaint.color = Color.WHITE
        levelPointPaint.style = Paint.Style.FILL
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        // 背景等级曲线
        levelLineBgPath.reset()
        levelLineBgPath.moveTo(width * 180 / 698f, height * (32 + 82) / 156f)
        levelLineBgPath.cubicTo(
            width * (180 + 90) / 698f,
            height * (32 + 83) / 156f,
            width * (180 + 299) / 698f,
            height * (32 + 86) / 156f,
            width * (180 + 492) / 698f,
            height * 32 / 156f)
        canvas.drawPath(levelLineBgPath, linePaint)

        val bgPathMeasure = PathMeasure(levelLineBgPath, false)

        // 上一等级标记点
        val prePointArr = getPathLinePoint(preLevelPercent, bgPathMeasure)
        canvas.drawCircle(prePointArr[0], prePointArr[1], levelPointRadius, levelPointPaint)

        // 下一等级标记点
        val nextPointArr = getPathLinePoint(nextLevelPercent, bgPathMeasure)
        canvas.drawCircle(nextPointArr[0], nextPointArr[1], levelPointRadius, levelPointPaint)

        // 进度
        levelLineBgPath.reset()
        // 进度占整条线比值
        val progressPercent = 0.7f
        val progressLinePathPercent = (preLevelPercent + (nextLevelPercent - preLevelPercent) * progressPercent)

        // 进度描述
        getPathLinePoint(progressLinePathPercent, bgPathMeasure).let {
            textPaint.color = Color.WHITE
            textPaint.textSize = 12f.sp2pxFloat()
            textPaint.textAlign = Paint.Align.CENTER
            canvas.drawText(
                "${(progressPercent*100).toInt()}%",
                it[0],
                it[1] - 12f.dp2pxFloat(),
                textPaint)
            it
        }
        bgPathMeasure.getSegment(
            bgPathMeasure.length * 0F,
            bgPathMeasure.length * progressLinePathPercent,
            levelLineBgPath, true)

        linePaint.color = Color.WHITE
        canvas.drawPath(levelLineBgPath, linePaint)
    }

    private fun getPathLinePoint(percent: Float, pathMeasure: PathMeasure): FloatArray {
        val floatArr = floatArrayOf(0f, 0f)
        pathMeasure.getPosTan(pathMeasure.length * percent, floatArr, floatArrayOf(0f, 0f))
        return floatArr
    }
}