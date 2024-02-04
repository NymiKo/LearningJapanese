package com.example.peil.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.peil.data.room.RoomContract
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel

@Entity(
    tableName = RoomContract.tableLessons
)
data class LessonEntity(
    @PrimaryKey(autoGenerate = false)
    val idLesson: Int,
    val header: String,
    val image: String,
    val chapter: Int,
    val completed: Boolean,
    val isSynchronized: Boolean = false
) {
    companion object {
        fun toLessonEntity(lesson: LessonModel) = LessonEntity(
            idLesson = lesson.idLesson,
            header = lesson.header,
            image = lesson.image,
            chapter = lesson.chapter,
            completed = lesson.completed
        )
    }

    fun toLessonModel() = LessonModel(
        idLesson = idLesson,
        header = header,
        image = image,
        chapter = chapter,
        completed =completed
    )
}
