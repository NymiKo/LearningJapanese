package com.example.peil.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.peil.data.room.RoomContract
import com.example.peil.data.room.entities.SubLessonEntity

@Dao
interface SubLessonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubLesson(subLesson: SubLessonEntity)

    @Query("SELECT * FROM ${RoomContract.tableSubLessons} WHERE idLesson = :idLesson")
    suspend fun getSubLessons(idLesson: Int): List<SubLessonEntity>

    @Query("DELETE FROM ${RoomContract.tableSubLessons}")
    suspend fun deleteAllSubLessons()
}