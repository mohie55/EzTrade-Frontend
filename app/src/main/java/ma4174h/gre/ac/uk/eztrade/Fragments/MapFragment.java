package ma4174h.gre.ac.uk.eztrade.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ma4174h.gre.ac.uk.eztrade.R;
import pub.devrel.easypermissions.EasyPermissions;

public class MapFragment extends Fragment {

//    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
//    private MapView mapView;

    GoogleMap googleMap1;
    LatLng latLng;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //initialize map fragment
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_maps);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                //check location permissions
                String permission1 = Manifest.permission.ACCESS_FINE_LOCATION;
                String permission2 = Manifest.permission.ACCESS_COARSE_LOCATION;
                if (EasyPermissions.hasPermissions(getActivity(), permission1, permission2)) {
                        googleMap1 = googleMap;
                        Bundle args = getArguments();
                    if(args != null) {
                        showAddedItemOnMap();
                    };
                    googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            // you could the user's location or town



//                            MarkerOptions markerOptions = new MarkerOptions();
//                            markerOptions.position(latLng);
//                            markerOptions.title(latLng.latitude + " : " + latLng.longitude);
//                            googleMap1.clear();
//                            googleMap1.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                            googleMap1.addMarker(markerOptions);

                            // Call method which adds all saved items from the server to the map

                        }
                    });
                }
            }
        });

//        mapView = view.findViewById(R.id.product_map);
//        initGoogleMaps(savedInstanceState);

        return view;
    }

    private void showAddedItemOnMap() {

        latLng = getArguments().getParcelable("latlng");
        //point map view to the recently added item
        googleMap1.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("latLng.latitude + " : " + latLng.longitude");
//        googleMap1.clear();
//        googleMap1.addMarker(markerOptions);
    }




//    private void initGoogleMaps(Bundle savedInstanceState){
//        // *** IMPORTANT ***
//        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
//        // objects or sub-Bundles.
//        Bundle mapViewBundle = null;
//        if (savedInstanceState != null) {
//            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
//        }
//
//        mapView.onCreate(mapViewBundle);
//        mapView.getMapAsync(this);
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        if (mapFragment != null) {
//            mapFragment.getMapAsync(callback);
//        }
//    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
//        if (mapViewBundle == null) {
//            mapViewBundle = new Bundle();
//            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
//        }
//
//        mapView.onSaveInstanceState(mapViewBundle);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//
//
//
//    @Override
//    public void onPause() {
//        mapView.onPause();
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        mapView.onDestroy();
//        super.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }

//    @Override
//    public void onMapReady(GoogleMap map) {
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        map.setMyLocationEnabled(true);
//    }
}