package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma4174h.gre.ac.uk.eztrade.LoadingDialog;
import ma4174h.gre.ac.uk.eztrade.Models.Item;
import ma4174h.gre.ac.uk.eztrade.Models.ItemImage;
import ma4174h.gre.ac.uk.eztrade.R;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemImageResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemResponse;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitBuilder;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitServices;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;

public class AddListingFragment extends Fragment {

    private static final int REQUEST_CODE_CHOOSE = 1;
//    private static final int RESULT_OK = ;
    Retrofit retrofit;
    RetrofitServices retrofitServices;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private Button categoryButton;
    private Button submitButton;
    private EditText descriptionEditTxt;
    private EditText titleEditTxt;
    private EditText priceEditTxt;
    private TextView categoryTextView;
    private LoadingDialog loadingDialog;
    private FusedLocationProviderClient fusedLocationClient;
    private Integer userId;
    private String category;
    private String description;
    private String title;
    private Double price;
    private Double latitude;
    private Double longitude;
    private LatLng latLng;

    private HashMap<Integer, Uri> selectedPhotos;
    private ArrayList<Bitmap> finalSelectedPhotos;
    private ArrayList<File> imageFiles;
    private List<ImageView> imageViews;

    private Boolean itemSaved;
    private StorageReference storageReference;
    private StorageTask uploadTask;
    private ArrayList<String> imageDownloadUrls;
    private int itemId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_listing, container, false);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());


    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString("title",title);
//        outState.putString("description",description);
//        outState.putString("category",category);
//        outState.putDouble("price",price);
//        outState.putSerializable("selectedPhotos",selectedPhotos);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        imageView1 = (ImageView) getView().findViewById(R.id.image_view1);
        imageView2 = (ImageView) getView().findViewById(R.id.image_view2);
        imageView3 = (ImageView) getView().findViewById(R.id.image_view3);
        imageView4 = (ImageView) getView().findViewById(R.id.image_view4);
        imageView5 = (ImageView) getView().findViewById(R.id.image_view5);

        imageViews = new ArrayList<>();
        selectedPhotos = new HashMap<>();
        loadingDialog = new LoadingDialog(getContext(), getActivity());

        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        imageViews.add(imageView5);

        categoryButton = (Button) getView().findViewById(R.id.categoryButton);
        categoryTextView = (TextView) getView().findViewById(R.id.categoryTextView);
        submitButton = (Button) getView().findViewById(R.id.submitButton);
        priceEditTxt = (EditText) getView().findViewById(R.id.priceEditTxt);
        descriptionEditTxt = (EditText) getView().findViewById(R.id.descriptionEditTxt);
        titleEditTxt = (EditText) getView().findViewById(R.id.titleEditTxt);



        Bundle args = getArguments();

        if (args != null) {
            // Restore last state of the fragment
            String title2 = args.getString("title", "");
            String description2 = args.getString("description", "");
            Double price2 = args.getDouble("price");
            String category2 = args.getString("category", "");
            HashMap<Integer, Uri> selectedPhotos2 = (HashMap<Integer, Uri>) args.getSerializable("selectedPhotos");
//            ArrayList<Drawable> selectedPhotos2 = (ArrayList<Drawable>) args.getSerializable("selectedPhotos");

            category = getArguments().getString("category");
            categoryTextView.setText(category);
            titleEditTxt.setText(title2);
            descriptionEditTxt.setText(description2);
            priceEditTxt.setText(price2.toString());
            categoryTextView.setText(category2);
            selectedPhotos.putAll(selectedPhotos2);


//

            int i = 0;
            for (HashMap.Entry<Integer, Uri> entry : selectedPhotos.entrySet()) {
                imageViews.get(i).setImageURI(entry.getValue());
                i++;
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }


        }

        //setting image view listeners
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoGallery(1);
            }

        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoGallery(2);
            }

        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoGallery(3);
            }

        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoGallery(4);
            }

        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoGallery(5);
            }

        });

        //selecting a category
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle args = new Bundle();
                title = titleEditTxt.getText().toString().trim();
                if (!priceEditTxt.getText().toString().trim().isEmpty()) {
                    price = Double.parseDouble(priceEditTxt.getText().toString().trim());
                    args.putDouble("price", price);
                }
                description = descriptionEditTxt.getText().toString().trim();
                ChooseCategoryFragment chooseCategoryFragment = new ChooseCategoryFragment();

                args.putString("title", title);
                args.putString("description", description);
                args.putString("category", category);
                args.putSerializable("selectedPhotos", selectedPhotos);
                chooseCategoryFragment.setArguments(args);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_main, chooseCategoryFragment).commit();

            }
        });

        //submit listing
        submitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                //check all fields are entered and save item
                if (checkFields()) {

                    String permission1 = Manifest.permission.ACCESS_FINE_LOCATION;
                    String permission2 = Manifest.permission.ACCESS_COARSE_LOCATION;
                    if (EasyPermissions.hasPermissions(getActivity(), permission1, permission2)) {

                        //get the device location
                        fusedLocationClient.getLastLocation()
                                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {

                                        if (location != null) {
                                            //get the coordinates of the device's location
                                            latitude = location.getLatitude();
                                            longitude = location.getLongitude();
                                            latLng = new LatLng(latitude, longitude);
                                            //Set item variables
                                            //set strings to use when saving the item
                                            title = titleEditTxt.getText().toString().trim();
                                            price = Double.parseDouble(priceEditTxt.getText().toString().trim());
                                            description = descriptionEditTxt.getText().toString().trim();

                                            //Get the logged in user
//                                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//                                            Gson gson = new Gson();
//                                            String json = sharedPreferences.getString("user", "");
//                                            User user = gson.fromJson(json, User.class);
//                                            userId = user.getId();

                                            // save the item/listing
                                            Item item = new Item(title, description, category, price, latitude, longitude, 1);
                                            saveItem(item);
                                            submitButton.setEnabled(true);

                                        }
                                    }
                                });

                    } else {
                        // Request permission
                        getLocationPermission();

                    }


                }
            }

        });

    }

    private void getLocationPermission() {

        String permission1 = Manifest.permission.ACCESS_FINE_LOCATION;
        String permission2 = Manifest.permission.ACCESS_COARSE_LOCATION;
        EasyPermissions.requestPermissions(this, "Please accept permission to access your location to add approximate location of item",
                1, permission1, permission2);

    }

    private boolean checkFields() {

        if (category == null || category.isEmpty()) {
            categoryTextView.setError("Please choose a category.");
            return false;
        } else if (descriptionEditTxt.getText().toString().trim().length() < 1) {
            descriptionEditTxt.setError("Description minimum 50 characters.");
            return false;

//        } else if (priceEditTxt.getText().toString().trim().isEmpty() || Double.parseDouble(priceEditTxt.getText().toString().trim()) < 0.01) {
//            //ask if they are sure they want to sell for free
//            priceEditTxt.setError("Please enter a price.");
//            return false;

        } else if (titleEditTxt.getText().toString().trim().length() < 1) {
            titleEditTxt.setError("Title minimum 5 characters");
            return false;
        } else if (selectedPhotos.isEmpty()) {
            Toast.makeText(getContext(), "Please add at least one image.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }


    private void saveItem(Item item) {

        //Start loading animation
        submitButton.setEnabled(false);
        loadingDialog.startProgressDialog();

        //save item and upload to server
        retrofit = RetrofitBuilder.getRetrofitInstance();
        retrofitServices = retrofit.create(RetrofitServices.class);

        Call<ItemResponse> call = retrofitServices.saveItem(item);

        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("success")) {
                        itemId = response.body().getItem().getId();
                        // upload images to firebase
                        uploadImagesToFB();


                    } else if (response.body().getMessage().equalsIgnoreCase("failed")) {
                        Toast.makeText(getActivity(), "Listing item failed", Toast.LENGTH_LONG).show();


                    }
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Error couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();
                t.printStackTrace();

            }
        });

//        loadingDialog.dismissDialog();

    }

    //    @AfterPermissionGranted(1)
    private void openPhotoGallery(int imageViewNum) {
        String permissions = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (EasyPermissions.hasPermissions(getActivity(), permissions)) {
            // open gallery
            ImagePicker.Companion.with(this)
//                    .crop()
                    .galleryOnly()
                    .start(imageViewNum);

        } else {
            // Request permission
            EasyPermissions.requestPermissions(this, "Please accept permission to access photo gallery to select a photo", 1, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri imageUri = data.getData();


        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case 1:
                    imageView1.setImageURI(imageUri);
                    break;
                case 2:
                    imageView2.setImageURI(imageUri);
                    break;
                case 3:
                    imageView3.setImageURI(imageUri);
                    break;
                case 4:
                    imageView4.setImageURI(imageUri);
                    break;
                case 5:
                    imageView5.setImageURI(imageUri);
                    break;

            }
            selectedPhotos.put(requestCode - 1, imageUri);

            System.out.println(selectedPhotos.size());

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        }

    }

    private void uploadImagesToFB() {

        storageReference = FirebaseStorage.getInstance().getReference().child("Images");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        databaseReference = database.getInstance().getReference("test");
        imageDownloadUrls = new ArrayList<>();

        for (HashMap.Entry<Integer, Uri> entry : selectedPhotos.entrySet()) {

            final StorageReference imageUpload = storageReference.child(System.currentTimeMillis() + "." + entry.getValue().getLastPathSegment());

            uploadTask = imageUpload.putFile(entry.getValue()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

//                            imageUpload.getDownloadUrl().toString()
//                            imageDownloadUrls.add(imageUpload.getDownloadUrl().toString());
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                imageDownloadUrls.add(downloadUri.toString());
                                if (imageDownloadUrls != null && imageDownloadUrls.size() == selectedPhotos.size() && itemId != 0) {
                                    saveItemImagesInDB(itemId);
                                }

//                                Log.e(TAG, "then: " + downloadUri.toString() + "list size" + imageDownloadUrls.size());


                            } else {
                                Toast.makeText(getActivity(), "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

        }



    }

    private void saveItemImagesInDB(int itemId) {

        //Save image download url to the database along with the item id as a reference
        for (String url : imageDownloadUrls) {

            ItemImage itemImage = new ItemImage(itemId, url);

            retrofit = RetrofitBuilder.getRetrofitInstance();
            retrofitServices = retrofit.create(RetrofitServices.class);

            Call<ItemImageResponse> call = retrofitServices.saveItemImage(itemImage);

            call.enqueue(new Callback<ItemImageResponse>() {
                @Override
                public void onResponse(Call<ItemImageResponse> call, Response<ItemImageResponse> response) {

                    if (response.body() != null) {
                        if (response.body().getMessage().equalsIgnoreCase("success")) {
                            Toast.makeText(getActivity(), "Item Listed Successfully", Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissDialog();
                            openHomePage(itemId);

                        } else if (response.body().getMessage().equalsIgnoreCase("failed")) {
                            Toast.makeText(getActivity(), "Listing item failed", Toast.LENGTH_LONG).show();

                        }
                    }
                }

                @Override
                public void onFailure(Call<ItemImageResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getActivity(), "Error, couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();

                }
            });
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

//    private void SendLink(String downloadUrl) {
//        ItemImageUpload itemImageUpload = new ItemImageUpload(downloadUrl,)
//    }

    private void openHomePage(int itemId) {

        Bundle mapArgs = new Bundle();
        mapArgs.putParcelable("latlng", latLng);
        mapArgs.putInt("itemId", itemId);
        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(mapArgs);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, mapFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

//        listingArgs = new Bundle();

//        title = titleEditTxt.getText().toString().trim();
//        if (!priceEditTxt.getText().toString().trim().isEmpty()) {
//            price = Double.parseDouble(priceEditTxt.getText().toString().trim());
//            listingArgs.putDouble("price",price);
//        }
//        description = descriptionEditTxt.getText().toString().trim();

//        listingArgs.putString("title",title);
//        listingArgs.putString("description",description);
//        listingArgs.putString("category",category);
//        listingArgs.putSerializable("selectedPhotos",selectedPhotos);
    }
}

