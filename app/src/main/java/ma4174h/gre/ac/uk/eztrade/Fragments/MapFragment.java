package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ma4174h.gre.ac.uk.eztrade.DTO.ItemLocationsDTO;
import ma4174h.gre.ac.uk.eztrade.R;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemLocationsResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemResponse;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitBuilder;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitServices;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapFragment extends Fragment {

//    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
//    private MapView mapView;

    GoogleMap googleMap;
    LatLng latLng;
    MarkerOptions markerOptions;
    String searchQuery;

    Retrofit retrofit;
    RetrofitServices retrofitServices;
    SearchView searchItemsBar;

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
                    MapFragment.this.googleMap = googleMap;

                    Bundle args = getArguments();
                    if (args != null) {
                        showAddedItemOnMap();
                    } else {
                        //Loads all markers for saved items
                        getAllItems();
                    }

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchItemsBar = (SearchView) getView().findViewById(R.id.searchItemsBar);
        searchItemsBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery = searchItemsBar.getQuery().toString();

                if (!searchQuery.isEmpty()) {
                    getItems(searchQuery);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        if (mapFragment != null) {
//            mapFragment.getMapAsync(callback);
//        }
    }

    private void getItems(String searchQuery) {

        retrofit = RetrofitBuilder.getRetrofitInstance();
        retrofitServices = retrofit.create(RetrofitServices.class);

        Call<ItemLocationsResponse> call = retrofitServices.getItemLocations(searchQuery);

        call.enqueue(new Callback<ItemLocationsResponse>() {
            @Override
            public void onResponse(Call<ItemLocationsResponse> call, Response<ItemLocationsResponse> response) {

                if (response.body().getMessage().equalsIgnoreCase("success")) {

                    List<ItemLocationsDTO> itemLocationsDTOList = response.body().getItemLocationsDTOList();
                    loadItemsOnMap(itemLocationsDTOList);

                } else if (response.body().getMessage().equalsIgnoreCase("failed")) {

                }
            }

            @Override
            public void onFailure(Call<ItemLocationsResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Error couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void loadItemsOnMap(List<ItemLocationsDTO> itemLocationsDTOList) {

        googleMap.clear();

        for (ItemLocationsDTO item : itemLocationsDTOList) {
            markerOptions = new MarkerOptions();
            latLng = new LatLng(item.getLatitude(), item.getLongitude());
            markerOptions.position(latLng);
            markerOptions.title(item.getTitle() + "            £" + item.getPrice().toString());
            googleMap.addMarker(markerOptions);
            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    showItemDetails(item.getItemId());
                }
            });
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }

    }

    private void showItemDetails(int itemId) {

            Bundle mapArgs = new Bundle();
            mapArgs.putString("searchQuery", searchQuery);
            mapArgs.putInt("itemId", itemId);
            ViewItemFragment viewItemFragment = new ViewItemFragment();
            viewItemFragment.setArguments(mapArgs);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_main, viewItemFragment).commit();

    }

    private void showAddedItemOnMap() {

        googleMap.clear();
        int itemId = 0;
        if (getArguments() != null) {
            latLng = getArguments().getParcelable("latlng");
            itemId = getArguments().getInt("itemId");
        }


        retrofit = RetrofitBuilder.getRetrofitInstance();
        retrofitServices = retrofit.create(RetrofitServices.class);
        MarkerOptions markerOptions = new MarkerOptions();

        if (itemId != 0) {
            Call<ItemResponse> call = retrofitServices.getItem(itemId);

            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {

                    if (response.body() != null) {
                        if (response.body().getMessage().equalsIgnoreCase("success")) {
                            Double price = response.body().getItem().getPrice();
                            String title = response.body().getItem().getTitle();

                            markerOptions.title(title + "              £" + price.toString());
                            markerOptions.position(latLng);
                            googleMap.addMarker(markerOptions);
                        } else if (response.body().getMessage().equalsIgnoreCase("failed")) {


                        }
                    }
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getActivity(), "Error, couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();

                }
            });
        }

        //point map view to the recently added item
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        markerOptions.position(latLng);
        googleMap.addMarker(markerOptions);
    }

    private void getAllItems() {

        retrofit = RetrofitBuilder.getRetrofitInstance();
        retrofitServices = retrofit.create(RetrofitServices.class);

        Call<ItemLocationsResponse> call = retrofitServices.getAllItemLocations();

        call.enqueue(new Callback<ItemLocationsResponse>() {
            @Override
            public void onResponse(Call<ItemLocationsResponse> call, Response<ItemLocationsResponse> response) {

                if (response.body().getMessage().equalsIgnoreCase("success")) {

                    List<ItemLocationsDTO> itemLocationsDTOList = response.body().getItemLocationsDTOList();
                    loadItemsOnMap(itemLocationsDTOList);

                } else if (response.body().getMessage().equalsIgnoreCase("failed")) {

                }
            }

            @Override
            public void onFailure(Call<ItemLocationsResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Error couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();

            }
        });
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