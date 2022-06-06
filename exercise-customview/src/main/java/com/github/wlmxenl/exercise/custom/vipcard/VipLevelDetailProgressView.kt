package com.github.wlmxenl.exercise.custom.vipcard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Paint.FontMetrics
import android.util.AttributeSet
import android.view.View
import com.github.wlmxenl.exercise.common.util.dp2pxFloat
import com.github.wlmxenl.exercise.common.util.dp2pxInt
import com.github.wlmxenl.exercise.common.util.sp2pxFloat


/**
 *
 * @author wangzf
 * @date 2022/5/30
 */
class VipLevelDetailProgressView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val nextLevelTips = "升级还需400积分"
    private val preLevelName = "黄金会员:500积分"
    private val nextLevelName = "铂金会员:1000积分"

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textBoundsRect = Rect()

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

        levelPointPaint.color = Color.WHITE
        levelPointPaint.style = Paint.Style.FILL
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        // 背景等级曲线
        levelLineBgPath.reset()
        linePaint.strokeCap = Paint.Cap.BUTT
        val levelLineStartY = height * 160 / 206f // 背景曲线 起点 Y 轴坐标
        levelLineBgPath.moveTo(0f, levelLineStartY)
        levelLineBgPath.cubicTo(
            width * 127 / 698f,
            height * 162 / 206f,
            width * 424 / 698f,
            height * 167 / 206f,
            width.toFloat() + levelLineHeight / 2f,
            levelLineHeight / 2f)
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
        linePaint.strokeCap = Paint.Cap.ROUND
        canvas.drawPath(levelLineBgPath, linePaint)

        // 升级积分提示
        textPaint.color = Color.parseColor("#452906")
        textPaint.textSize = 14f.sp2pxFloat()
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.getTextBounds(nextLevelTips, 0, nextLevelTips.length, textBoundsRect)
        var fontMetrics: FontMetrics = textPaint.fontMetrics
        val bottomCenterTextY = height - textBoundsRect.height() / 2f + fontMetrics.leading + fontMetrics.descent
        canvas.drawText(
            nextLevelTips,
            width / 2f,
            bottomCenterTextY,
            textPaint)

        // 上一等级名称
        textPaint.textSize = 11f.sp2pxFloat()
        textPaint.color = Color.WHITE
        fontMetrics = textPaint.fontMetrics
        val preLevelNameCenterY = bottomCenterTextY - (fontMetrics.leading + fontMetrics.descent) / 2f
        canvas.drawText(preLevelName, prePointArr[0], preLevelNameCenterY, textPaint)

        // 下一等级名称
        canvas.drawText(nextLevelName, nextPointArr[0], height * 137 / 206f, textPaint)
    }

    private fun getPathLinePoint(percent: Float, pathMeasure: PathMeasure): FloatArray {
        val floatArr = floatArrayOf(0f, 0f)
        pathMeasure.getPosTan(pathMeasure.length * percent, floatArr, floatArrayOf(0f, 0f))
        return floatArr
    }
}