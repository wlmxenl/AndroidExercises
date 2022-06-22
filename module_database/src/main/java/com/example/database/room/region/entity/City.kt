package com.example.database.room.region.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * 市级数据
 * @author wangzf
 * @date 2022/2/11
 */
@Entity(
    tableName = "region_city",
    indices = [Index(name = "idx_name", value = ["name"])]
)
data class City(
    @PrimaryKey val id: Int,
    val code: Int,
    val name: String,
    @ColumnInfo(name = "short_name") val shortName: String,
    @ColumnInfo(name = "provincecode") val provinceCode: Int,
    @ColumnInfo(name = "weather_code") val weatherCode: Int,
    @ColumnInfo(name = "nmc_code") val nmcCode: Int,
    @ColumnInfo(name = "port_id") val portId: Int,
    val pinyin: String,
    val py: String,
    @ColumnInfo(name = "city_group") val cityGroup: String,
    val location: String
)