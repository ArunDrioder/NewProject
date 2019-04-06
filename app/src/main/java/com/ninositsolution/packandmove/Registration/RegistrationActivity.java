package com.ninositsolution.packandmove.Registration;
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
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    private static final String TAG = "RegisterActivity";

    private POJOClass pojoClass;

    ProgressBar progressBar;

    Button register,loginFromRegister;
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




        firstName = regFirstName.getText().toString();
        lastName = registerLastName.getText().toString();
        emailAddress = registerEmailAddress.getText().toString();
        mobileNumber = registerMobileNumber.getText().toString();
        password = registerPassword.getText().toString();
        confirmPasword = registerConfirmPassword.getText().toString();





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgressBar();

                RegistrationRequest registrationRequest = new RegistrationRequest();
                registrationRequest.setName(firstName);
                registrationRequest.setLastname(lastName);
                registrationRequest.setEmail(emailAddress);
                registrationRequest.setMobile(mobileNumber);
                registrationRequest.setPassword(password);
                registrationRequest.setConfirm_password(confirmPasword);

                Call<POJOClass> Register = retrofitInterface.registerationApi(registrationRequest);
                Register.enqueue(new Callback<POJOClass>() {
                    @Override
                    public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                    {
                        if (!pojoClass.getStatus().isEmpty())
                        {
                            if (pojoClass.getStatus().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();
                                Log.e(TAG, "Entered on success Status is: ->" + pojoClass.getStatus());


                                Toast.makeText(getApplicationContext(),pojoClass.getMessage(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegistrationActivity.this, OTPVerificationActivity.class);
                                startActivity(intent);


                            }
                            else
                            {
                                Log.e(TAG, "Entered on error Status is: ->" + pojoClass.getStatus());
                                hideProgressBar();
                                Toast.makeText(getApplicationContext(),pojoClass.getMessage(),Toast.LENGTH_LONG).show();

                            }
                        }



                    }

                    @Override
                    public void onFailure(Call<POJOClass> call, Throwable t)
                    {
                        Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_LONG).show();

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
