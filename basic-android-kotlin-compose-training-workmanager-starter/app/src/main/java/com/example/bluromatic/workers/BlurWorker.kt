package com.example.bluromatic.workers
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import android.content.Context
import android.graphics.BitmapFactory
import com.example.bluromatic.R

private const val TAG = "BlurWorker"

class BlurWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {

        makeStatusNotification(
            applicationContext.resources.getString(R.string.blurring_image),
            applicationContext
        )

        return try {
            val picture = BitmapFactory.decodeResource(
                applicationContext.resources,
                R.drawable.android_cupcake
            )

            val output = blurBitmap(picture, 1)
            // Write bitmap to a temp file
            val outputUri = writeBitmapToFile(applicationContext, output)
            makeStatusNotification(
                "Output is $outputUri",
                applicationContext
            )


            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}