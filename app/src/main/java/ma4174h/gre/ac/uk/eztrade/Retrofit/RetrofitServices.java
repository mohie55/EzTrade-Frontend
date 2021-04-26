package ma4174h.gre.ac.uk.eztrade.Retrofit;

import ma4174h.gre.ac.uk.eztrade.Responses.LoginResponse;
import ma4174h.gre.ac.uk.eztrade.Responses.RegisterResponse;
import retrofit2.Call;
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
    Call<RegisterResponse> registerUser(@Field("firstName") String firstName, @Field("lastName") String lastName, @Field("email") String email, @Field("password") String password);
}
