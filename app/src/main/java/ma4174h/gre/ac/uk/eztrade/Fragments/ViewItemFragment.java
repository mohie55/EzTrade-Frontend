package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import ma4174h.gre.ac.uk.eztrade.R;
import ma4174h.gre.ac.uk.eztrade.Responses.ImageUrlsResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.UserContactDetailsResponse;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitBuilder;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

class ViewItemFragment extends Fragment {

    private Retrofit retrofit;
    private RetrofitServices retrofitServices;

    private Double price;
    private String title;
    private String description;
    private int userId;
    private String sellerEmail;
    private String sellerFirstName;
    private int sellerReviews;
    private int itemId;
    private String searchQuery;
    private List<String> imageDownloadUrls;

    ImageSlider imageSlider;
    private TextView itemPriceTV;
    private TextView itemTitleTV;
    private TextView itemDescriptionTV;
    private TextView sellerEmailTV;
    private TextView sellerFirstNameTV;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageSlider = getView().findViewById(R.id.itemImageSlider);
        itemPriceTV = getView().findViewById(R.id.itemPriceTV);
        itemTitleTV = getView().findViewById(R.id.itemTitleTV);
        itemDescriptionTV = getView().findViewById(R.id.itemDescriptionTV);
        sellerFirstNameTV = getView().findViewById(R.id.sellerFirstNameTV);
        sellerEmailTV = getView().findViewById(R.id.sellerEmailTV);

        if (getArguments() != null) {
            Bundle args = getArguments();
            itemId = args.getInt("itemId");
            searchQuery = args.getString("searchQuery");
            getImageUrls(itemId);
        }

    }

    private void getImageUrls(int itemId) {

        if (itemId != 0) {
            Call<ImageUrlsResponse> call = retrofitServices.getImageUrls(itemId);

            call.enqueue(new Callback<ImageUrlsResponse>() {
                @Override
                public void onResponse(Call<ImageUrlsResponse> call, Response<ImageUrlsResponse> response) {

                    if (response.body() != null) {
                        if (response.body().getMessage().equalsIgnoreCase("success")) {
                            imageDownloadUrls = new ArrayList<>();
                            imageDownloadUrls.addAll(response.body().getListOfImageUrls());
                            getItemDetails();

                        } else if (response.body().getMessage().equalsIgnoreCase("failed")) {


                        }
                    }
                }

                @Override
                public void onFailure(Call<ImageUrlsResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getActivity(), "Error, couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    private void getItemDetails() {

        retrofit = RetrofitBuilder.getRetrofitInstance();
        retrofitServices = retrofit.create(RetrofitServices.class);

        if (itemId != 0) {
            Call<ItemResponse> call = retrofitServices.getItem(itemId);

            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {

                    if (response.body() != null) {
                        if (response.body().getMessage().equalsIgnoreCase("success")) {
                            Double price = response.body().getItem().getPrice();
                            String title = response.body().getItem().getTitle();
                            String description = response.body().getItem().getDescription();
                            int userId = response.body().getItem().getUserId();
                            getSellerDetails(userId);


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


    }

    private void getSellerDetails(int userId) {
        if (userId != 0) {
            Call<UserContactDetailsResponse> call = retrofitServices.getSellerDetails(userId);

            call.enqueue(new Callback<UserContactDetailsResponse>() {
                @Override
                public void onResponse(Call<UserContactDetailsResponse> call, Response<UserContactDetailsResponse> response) {

                    if (response.body() != null) {
                        if (response.body().getMessage().equalsIgnoreCase("success")) {
                            sellerFirstName = response.body().getFirstName();
                            sellerEmail = response.body().getEmail();
                            sellerReviews = response.body().getReviews();

                            showItemDetails(title, description, price, sellerFirstName, sellerEmail,sellerReviews);

                        } else if (response.body().getMessage().equalsIgnoreCase("failed")) {


                        }
                    }
                }

                @Override
                public void onFailure(Call<UserContactDetailsResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getActivity(), "Error, couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();

                }
            });
        }
    }


    private void showItemDetails(String title, String description, Double price, String sellerrFirstName , String sellerEmail, int reviews) {

        List<SlideModel> slideModels = new ArrayList<>();
        for (String imageUrl : imageDownloadUrls) {
            slideModels.add(new SlideModel(imageUrl, ScaleTypes.FIT));
        }
        imageSlider.setImageList(slideModels);
        itemPriceTV.setText("£" + price);
        itemTitleTV.setText(title);
        itemDescriptionTV.setText(description);
        sellerFirstNameTV.setText(sellerFirstName);
        sellerEmailTV.setText(sellerEmail);

    }
}