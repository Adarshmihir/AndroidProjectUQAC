package com.example.rapstargo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location userLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setScrollGesturesEnabled(false); // a voir pour autoriser la rotation
        mMap.getUiSettings().setZoomControlsEnabled(false);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LoadMapData();

        }
        else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }

        }

    }

    @SuppressLint("MissingPermission")
    private void LoadMapData() {

        Log.i("PERMISSION LOG", "PERMISSION GRANTED");

        //mMap.setMyLocationEnabled(true);

        // get the position with custom listener
        mFusedLocationClient.getLastLocation().addOnSuccessListener(getLocationListner);

    }

    // PERMISSION REQUEST
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            // location permission
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {

                Log.i("PERMISSION LOG", "PERMISSION CHECK");


                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    LoadMapData(); // permission granted

                }

                break;
            }

        }

    }

    /* LISTENER */

    private OnSuccessListener<Location> getLocationListner = new OnSuccessListener<Location>() {

        @Override
        public void onSuccess(Location location) {

            Log.i("getLocation", location.toString() );
            userLocation = location;

            if (userLocation != null) {

                // Add a marker in userLocation and move the camera
                LatLng userLatLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
                //mMap.addMarker(new MarkerOptions().position(userLatLng).icon(BitmapDescriptorFactory.fromAsset("test.png")));


                /* Camera settings -> https://stackoverflow.com/questions/38323724/how-does-pok%C3%A8mon-go-uses-custom-google-map-using-google-map-api  */
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(userLatLng)
                        .zoom(18)
                        .tilt(0f)
                        .bearing(0)
                        .build();

                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

        }
    };

}
