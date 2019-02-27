package com.ninositsolution.packandmove.Otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;

public class OTPVerificationActivity extends AppCompatActivity {
    Button verifyOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        verifyOtp = (Button)findViewById(R.id.verifyOtpBtn);
        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTPVerificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
