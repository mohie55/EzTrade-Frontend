package ma4174h.gre.ac.uk.eztrade.Retrofit;

import java.io.File;
import java.util.List;

import ma4174h.gre.ac.uk.eztrade.Models.Item;
import ma4174h.gre.ac.uk.eztrade.Responses.ItemResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.LoginResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitServices {


    @FormUrlEncoded
    @POST("/login")
    Call<LoginResponse> checkUserCredentials(
            @Field("email") String email,
            @Field("password") String password);


    @GET("/hello")
    Call<String> helloUser();

    @FormUrlEncoded
    @POST("/register")
    Call<RegisterResponse> registerUser(@Field("firstName") String firstName, @Field("lastName") String lastName,
                                        @Field("email") String email, @Field("password") String password);

    @POST("/save_item")
    Call<ItemResponse> saveItem(@Body Item item);

//    @FormUrlEncoded
//    @POST("/save_item")
//    Call<RegisterResponse> saveItem(@Field("title") String title, @Field("description") String description,
//                                    @Field("category") String category, @Field("price") String price,
//                                    @Field("imageFiles") List<File> imageFiles, @Field("latitude") String latitude,
//                                    @Field("longitude") String longitude);




}
