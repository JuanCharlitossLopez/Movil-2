package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View


class BallView(context: Context) : View(context) {
    private val ball: Bitmap
    private var xPos = 0f
    private var yPos = 0f
    private val sensorManager: SensorManager
    private val sensor: Sensor?
    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            xPos += event.values[0] * 5
            yPos += event.values[1] * 5
            if (xPos < 0) xPos = 0f
           // if (xPos > width - ball.getWidth()) xPos = (width - ball.getWidth()).toFloat()
            if (yPos < 0) yPos = 0f
            //if (yPos > height - ball.getHeight()) yPos = (height - ball.getHeight()).toFloat()
            invalidate()
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Not used
        }
    }

    init {
        val originalBall = BitmapFactory.decodeResource(resources, R.drawable.ball)
        ball = Bitmap.createScaledBitmap(originalBall, originalBall.width / 10, originalBall.height / 10, true)
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_GAME)
        // Initialize the ball position to the center of the screen
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        xPos = 0f
        yPos = 0f
        xPos = screenWidth / 2f - ball.width / 2f
        yPos = screenHeight / 2f - ball.height / 2f
        ball.height
        ball.width
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(ball, xPos, yPos, null)
    }
}