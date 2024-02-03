package com.example.peil.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.peil.data.room.RoomContract
import com.example.peil.data.room.entities.LessonEntity

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity): Long

    @Query("SELECT idLesson FROM ${RoomContract.tableLessons}")
    suspend fun getIdLessonsList(): List<Int>

    @Query("SELECT * FROM ${RoomContract.tableLessons}")
    suspend fun getLessonsList(): List<LessonEntity>
}