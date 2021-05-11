package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.commons.validator.routines.EmailValidator;

import ma4174h.gre.ac.uk.eztrade.Activities.LoginActivity;
import ma4174h.gre.ac.uk.eztrade.Activities.MainActivity;
import ma4174h.gre.ac.uk.eztrade.Models.User;
import ma4174h.gre.ac.uk.eztrade.R;
import ma4174h.gre.ac.uk.eztrade.Responses.LoginResponse;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitBuilder;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {


    private Button loginBtn;
    private EditText emailEditTxtLogin;
    private EditText passwordEditTxtLogin;
    private TextView forgotPass;
    public static final String SHARED_PREFERENCES = "SharedPreferences";



    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        loginBtn = root.findViewById(R.id.loginBtn);
        emailEditTxtLogin = root.findViewById(R.id.emailEditTxtLogin);
        passwordEditTxtLogin = root.findViewById(R.id.passwordEditTxtLogin);
        forgotPass = root.findViewById(R.id.forgotPassTextView);

        loginBtn.setTranslationY(300);
        loginBtn.setAlpha(0);
        loginBtn.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        emailEditTxtLogin.setTranslationY(300);
        emailEditTxtLogin.setAlpha(0);
        emailEditTxtLogin.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        passwordEditTxtLogin.setTranslationY(300);
        passwordEditTxtLogin.setAlpha(0);
        passwordEditTxtLogin.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        forgotPass.setTranslationY(300);
        forgotPass.setAlpha(0);
        forgotPass.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Validating fields
                if (!validateEmail(emailEditTxtLogin.getText().toString().trim())) {
                    emailEditTxtLogin.setError("Invalid Email");
                } else {

                    Retrofit retrofit = RetrofitBuilder.getRetrofitInstance();
                    RetrofitServices retrofitServices = retrofit.create(RetrofitServices.class);

                    Call<LoginResponse> call = retrofitServices.checkUserCredentials(emailEditTxtLogin.getText().toString().trim(), passwordEditTxtLogin.getText().toString());

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                            if (response.body().getMessage().equalsIgnoreCase("success")) {
                                Toast.makeText(getActivity(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                openHomePage(response.body().getUser());

                            } else if (response.body().getMessage().equalsIgnoreCase("failed")) {
                                Toast.makeText(getActivity(), "Email and Password don't match", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(getActivity(), "Error couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();

                        }
                    });
                }

            }

        });
        return root;
    }

    public void openHomePage(User user) {
        // save user in shared preferences (locally)

//        SharedPreferences sharedPref = getActivity().getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
//        SharedPreferences.Editor prefsEditor = sharedPref.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        prefsEditor.putString("user", json);
//        prefsEditor.commit();

        SharedPreferences  sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);

        User loggedUser = user;
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(loggedUser);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
        Intent intent = new Intent(getActivity(),MainActivity.class);
        getActivity().startActivity(intent);

    }



    public static boolean validateEmail(String email) {
        // create the EmailValidator instance
        EmailValidator validator = EmailValidator.getInstance();

        // check for valid email addresses using isValid method
        return validator.isValid(email);
    }
}