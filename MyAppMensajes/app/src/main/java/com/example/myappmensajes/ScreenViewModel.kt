package com.example.myappmensajes

import android.telephony.SmsManager
import androidx.lifecycle.ViewModel

class ScreenViewModel: ViewModel() {
    fun sendSMS(tel:String, mesg:String){
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(tel,
            null,
            mesg,
            null,
            null);
    }
}