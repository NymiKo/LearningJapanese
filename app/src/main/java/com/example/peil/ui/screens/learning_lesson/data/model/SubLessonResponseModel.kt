package com.example.peil.ui.screens.learning_lesson.data.model

import androidx.compose.runtime.mutableStateOf
import com.example.peil.ui.screens.learning_lesson.SubLessonsType
import com.google.gson.annotations.SerializedName

data class SubLessonResponseModel(
    val idSubLesson: Int,
    val header: String,
    @SerializedName("lesson_image")
    val lessonImage: String,
    @SerializedName("new_word")
    val newWord: String,
    @SerializedName("translation_word")
    val translationWord: String,
    val options: String,
    val correctOption: String,
    val sentence: String,
    val remark: String,
    val type: Int,
    val audio: String
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
