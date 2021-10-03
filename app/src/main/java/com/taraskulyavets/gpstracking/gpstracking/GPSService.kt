package com.taraskulyavets.gpstracking.gpstracking

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
import com.taraskulyavets.gpstracking.R
import com.taraskulyavets.gpstracking.gpstracking.model.MyLocation
import com.taraskulyavets.gpstracking.main.MainActivity
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.lang.Exception
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class GPSService : Service(), LocationListener {

    private val locManager by lazy { getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    private val locationRepository by inject<LocationRepository>()

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        super.onDestroy()
        locManager.removeUpdates(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("asdasdasdjkhkjsd")
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
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                30000,
                100f,
                this)
        return START_NOT_STICKY
    }

    override fun onLocationChanged(loc: Location) {
        println(loc)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val time = ZonedDateTime.now()
                locationRepository.saveLocation(
                    MyLocation(
                        loc.latitude,
                        loc.longitude,
                        time.format(DateTimeFormatter.ISO_INSTANT)
                    )
                )
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