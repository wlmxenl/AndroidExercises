## 参考文档

- [Room 官方指南](https://developer.android.google.cn/training/data-storage/room)
- [Android 开发者 > Jetpack > 库 > Room](https://developer.android.google.cn/jetpack/androidx/releases/room#kts)
- [使用 Room DAO 访问数据](https://developer.android.google.cn/training/data-storage/room/accessing-data)



## 遇到的问题

### `Pre-packaged database has an invalid schema`

错误日志如下：

```prolog
2022-02-11 17:29:25.929 15322-15359/com.github.wlmxenl.exercise.room E/AndroidRuntime: FATAL EXCEPTION: Thread-2
    Process: com.github.wlmxenl.exercise.room, PID: 15322
    java.lang.IllegalStateException: Pre-packaged database has an invalid schema: region_city(com.github.wlmxenl.exercise.room.region.entity.City).
     Expected:
    TableInfo{name='region_city', columns={code=Column{name='code', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, pinyin=Column{name='pinyin', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, provincecode=Column{name='provincecode', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, city_group=Column{name='city_group', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, name=Column{name='name', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, nmc_code=Column{name='nmc_code', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, port_id=Column{name='port_id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, py=Column{name='py', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, short_name=Column{name='short_name', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, location=Column{name='location', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'}, weather_code=Column{name='weather_code', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}
     Found:
    TableInfo{name='region_city', columns={code=Column{name='code', type='INTEGER(11)', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, pinyin=Column{name='pinyin', type='TEXT(50)', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, provincecode=Column{name='provincecode', type='INTEGER(11)', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, city_group=Column{name='city_group', type='TEXT(10)', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, name=Column{name='name', type='TEXT(20)', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, nmc_code=Column{name='nmc_code', type='INTEGER(11)', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, port_id=Column{name='port_id', type='INTEGER(11)', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, py=Column{name='py', type='TEXT(10)', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, short_name=Column{name='short_name', type='TEXT(20)', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, location=Column{name='location', type='TEXT(100)', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, id=Column{name='id', type='INTEGER(11)', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'}, weather_code=Column{name='weather_code', type='INTEGER(11)', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[Index{name='idx_name', unique=false, columns=[name], orders=[ASC]}]}
        at androidx.room.RoomOpenHelper.onCreate(RoomOpenHelper.java:82)
        at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.onCreate(FrameworkSQLiteOpenHelper.java:177)
        at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:411)
        at android.database.sqlite.SQLiteOpenHelper.getWritableDatabase(SQLiteOpenHelper.java:316)
        at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getWritableSupportDatabase(FrameworkSQLiteOpenHelper.java:151)
        at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.getWritableDatabase(FrameworkSQLiteOpenHelper.java:112)
        at androidx.room.RoomDatabase.inTransaction(RoomDatabase.java:706)
2022-02-11 17:29:25.930 15322-15359/com.github.wlmxenl.exercise.room E/AndroidRuntime:     at androidx.room.RoomDatabase.assertNotSuspendingTransaction(RoomDatabase.java:483)
        at com.github.wlmxenl.exercise.room.region.dao.ProvinceDao_Impl.getAll(ProvinceDao_Impl.java:31)
        at com.github.wlmxenl.exercise.room.MainActivity.onCreate$lambda-1(MainActivity.kt:29)
        at com.github.wlmxenl.exercise.room.MainActivity.$r8$lambda$Kz5VzJRrVlQB7QeNAuw7J-KgHrM(Unknown Source:0)
        at com.github.wlmxenl.exercise.room.MainActivity$$ExternalSyntheticLambda0.run(Unknown Source:2)
        at java.lang.Thread.run(Thread.java:923)
```

源码分析

```java
final TableInfo _infoRegionCity = new TableInfo("region_city", _columnsRegionCity, _foreignKeysRegionCity, _indicesRegionCity);
final TableInfo _existingRegionCity = TableInfo.read(_db, "region_city");
if (! _infoRegionCity.equals(_existingRegionCity)) {
  return new RoomOpenHelper.ValidationResult(false, "region_city(com.github.wlmxenl.exercise.room.region.entity.City).\n"
          + " Expected:\n" + _infoRegionCity + "\n"
          + " Found:\n" + _existingRegionCity);
}
```

错误分析

```prolog
indices=[]
indices=[Index{name='idx_name', unique=false, columns=[name], orders=[ASC]}]
```

解决问题

- 定义实体类字段类型与数据库表字段类型保持一致

- 索引配置需要与数据库保持一致
