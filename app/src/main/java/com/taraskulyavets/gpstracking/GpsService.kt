package com.taraskulyavets.gpstracking

import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class GpsService : Service(), LocationListener {

    private val locManager by lazy { getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        super.onDestroy()
        locManager.removeUpdates(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, "777")
            .setContentTitle("GPS tracking")
            .setContentText("Description")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            println("No permission")
            return START_NOT_STICKY
        }

//        val criteria = Criteria();
//        criteria.accuracy = Criteria.ACCURACY_FINE;
//        criteria.isCostAllowed = false;
//        val providerName = locManager.getBestProvider(criteria, true);
//        if (providerName != null) {
//            println(null)
//        }
        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
            30_000L,
            100f,
            this)
        return START_NOT_STICKY
    }

    override fun onLocationChanged(loc: Location) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ClientHTTP.client.post("http://vladar.tk:3000") {
                    headers {
                        append(HttpHeaders.ContentType, "application/json")
                    }
                    body = MyLocation(loc.latitude, loc.longitude)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String) {
    }

    override fun onProviderDisabled(provider: String) {
    }
}