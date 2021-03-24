package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.apache.commons.validator.routines.EmailValidator;

import ma4174h.gre.ac.uk.eztrade.Responses.LoginResponse;
import ma4174h.gre.ac.uk.eztrade.R;
import ma4174h.gre.ac.uk.eztrade.RetrofitBuilder;
import ma4174h.gre.ac.uk.eztrade.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginFragment extends Fragment {


    private Button loginBtn;
    private EditText emailEditTxt;
    private EditText passwordEditTxt;
    private TextView forgotPass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        loginBtn = root.findViewById(R.id.loginBtn);
        emailEditTxt = root.findViewById(R.id.emailEditTxt);
        passwordEditTxt = root.findViewById(R.id.passwordEditTxt);
        forgotPass = root.findViewById(R.id.forgotPassTextView);

        loginBtn.setTranslationY(300);
        loginBtn.setAlpha(0);
        loginBtn.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        emailEditTxt.setTranslationY(300);
        emailEditTxt.setAlpha(0);
        emailEditTxt.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        passwordEditTxt.setTranslationY(300);
        passwordEditTxt.setAlpha(0);
        passwordEditTxt.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        forgotPass.setTranslationY(300);
        forgotPass.setAlpha(0);
        forgotPass.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validating fields
                if (!validateEmail(emailEditTxt.getText().toString().trim()) && !passwordEditTxt.getText().toString().trim().equals("")) {
                    if (validateEmail(emailEditTxt.getText().toString().trim())) {
                        if (passwordEditTxt.getText().toString().trim().length() < 8) {
                            passwordEditTxt.setError("Minimum 8 characters");
                        } else {

                            Retrofit retrofit = RetrofitBuilder.getRetrofitInstance();

                            RetrofitServices retrofitServices = retrofit.create(RetrofitServices.class);
                            Call<LoginResponse> call = retrofitServices.checkUserCredentials(emailEditTxt.toString().trim(), passwordEditTxt.toString().trim());

                            call.enqueue(new Callback<LoginResponse>() {
                                @Override
                                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                    LoginResponse message = response.body();

                                    if (response.body().getMessage().equals("success")) {
                                        System.out.println(response.code());
                                        Toast.makeText(getActivity(),"Logged in Successfully",Toast.LENGTH_SHORT).show();

                                    } else if (response.body().getMessage().equals("failed")) {
                                        Toast.makeText(getActivity(),"Email and Password don't match",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<LoginResponse> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });
                        }

                    } else if (!validateEmail(emailEditTxt.getText().toString().trim())) {
                        emailEditTxt.setError("Invalid Email");
                    }

                } else if (emailEditTxt.getText().toString().trim().equals("")) {
                    emailEditTxt.setError("Enter Email");
                } else if (passwordEditTxt.getText().toString().trim().equals("")) {
                    passwordEditTxt.setError("Enter Password");
                }

            }
        });

        return root;

    }

    public static boolean validateEmail(String email) {
        // create the EmailValidator instance
        EmailValidator validator = EmailValidator.getInstance();

        // check for valid email addresses using isValid method
        return validator.isValid(email);
    }
}
