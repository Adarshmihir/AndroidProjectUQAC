package com.example.rapstargo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    // permission
    static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 0;


    private GoogleMap mMap; // configuration inside the OnMapReady
    private Location userLocation;
    private LocationRequest mLocationRequest; // configuration inside the OnCreate
    private FusedLocationProviderClient mFusedLocationClient; // configuration inside the OnCreate
    private Marker mUsermarker;
    private List<Hub> mHubList = new ArrayList<Hub>();

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
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(false);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

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

        //Get HubList -> Change for API request
        mHubList.add(new Hub(new LocationHub(48.4203, -71.0526, 5.0)));
        mHubList.add(new Hub(new LocationHub(48.4204752, -71.0448095, 5.0)));

        //get the position with custom listener
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null); // Setup interval update

        //add listener on marker
        mMap.setOnMarkerClickListener(this);

    }

    private void CameraCenterOnPlayer(LatLng pos) {


        /* Camera settings -> https://stackoverflow.com/questions/38323724/how-does-pok%C3%A8mon-go-uses-custom-google-map-using-google-map-api  */
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(pos)
                .zoom(18)
                .tilt(0)
                .bearing(0)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    private void HubMarker() {

        //Place marker for all hubs
        int nbVisibleHub = 0;

        for (Hub hub : mHubList) {

            Log.i("Alexis-hubForeach", hub.getLocation().toString() );

            LatLng hubPos = new LatLng(hub.getLocation().getLatitude(), hub.getLocation().getLongitude());

            if(mMap.getProjection().getVisibleRegion().latLngBounds.contains(hubPos) && hub.getmMarker() == null) {

                hub.setmMarker(mMap.addMarker(new MarkerOptions().position(hubPos).zIndex(1.0f)));
                nbVisibleHub++;

            }
            else if(mMap.getProjection().getVisibleRegion().latLngBounds.contains(hubPos) && hub.getmMarker() != null) {
                nbVisibleHub++;

            }
            else if(!mMap.getProjection().getVisibleRegion().latLngBounds.contains(hubPos) && hub.getmMarker() != null) {
                hub.removeMarker();

            }

        }

        Log.i("Alexis-NbVisibleHub", Integer.toString(nbVisibleHub));

    }

    /* LISTENER / CALLBACK */

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {

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

                    mUsermarker = mMap.addMarker(new MarkerOptions().position(userLatLng).icon(BitmapDescriptorFactory.fromAsset("player.png")).zIndex(.5f));

                    CameraCenterOnPlayer(userLatLng);
                    HubMarker();
                }

            }
        }
    };

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

    @Override
    public boolean onMarkerClick(final Marker marker) {

        if(marker.equals(mUsermarker)){

            Log.i("Alexis-MarkerClick", "User marker");
        }
        else{

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Do you want join this hub ?");
            // alert.setMessage("Message");

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {

                    Log.i("Alexis-MarkerClick", "User join this hub");

                }

            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {

                            Log.i("Alexis-MarkerClick", "User not joined this hub");
                            CameraCenterOnPlayer(mUsermarker.getPosition());

                        }

                    });

            alert.show();

        }

        return false;
    }
}
