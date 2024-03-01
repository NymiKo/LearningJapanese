package com.example.peil.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

    @Query("UPDATE ${RoomContract.tableLessons} SET completed = :completed WHERE idLesson = :idLesson")
    suspend fun updateLesson(idLesson: Int, completed: Boolean)

    @Query("UPDATE ${RoomContract.tableLessons} SET isSynchronized = :isSynchronized WHERE idLesson = :idLesson")
    suspend fun lessonSynchronized(idLesson: Int, isSynchronized: Boolean)

    @Query("SELECT idLesson FROM ${RoomContract.tableLessons} WHERE isSynchronized = 0 AND completed = 1")
    suspend fun getIdUnsychronizedLessonsList(): List<Int>

    @Query("UPDATE ${RoomContract.tableLessons} SET isSynchronized = 1 WHERE idLesson IN (:lessons)")
    suspend fun updateSynchronizedLessons(lessons: List<Int>)

    @Query("DELETE FROM ${RoomContract.tableLessons}")
    suspend fun deleteAllLessons()
}