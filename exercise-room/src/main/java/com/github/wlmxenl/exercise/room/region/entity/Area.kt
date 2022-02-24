package com.github.wlmxenl.exercise.room.region.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * 区县数据
 * @author wangzf
 * @date 2022/2/11
 */
@Entity(
    tableName = "region_area",
    indices = [Index(name = "region_area_id_uindex", value = ["id"], unique = true)]
)
data class Area(
    @PrimaryKey val id: Int,
    val code: Int,
    val name: String,
    @ColumnInfo(name = "short_name") val shortName: String,
    @ColumnInfo(name = "citycode") val cityCode: Int,
    @ColumnInfo(name = "weather_code") val weatherCode: Int,
    @ColumnInfo(name = "nmc_code") val nmcCode: Int,
    @ColumnInfo(name = "port_id") val portId: Int,
    val location: String,
    @ColumnInfo(name = "total_fishing") val totalFishing: Int,
    @ColumnInfo(name = "total_shop") val totalShop: Int
)