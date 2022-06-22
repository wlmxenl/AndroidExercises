package com.example.database

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.database.room.region.RegionDatabase

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        copyDBFileToDatabasePath()

        val db = Room.databaseBuilder(applicationContext, RegionDatabase::class.java, "region.db")
            .build()

        Thread {
            Log.e(TAG, "onCreate: ")
            db.provinceDao().getAll().forEach {
                Log.e(TAG, it.name)
            }
        }.start()
    }

    private fun copyDBFileToDatabasePath() {
        val destDbFile = getDatabasePath("region.db")
        if (destDbFile.exists()) {
            return
        }
        assets.open("region.db").use { input ->
            destDbFile.outputStream().use {
                input.copyTo(it)
            }
        }
    }
}