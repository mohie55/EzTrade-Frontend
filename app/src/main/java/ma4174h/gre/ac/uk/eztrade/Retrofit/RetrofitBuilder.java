package ma4174h.gre.ac.uk.eztrade.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static Retrofit retrofit;

    private RetrofitBuilder() {};


    public static Retrofit getRetrofitInstance(){
        if (retrofit == null) {
             retrofit = new Retrofit.Builder()
                     //                    .baseUrl("https://eztrade-mohie.herokuapp.com")
                    .baseUrl("http://192.168.0.10:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
