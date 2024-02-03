package com.example.peil.data.room.entities

import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.peil.data.room.RoomContract
import com.example.peil.ui.screens.learning_lesson.SubLessonsType
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel

@Entity(
    tableName = RoomContract.tableSubLessons
)
data class SubLessonEntity(
    @PrimaryKey(autoGenerate = false)
    val idSubLesson: Int,
    val header: String = "",
    val lessonImage: String = "",
    val newWord: String = "",
    val translationWord: String = "",
    val options: String = "",
    val correctOption: String = "",
    val sentence: String = "",
    val remark: String = "",
    val type: Int,
    val audio: String = "",
    val idLesson: Int
) {
    fun toSubLessonModel() = SubLessonModel(
        idSubLesson = idSubLesson,
        header = header,
        lessonImage = lessonImage,
        newWord = newWord,
        translationWord = translationWord,
        completed = mutableStateOf(false),
        options = options.split(",").toTypedArray(),
        correctOption = correctOption.split(",").toTypedArray(),
        sentence = sentence.split(",").toTypedArray(),
        remark = remark,
        type = SubLessonsType.entries.find { it.type == type } ?: SubLessonsType.SubLessonNewInfo,
        audio = audio
    )
}
