package com.ninositsolution.packandmove.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.Registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button linkToRegister;
    LinearLayout otpLayout;
    View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button)findViewById(R.id.login_btn);
        linkToRegister = (Button)findViewById(R.id.register_btn_from_login);
        otpLayout = (LinearLayout)findViewById(R.id.otpLayout);
        view1 = (View)findViewById(R.id.view1);

        linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setVisibility(View.VISIBLE);
                otpLayout.setVisibility(View.VISIBLE);
                login.setText(R.string.login_header);


            }
        });
    }
}
