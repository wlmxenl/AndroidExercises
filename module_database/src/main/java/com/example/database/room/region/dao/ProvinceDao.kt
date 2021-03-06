package com.example.database.room.region.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.database.room.region.entity.Province

/**
 * 数据访问对象
 * @author wangzf
 * @date 2022/2/11
 */
@Dao
interface ProvinceDao {

    @Query("SELECT * FROM region_province")
    fun getAll(): List<Province>

}