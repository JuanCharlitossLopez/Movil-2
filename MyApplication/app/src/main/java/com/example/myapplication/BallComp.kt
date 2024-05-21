package com.example.myapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.getSystemService



@Composable
fun BallGame() {
    val context = LocalContext.current
    val sensorManager = context.getSystemService<SensorManager>()!!
    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    var xPos by remember { mutableStateOf(0f) }
    var yPos by remember { mutableStateOf(0f) }
    var scoreTop by remember { mutableStateOf(0) }
    var scoreBottom by remember { mutableStateOf(0) }
    val ballBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ball3).asImageBitmap()
    val backgroundBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.grass).asImageBitmap()

    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.toFloat()
    val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.toFloat()
    val ballWidth = ballBitmap.width.toFloat() / 2
    val ballHeight = ballBitmap.height.toFloat() / 2
    val goalWidth = 400f
    val goalHeight = 50f

    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                xPos += event.values[0] * 5
                yPos += event.values[1] * 5

                if (xPos < 0) xPos = 0f
                if (xPos > screenWidth - ballWidth) xPos = screenWidth - ballWidth
                if (yPos < 0) yPos = 0f
                if (yPos > screenHeight - ballHeight) yPos = screenHeight - ballHeight

                if (yPos <= goalHeight && xPos >= screenWidth / 2f - goalWidth / 2f && xPos <= screenWidth / 2f + goalWidth / 2f) {
                    scoreTop++
                    val (newXPos, newYPos) = resetBall(screenWidth, screenHeight, ballWidth, ballHeight)
                    xPos = newXPos
                    yPos = newYPos
                }
                if (yPos >= screenHeight - goalHeight - ballHeight && xPos >= screenWidth / 2f - goalWidth / 2f && xPos <= screenWidth / 2f + goalWidth / 2f) {
                    scoreBottom++
                    val (newXPos, newYPos) = resetBall(screenWidth, screenHeight, ballWidth, ballHeight)
                    xPos = newXPos
                    yPos = newYPos
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Not used
            }
        }
        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_GAME)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawImage(
                    image = backgroundBitmap,
                    topLeft = Offset.Zero,
                    //dstSize = Size(size.width, size.height),
                    alpha = 1f
                )
                drawRect(
                    color = Color.Red,
                    topLeft = Offset((screenWidth / 2f - goalWidth / 2f), 0f),
                    size = androidx.compose.ui.geometry.Size(goalWidth, goalHeight)
                )
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset((screenWidth / 2f - goalWidth / 2f), screenHeight - goalHeight),
                    size = androidx.compose.ui.geometry.Size(goalWidth, goalHeight)
                )
                drawImage(ballBitmap, Offset(xPos, yPos))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "RED: $scoreBottom", modifier = Modifier.padding(16.dp)
                    ,
                    color = Color.White)
                Text(text = "BLUE: $scoreTop", modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            }
        }

    }
}

fun resetBall(screenWidth: Float, screenHeight: Float, ballWidth: Float, ballHeight: Float): Pair<Float, Float> {
    val xPos = screenWidth / 2f - ballWidth / 2f
    val yPos = screenHeight / 2f - ballHeight / 2f
    return xPos to yPos
}
