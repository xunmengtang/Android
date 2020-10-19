package com.reaksmeyarun.pda.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reaksmeyarun.pda.R


@Suppress("DEPRECATED_IDENTITY_EQUALS", "DEPRECATION")
class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23
    private var mMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        checkPermission()

    }
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                MY_PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
            val mapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment!!.getMapAsync(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if (grantResults.lastIndex > 0
                    && grantResults[0] === PackageManager.PERMISSION_GRANTED
                ) {
                    val mapFragment =
                        supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                    mapFragment!!.getMapAsync(this)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        val mMap1 = mMap
        if (mMap1 != null) mMap1 else throw NullPointerException("Expression 'mMap' must not be null")
        mMap!!.setOnMyLocationChangeListener {
            val latlng = LatLng(11.5355911, 104.922504)
            val markerOptions = MarkerOptions()
            markerOptions.position(latlng)
            markerOptions.title("My Marker")
            mMap!!.clear()
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 20f)
            mMap!!.animateCamera(cameraUpdate)
            mMap!!.addMarker(markerOptions)
        }
    }
}