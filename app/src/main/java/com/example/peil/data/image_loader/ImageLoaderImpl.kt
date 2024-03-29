package com.example.peil.data.image_loader

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class ImageLoaderImpl(
    private val context: Context
) : ImageLoader {
    override suspend fun downloadAndSaveImage(
        imageUrl: String,
        child: String,
        fileName: String
    ): String = withContext(Dispatchers.IO) {
        val directory = File(context.getExternalFilesDir(null), child)
        directory.mkdirs()
        val file = File(directory, fileName)

        val result = try {
            val resource = Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .apply(
                    RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                )
                .submit()
                .get()

            val compressFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Bitmap.CompressFormat.WEBP_LOSSY
            } else {
                Bitmap.CompressFormat.WEBP
            }

            FileOutputStream(file).use { outputStream ->
                resource.compress(compressFormat, 100, outputStream)
            }

            file.absolutePath
        } catch (e: Exception) {
            null
        }

        return@withContext result ?: ""
    }
}