package ma4174h.gre.ac.uk.eztrade.Retrofit;

import ma4174h.gre.ac.uk.eztrade.Models.Item;
import ma4174h.gre.ac.uk.eztrade.Models.ItemImage;
import ma4174h.gre.ac.uk.eztrade.Responses.ImageUrlsResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemImageResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemLocationsResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.LoginResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.RegisterResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.UserContactDetailsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitServices {


    @FormUrlEncoded
    @POST("/login")
    Call<LoginResponse> checkUserCredentials(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("/register")
    Call<RegisterResponse> registerUser(@Field("firstName") String firstName, @Field("lastName") String lastName,
                                        @Field("email") String email, @Field("password") String password);

    @POST("/saveItem")
    Call<ItemResponse> saveItem(@Body Item item);

    @POST("/saveItemImage")
    Call<ItemImageResponse> saveItemImage(@Body ItemImage itemImage);

    @GET("/getAllItemLocations")
    Call<ItemLocationsResponse> getAllItemLocations();


    @GET("/getItemLocations")
    Call<ItemLocationsResponse> getItemLocations(@Query("searchQuery") String searchQuery);

    @GET("/getItem")
    Call<ItemResponse> getItem(@Query("itemId") int itemId);

    @GET("/getSellerDetails")
    Call<UserContactDetailsResponse> getSellerDetails(int userId);

    @GET("/getImageUrls")
    Call<ImageUrlsResponse> getImageUrls(int itemId);
}
