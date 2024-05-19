package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View

class BallView(context: Context) : View(context) {
    private lateinit var ball: Bitmap
    private lateinit var background: Bitmap
    private var xPos = 0f
    private var yPos = 0f
    private val sensorManager: SensorManager
    private val sensor: Sensor?
    private val sensorListener: SensorEventListener
    private var scoreTop = -1
    private var scoreBottom = -1
    private val paint = Paint()
    private val goalWidth = 400
    private val goalHeight = 50

    init {
        // Inicializar el bitmap de la pelota
        val originalBall = BitmapFactory.decodeResource(resources, R.drawable.ball)
        ball = Bitmap.createScaledBitmap(originalBall, originalBall.width / 10, originalBall.height / 10, true)

        // Inicializar el bitmap del fondo
        val originalBackground = BitmapFactory.decodeResource(resources, R.drawable.futbol_field)
        background = Bitmap.createScaledBitmap(originalBackground, context.resources.displayMetrics.widthPixels, context.resources.displayMetrics.heightPixels, true)

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

                checkGoal()
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

    private fun checkGoal() {
        // Verificar si la pelota entra en la portería superior
        if (yPos <= goalHeight && xPos >= width / 2f - goalWidth / 2f && xPos <= width / 2f + goalWidth / 2f) {
            scoreTop++
            resetBall()
        }
        // Verificar si la pelota entra en la portería inferior
        if (yPos >= height - goalHeight - ball.height && xPos >= width / 2f - goalWidth / 2f && xPos <= width / 2f + goalWidth / 2f) {
            scoreBottom++
            resetBall()
        }
    }

    private fun resetBall() {
        // Resetear la posición de la pelota al centro de la pantalla
        xPos = width / 2f - ball.width / 2f
        yPos = height / 2f - ball.height / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dibujar el fondo
        canvas.drawBitmap(background, 0f, 0f, null)

        // Dibujar las porterías
        paint.color = Color.RED
        canvas.drawRect((width / 2f - goalWidth / 2f), 0f, (width / 2f + goalWidth / 2f), goalHeight.toFloat(), paint)
        canvas.drawRect((width / 2f - goalWidth / 2f), (height - goalHeight).toFloat(), (width / 2f + goalWidth / 2f), height.toFloat(), paint)

        // Dibujar la pelota
        canvas.drawBitmap(ball, xPos, yPos, null)

        // Dibujar las puntuaciones
        paint.color = Color.BLACK
        paint.textSize = 50f
        canvas.drawText("LOCAL: $scoreBottom", 50f, 100f, paint)
        canvas.drawText("VISITANTE: $scoreTop", 50f, height - 50f, paint)
    }
}
