package com.example.broadcasttel.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.widget.Toast


class IncomingCallReceiver : BroadcastReceiver() {

    fun showToastMsg(context: Context, msg: String) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private lateinit var telephonyManager: TelephonyManager
    private lateinit var phoneStateListener: PhoneStateListener

    override fun onReceive(context: Context, intent: Intent) {
        telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        phoneStateListener = object : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, phoneNumber: String) {
                super.onCallStateChanged(state, phoneNumber)
                if (state == TelephonyManager.CALL_STATE_RINGING) {
                    // Send message to the incoming number here
                    Log.d("TEL", "Número de teléfono: $phoneNumber")
                    sendMessage(context,phoneNumber)
                    // Obtener el número de teléfono durante una llamada entrante
                    //showToastMsg(context, msg = "T:$phoneNumber")
                }
            }
        }
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
    }

    private fun sendMessage(context: Context,phoneNumber: String?) {
       if (!phoneNumber.isNullOrBlank()){
           val smsManager = SmsManager.getDefault()
           val message = "Hello, this is an automated message."
           smsManager.sendTextMessage(phoneNumber, null, message, null, null)
           showToastMsg(context, msg = "Mensaje Enviado")
       }else{
           showToastMsg(context, msg = "Tel:$phoneNumber")
       }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE)
//    }
}
