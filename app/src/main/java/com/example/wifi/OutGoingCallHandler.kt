package com.example.wifi

import com.example.wifi.MainActivity2
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class MyOutgoingCallHandler : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Extract phone number reformatted by previous receivers
        var phoneNumber = resultData
        if (phoneNumber == null) {
            // No reformatted number, use the original
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
        }
        if (phoneNumber == "*1234#") {
            // DialedNumber checking.
            // My app will bring up, so cancel the broadcast
            resultData = null
            // Start my app
            val i = Intent(context, MainActivity::class.java)
            i.putExtra("extra_phone", phoneNumber)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }
}