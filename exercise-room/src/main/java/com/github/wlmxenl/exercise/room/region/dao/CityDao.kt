package com.github.wlmxenl.exercise.room.region.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.wlmxenl.exercise.room.region.entity.City

/**
 * 数据访问对象
 * @Author wangzhengfu
 * @Date 2022/2/11
 */
@Dao
interface CityDao {

    @Query("SELECT * FROM region_city WHERE provincecode = :provincecode")
    fun findCityWithProvinceCode(provincecode: String): List<City>

}