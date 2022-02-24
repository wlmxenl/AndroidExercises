package com.github.wlmxenl.exercise.room.region

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.wlmxenl.exercise.room.region.dao.CityDao
import com.github.wlmxenl.exercise.room.region.dao.ProvinceDao
import com.github.wlmxenl.exercise.room.region.entity.Area
import com.github.wlmxenl.exercise.room.region.entity.City
import com.github.wlmxenl.exercise.room.region.entity.Province

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