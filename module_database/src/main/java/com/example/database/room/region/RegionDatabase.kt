package com.example.database.room.region

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.room.region.dao.CityDao
import com.example.database.room.region.dao.ProvinceDao
import com.example.database.room.region.entity.Area
import com.example.database.room.region.entity.City
import com.example.database.room.region.entity.Province

/**
 *
 * @author wangzf
 * @date 2022/2/11
 */

@Database(version = 1, entities = [Province::class, City::class, Area::class])
abstract class RegionDatabase : RoomDatabase() {
    abstract fun provinceDao(): ProvinceDao
    abstract fun cityDao(): CityDao
}