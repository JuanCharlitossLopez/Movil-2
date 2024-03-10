package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.provider.Settings

class AirplaneModeReceiver(
    val onEnabled: () -> Unit,
    val onDisabled: () -> Unit
) : BroadcastReceiver() {
    private var registered = false


    //escuchar con el evento
    override fun onReceive(context: Context?, intent: Intent?) {
        val activado = Settings.Global.getInt(context?.contentResolver, Settings.Global.AIRPLANE_MODE_ON) != 0//Para el modo avion en el tel
        if (activado) onEnabled else onDisabled
    }

    //registrar el evento
    fun register(context: Context){
        if(!registered){
            val filter = IntentFilter()
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            context.registerReceiver(this, filter)
            registered = true
        }
    }

    //sacar el registro
    fun unregister(context: Context){
        if (registered){
            context.unregisterReceiver(this)
            registered = false

        }
    }


}