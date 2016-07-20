package com.example.keigo.defencer;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by keigo on 2016/04/08.
 */
public class GetLocationActivity extends AppCompatActivity /*implements LocationManager*/{
//
//   private LocationManager locationManager;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_get_gps);
//
//        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
//    }
//
//    @Override
//    public void onResume(){
//        if (locationManager != null){
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                    3000,
//                    10,
//                    );
//        }
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause(){
//        if (locationManager != null){
//            locationManager.removeUpdates(this);
//        }
//    }
//
//    @Override
//    public void onLocationChanged(Location location){
//        Log.d("Lati",String.valueOf(location.getLatitude()));
////        Log.d("Long",String.valueOf(location.getLongitude()));
//    }
//
//    @Override
//    public void onProviderDisabed(String provider){}
//
//    @Override
//    public void onProciderEnabled(String provider){}
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras){
//        switch (status){
//            case LocationProvider.AVAILABLE:
//                Log.d("status","AWAILABLE");
//                break;
//            case LocationProvider.OUT_OF_SERVICE:
//                Log.d("status","OUT OF SERVICE");
//                break;
//            case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                Log.d("status","TEMPORARILY UNAVAIBLE");
//                break;
//        }
//    }
}
