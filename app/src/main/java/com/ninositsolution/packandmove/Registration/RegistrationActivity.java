package com.ninositsolution.packandmove.Registration;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ninositsolution.packandmove.Login.LoginActivity;
import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.Otp.OTPVerificationActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.Registration.pojo.RegistrationRequest;
import com.ninositsolution.packandmove.pojo.Data;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.utils.Session;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private POJOClass pojoClass;
    private Data data;

    ProgressBar progressBar;

    Button register,loginFromRegister;
    Context context;
    EditText regFirstName,registerLastName,registerMobileNumber,registerEmailAddress,registerPassword,registerConfirmPassword;
    String firstName,lastName,mobileNumber,emailAddress,password,confirmPasword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = (Button)findViewById(R.id.register_button);
        loginFromRegister = (Button)findViewById(R.id.login_btn_from_register);
        progressBar = findViewById(R.id.register_progress);

        regFirstName = findViewById(R.id.regFirstName);
        registerLastName = findViewById(R.id.registerLastName);
        registerMobileNumber = findViewById(R.id.registerMobileNumber);
        registerEmailAddress = findViewById(R.id.registerEmailAddress);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPassword  =findViewById(R.id.registerConfirmPassword);
        context = RegistrationActivity.this;





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();

                firstName = regFirstName.getText().toString();
                lastName = registerLastName.getText().toString();
                emailAddress = registerEmailAddress.getText().toString();
                mobileNumber = registerMobileNumber.getText().toString();
                password = registerPassword.getText().toString();
                confirmPasword = registerConfirmPassword.getText().toString();

                Log.e(TAG,"registration button clicked() ->");

                RegistrationRequest registrationRequest = new RegistrationRequest();
                registrationRequest.setName(firstName);
                registrationRequest.setLastname(lastName);
                registrationRequest.setEmail(emailAddress);
                registrationRequest.setMobile(mobileNumber);
                registrationRequest.setPassword(password);
                registrationRequest.setConfirm_password(confirmPasword);

                Log.e(TAG,"All textfields has been assigned and setted to request class");
                RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
                Log.e(TAG,"crossed API call and entering to call POJO ");
                Call<POJOClass> Register = retrofitInterface.registerationApi(registrationRequest);
                Log.e(TAG,"Going to enter callback ");

                Register.enqueue(new Callback<POJOClass>() {
                    @Override
                    public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                    {
                        hideProgressBar();

                        Log.e(TAG,"Entered  callback ");

                        if (response.code() == 200)
                        {
                            pojoClass = response.body();


                            Log.i(TAG, "code : "+response.code());

                                if (pojoClass.getStatus() != null)
                                {
                                    if (pojoClass.getStatus().equalsIgnoreCase("success"))
                                    {
                                        hideProgressBar();

                                        Log.e(TAG, "Entered on success Status is: ->" + pojoClass.getStatus());
                                        Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();
                                        Session.setToken(pojoClass.getData().getToken(), context);
                                        Session.setUserId(pojoClass.getUser_id(), context);
                                        Session.setOtp(String.valueOf(pojoClass.getOtp()), context);
                                        String receivedOtp = String.valueOf(pojoClass.getOtp());
                                        Toast.makeText(getApplicationContext(),"user id is:->" + pojoClass.getUser_id(),Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegistrationActivity.this, OTPVerificationActivity.class);
                                        intent.putExtra("receivedOTP", receivedOtp);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        hideProgressBar();
                                        Log.e(TAG, "Entered on error Status is: ->" + pojoClass.getStatus());

                                        Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();

                                    }

                                }
                                else
                                {
                                    Toast.makeText(context, "status is null", Toast.LENGTH_SHORT).show();

                                }

                        }
                        else
                            {
                            Toast.makeText(context, "Error code : "+response.code(), Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Error code : "+response.code());
                            }
                    }

                    @Override
                    public void onFailure(Call<POJOClass> call, Throwable t)

                    {
                        hideProgressBar();


                        Log.e(TAG,"Error is:->" + t);

                        Toast.makeText(getApplicationContext(),""+t, Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        loginFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void showProgressBar()
    {
        if (progressBar.getVisibility() == View.GONE)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar()
    { if (progressBar.getVisibility() == View.VISIBLE)
    {
        progressBar.setVisibility(View.GONE);
    }


    }


}
