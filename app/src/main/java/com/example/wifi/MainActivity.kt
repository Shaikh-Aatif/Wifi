package com.example.wifi

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        p.setComponentEnabledSetting(
//            componentName,
//            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//            PackageManager.DONT_KILL_APP
//        )
        val permission = android.Manifest.permission.PROCESS_OUTGOING_CALLS
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), 1)
        val receiver = MyOutgoingCallHandler()
        val phone = intent.getStringExtra("extra_phone")
        if (phone != null) {
            Toast.makeText(baseContext, phone, Toast.LENGTH_LONG).show()
            receiver.onReceive(baseContext, intent)

        }

        val i = Intent(this, MainActivity2::class.java)
        startActivity(i)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            (15 * 60 * 1000).toLong(),
            pendingIntent
        )

        finish()
    }


}