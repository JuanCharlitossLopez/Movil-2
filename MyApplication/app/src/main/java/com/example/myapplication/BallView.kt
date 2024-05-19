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
    private lateinit var ball: Bitmap
    private var xPos = 0f
    private var yPos = 0f
    private val sensorManager: SensorManager
    private val sensor: Sensor?
    private val sensorListener: SensorEventListener

    init {
        // Inicializar el bitmap de la pelota
        val originalBall = BitmapFactory.decodeResource(resources, R.drawable.ball)
        ball = Bitmap.createScaledBitmap(originalBall, originalBall.width / 10, originalBall.height / 10, true)

        // Configurar el sensor
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                xPos += event.values[0] * 5
                yPos += event.values[1] * 5
                if (xPos < 0) xPos = 0f
                if (xPos > width - ball.width) xPos = (width - ball.width).toFloat()
                if (yPos < 0) yPos = 0f
                if (yPos > height - ball.height) yPos = (height - ball.height).toFloat()
                invalidate()
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                // Not used
            }
        }
        sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_GAME)

        // Inicializar la posición de la pelota en el centro de la pantalla
        post {
            val displayMetrics = context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels
            xPos = screenWidth / 2f - ball.width / 2f
            yPos = screenHeight / 2f - ball.height / 2f
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(ball, xPos, yPos, null)
    }
}
