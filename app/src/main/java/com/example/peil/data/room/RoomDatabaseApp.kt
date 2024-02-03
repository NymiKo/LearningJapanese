package com.example.peil.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.peil.data.room.dao.LessonDao
import com.example.peil.data.room.entities.LessonEntity

@Database(
    entities = [
        LessonEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class RoomDatabaseApp : RoomDatabase() {
    abstract fun lessonDao(): LessonDao
}