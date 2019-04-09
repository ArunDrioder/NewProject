package com.ninositsolution.packandmove.Otp;

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
import com.ninositsolution.packandmove.Otp.pojo.OTPRequest;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.utils.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerificationActivity extends AppCompatActivity {
    private static final String TAG = "OTPActivity";

    Button verifyOtp;
    EditText otpEditText;
    String user_id,otp;
    Context context;
    ProgressBar otpProgress;
    private POJOClass pojoClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        verifyOtp = (Button)findViewById(R.id.verifyOtpBtn);
        otpEditText = findViewById(R.id.otpEditText);
        otpProgress = findViewById(R.id.otp_progress);

        context = OTPVerificationActivity.this;


        Intent i = getIntent();
        String setOTP = i.getStringExtra("receivedOTP");
        otpEditText.setText(setOTP);


        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgressBar();

                otp = otpEditText.getText().toString();
                user_id = Session.getUserId(context);
                Log.e(TAG,"otp button clicked() ->");

                OTPRequest otpRequest = new OTPRequest();
                otpRequest.setOtp(otp);
                otpRequest.setUser_id(user_id);
                RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
                Call<POJOClass> OTPVerify = retrofitInterface.otpVerifyApi(otpRequest);
                OTPVerify.enqueue(new Callback<POJOClass>() {
                    @Override
                    public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                    {
                        hideProgressBar();

                        if (response.code() == 200)
                        {
                            pojoClass = response.body();

                            if (pojoClass.getStatus() != null)
                            {
                                if (pojoClass.getStatus().equalsIgnoreCase("success"))
                                {
                                    hideProgressBar();
                                    Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(OTPVerificationActivity.this, LoginActivity.class);
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
    }

    private void showProgressBar()
    {
        if (otpProgress.getVisibility() == View.GONE)
        {
            otpProgress.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar()
    { if (otpProgress.getVisibility() == View.VISIBLE)
    {
        otpProgress.setVisibility(View.GONE);
    }


    }

}
