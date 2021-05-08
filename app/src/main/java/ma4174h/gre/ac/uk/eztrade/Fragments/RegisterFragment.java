package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import ma4174h.gre.ac.uk.eztrade.Responses.RegisterResponse;
import ma4174h.gre.ac.uk.eztrade.R;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitBuilder;
import ma4174h.gre.ac.uk.eztrade.Retrofit.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static ma4174h.gre.ac.uk.eztrade.Fragments.LoginFragment.validateEmail;

public class RegisterFragment extends Fragment {

    private Button registerBtn;
    private EditText emailEditTxtRegister;
    private EditText passwordEditTxtRegister;
    private EditText confirmPasswordEditTxt;
    private EditText firstNameEditTxt;
    private EditText lastNameEditTxt;
    private EditText phoneNumberEditTxtRegister;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_register, container, false);

        registerBtn = root.findViewById(R.id.registerBtn);
        emailEditTxtRegister = root.findViewById(R.id.emailEditTxtRegister);
        passwordEditTxtRegister = root.findViewById(R.id.passwordEditTxtRegister);
        confirmPasswordEditTxt = root.findViewById(R.id.confirmPasswordEditTxt);
        firstNameEditTxt = root.findViewById(R.id.firstNameEditTxt);
        lastNameEditTxt = root.findViewById(R.id.lastNameEditTxt);
        phoneNumberEditTxtRegister = root.findViewById(R.id.phoneNumberEditTxtRegister);

        registerBtn.setTranslationY(300);
        registerBtn.setAlpha(0);
        registerBtn.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        emailEditTxtRegister.setTranslationY(300);
        emailEditTxtRegister.setAlpha(0);
        emailEditTxtRegister.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
        passwordEditTxtRegister.setTranslationY(300);
        passwordEditTxtRegister.setAlpha(0);
        passwordEditTxtRegister.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();
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
                if (!validateEmail(emailEditTxtRegister.getText().toString().trim())) {
                    emailEditTxtRegister.setError("Invalid Email");
                } else if (passwordEditTxtRegister.getText().toString().length() < 8) {
                    passwordEditTxtRegister.setError("Minimum 8 characters");
                } else if (firstNameEditTxt.getText().toString().trim().equals("")) {
                    firstNameEditTxt.setError("Enter a First Name");
                } else if (lastNameEditTxt.getText().toString().trim().equals("")) {
                    lastNameEditTxt.setError("Enter a Last Name");
                } else if (!confirmPasswordEditTxt.getText().toString().equals(passwordEditTxtRegister.getText().toString())) {
                    passwordEditTxtRegister.setError("Passwords don't match");
                    confirmPasswordEditTxt.setError("Passwords don't match");
                    passwordEditTxtRegister.setText("");
                    confirmPasswordEditTxt.setText("");
                } else {

                    Retrofit retrofit = RetrofitBuilder.getRetrofitInstance();
                    RetrofitServices retrofitServices = retrofit.create(RetrofitServices.class);

                    Call<RegisterResponse> call = retrofitServices.registerUser(firstNameEditTxt.getText().toString().trim(), lastNameEditTxt.getText().toString().trim(),
                            emailEditTxtRegister.getText().toString().trim(), passwordEditTxtRegister.getText().toString().trim());

                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            RegisterResponse message = response.body();

                            if (response.body().getMessage().equals("success")) {
                                System.out.println(response.code());
                                Toast.makeText(getActivity(), "Registered Successfully!", Toast.LENGTH_LONG).show();
                                firstNameEditTxt.setText("");
                                lastNameEditTxt.setText("");
                                emailEditTxtRegister.setText("");
                                firstNameEditTxt.setText("");
                                passwordEditTxtRegister.setText("");
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
