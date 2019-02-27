package com.ninositsolution.packandmove.Registration;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ninositsolution.packandmove.Login.LoginActivity;
import com.ninositsolution.packandmove.Otp.OTPVerificationActivity;
import com.ninositsolution.packandmove.R;

public class RegistrationActivity extends AppCompatActivity {
    Button register,loginFromRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = (Button)findViewById(R.id.register_button);
        loginFromRegister = (Button)findViewById(R.id.login_btn_from_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, OTPVerificationActivity.class);
                startActivity(intent);
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

}}
