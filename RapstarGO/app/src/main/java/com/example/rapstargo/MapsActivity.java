package com.example.rapstargo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // permission
    static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 0;


    private GoogleMap mMap; // configuration inside the OnMapReady
    private Location userLocation;
    private LocationRequest mLocationRequest; // configuration inside the OnCreate
    private FusedLocationProviderClient mFusedLocationClient; // configuration inside the OnCreate
    private Marker mUsermarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // FusedLocationClient configuration
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Location Request configuration
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // need it for GPS usage
        mLocationRequest.setInterval(5000); // 10 seconds interval maximum
        mLocationRequest.setSmallestDisplacement(1.f); // need 10 meters movement for update

        // userLocation initialisation
        userLocation = new Location("");

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
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null); // Setup interval update
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

    /* LISTENER / CALLBACK */

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {

            Log.i("Alexis-LocationCallback", "Interval is called" );

            if (locationResult != null) {

               Location location = locationResult.getLastLocation();

                if (location != null && (userLocation.getLatitude() != location.getLatitude()
                    || userLocation.getLongitude() != location.getLongitude()
                    || userLocation.getAltitude() != location.getAltitude()) ) {

                    Log.i("Alexis-GetLastLocation", location.toString() );
                    Log.i("Alexis-OldLocation", userLocation.toString() );

                    userLocation = location;

                    // Add a marker in userLocation and move the camera
                    LatLng userLatLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());

                    if(mUsermarker != null)
                        mUsermarker.remove();

                    mUsermarker = mMap.addMarker(new MarkerOptions().position(userLatLng).icon(BitmapDescriptorFactory.fromAsset("player.png")));


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
        }
    };

}
