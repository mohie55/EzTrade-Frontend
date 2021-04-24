package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import ma4174h.gre.ac.uk.eztrade.Responses.RegisterResponse;
import ma4174h.gre.ac.uk.eztrade.R;
import ma4174h.gre.ac.uk.eztrade.RetrofitBuilder;
import ma4174h.gre.ac.uk.eztrade.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static ma4174h.gre.ac.uk.eztrade.Fragments.LoginFragment.validateEmail;

public class RegisterFragment extends Fragment {

    private Button registerBtn;
    private EditText emailEditTxt;
    private EditText passwordEditTxt;
    private EditText confirmPasswordEditTxt;
    private EditText firstNameEditTxt;
    private EditText lastNameEditTxt;
    ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_register, container, false);

        registerBtn = root.findViewById(R.id.registerBtn);
        emailEditTxt = root.findViewById(R.id.emailRegEditTxt);
        passwordEditTxt = root.findViewById(R.id.passwordRegEditTxt);
        confirmPasswordEditTxt = root.findViewById(R.id.confirmPasswordEditTxt);
        firstNameEditTxt = root.findViewById(R.id.firstNameEditTxt);
        lastNameEditTxt = root.findViewById(R.id.lastNameEditTxt);

        registerBtn.setTranslationY(300);
        registerBtn.setAlpha(0);
        registerBtn.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        emailEditTxt.setTranslationY(300);
        emailEditTxt.setAlpha(0);
        emailEditTxt.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        passwordEditTxt.setTranslationY(300);
        passwordEditTxt.setAlpha(0);
        passwordEditTxt.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        confirmPasswordEditTxt.setTranslationY(300);
        confirmPasswordEditTxt.setAlpha(0);
        confirmPasswordEditTxt.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        firstNameEditTxt.setTranslationY(300);
        firstNameEditTxt.setAlpha(0);
        firstNameEditTxt.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        lastNameEditTxt.setTranslationY(300);
        lastNameEditTxt.setAlpha(0);
        lastNameEditTxt.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validating fields
                if (!validateEmail(emailEditTxt.getText().toString().trim())) {
                    emailEditTxt.setError("Invalid Email");
                } else if (passwordEditTxt.getText().toString().length() < 8) {
                    passwordEditTxt.setError("Minimum 8 characters");
                } else if (firstNameEditTxt.getText().toString().trim().equals("")) {
                    firstNameEditTxt.setError("Enter a First Name");
                } else if (lastNameEditTxt.getText().toString().trim().equals("")) {
                    lastNameEditTxt.setError("Enter a Last Name");
                } else if (!confirmPasswordEditTxt.getText().toString().equals(passwordEditTxt.getText().toString())) {
                    passwordEditTxt.setError("Passwords don't match");
                    confirmPasswordEditTxt.setError("Passwords don't match");
                    passwordEditTxt.setText("");
                    confirmPasswordEditTxt.setText("");
                } else {

                    Retrofit retrofit = RetrofitBuilder.getRetrofitInstance();
                    RetrofitServices retrofitServices = retrofit.create(RetrofitServices.class);

                    Call<RegisterResponse> call = retrofitServices.registerUser(firstNameEditTxt.getText().toString().trim(), lastNameEditTxt.getText().toString().trim(),
                            emailEditTxt.getText().toString().trim(), passwordEditTxt.getText().toString().trim());

                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            RegisterResponse message = response.body();

                            if (response.body().getMessage().equals("success")) {
                                System.out.println(response.code());
                                Toast.makeText(getActivity(), "Registered Successfully!", Toast.LENGTH_LONG).show();
                                firstNameEditTxt.setText("");
                                lastNameEditTxt.setText("");
                                emailEditTxt.setText("");
                                firstNameEditTxt.setText("");
                                passwordEditTxt.setText("");
                                confirmPasswordEditTxt.setText("");
                                //Go to login fragment
                                viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);
                                viewPager.setCurrentItem(0);

                            } else if (response.body().getMessage().equals("failed")) {
                                System.out.println("error " + response.errorBody() + "   " + response.code());
                                Toast.makeText(getActivity(), "Error Occurred, Please Retry.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(getActivity(), "Error couldn't connect to server, Please Retry.", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
                    });
        return root;
    }
}
