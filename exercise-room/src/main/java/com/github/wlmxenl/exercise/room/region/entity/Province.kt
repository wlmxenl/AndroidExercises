package com.github.wlmxenl.exercise.room.region.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 省级数据
 * @Author wangzhengfu
 * @Date 2022/2/11
 */
@Entity(tableName = "region_province")
data class Province (
    @PrimaryKey val id: Int,
    val code: Int,
    val name: String
    )