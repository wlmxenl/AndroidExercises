package com.github.wlmxenl.exercise.common.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange

/**
 *
 * @author wangzf
 * @date 2022/2/23
 */
object BitmapUtil {

    fun decodeResource(context: Context, @DrawableRes resId: Int, @IntRange(from = 0) outWidth: Int): Bitmap {
        val options = BitmapFactory.Options()
        // 只返回 outWidth、outHeight、outMimeType 避免内存分配
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, resId, options)
        // 返回按指定宽度缩放的 bitmap
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = outWidth
        return BitmapFactory.decodeResource(context.resources, resId, options)
    }

}