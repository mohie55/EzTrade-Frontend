package ma4174h.gre.ac.uk.eztrade.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.validator.routines.EmailValidator;

import ma4174h.gre.ac.uk.eztrade.R;
import ma4174h.gre.ac.uk.eztrade.Responses.LoginResponse;
import ma4174h.gre.ac.uk.eztrade.RetrofitBuilder;
import ma4174h.gre.ac.uk.eztrade.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
/
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    private Button loginBtn;
    private EditText emailEditTxtLogin;
    private EditText passwordEditTxtLogin;
    private TextView forgotPass;

//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment1.
     */
    // TODO: Rename and change types and number of parameters
//    public static LoginFragment newInstance() {
//        LoginFragment fragment = new LoginFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

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
//                            LoginResponse message = response.body();

                            if (response.body().getMessage().equalsIgnoreCase("success")) {
                                Toast.makeText(getActivity(), "Logged in Successfully", Toast.LENGTH_SHORT).show();

                                //move to home page

                            } else if (response.body().getMessage().equalsIgnoreCase("failed")) {
                                Toast.makeText(getActivity(), "Email and Password don't match", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(getActivity(), "Error couldn't connect to server, Please Retry lol.", Toast.LENGTH_LONG).show();

                        }
                    });
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