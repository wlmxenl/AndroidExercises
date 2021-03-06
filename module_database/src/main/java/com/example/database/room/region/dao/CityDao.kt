package com.example.database.room.region.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.database.room.region.entity.City

/**
 * 数据访问对象
 * @author wangzf
 * @date 2022/2/11
 */
@Dao
interface CityDao {

    @Query("SELECT * FROM region_city WHERE provincecode = :provincecode")
    fun findCityWithProvinceCode(provincecode: String): List<City>

}