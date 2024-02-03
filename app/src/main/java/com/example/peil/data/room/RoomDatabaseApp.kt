package com.example.peil.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.peil.data.room.dao.LessonDao
import com.example.peil.data.room.dao.SubLessonDao
import com.example.peil.data.room.entities.LessonEntity
import com.example.peil.data.room.entities.SubLessonEntity

@Database(
    entities = [
        LessonEntity::class,
        SubLessonEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class RoomDatabaseApp : RoomDatabase() {
    abstract fun lessonDao(): LessonDao
    abstract fun subLessonDao(): SubLessonDao
}