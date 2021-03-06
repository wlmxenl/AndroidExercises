package com.example.database.room.region.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 省级数据
 * @author wangzf
 * @date 2022/2/11
 */
@Entity(tableName = "region_province")
data class Province (
    @PrimaryKey val id: Int,
    val code: Int,
    val name: String
    )