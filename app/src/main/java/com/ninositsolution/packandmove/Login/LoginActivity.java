package com.ninositsolution.packandmove.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ninositsolution.packandmove.Login.pojo.LoginRequest;
import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.Registration.RegistrationActivity;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.utils.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.System.exit;
import static java.lang.System.in;

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";


    Button login;
    Button linkToRegister;
    Context context;
    EditText loginMobile,loginPassowrd;
    ProgressBar progressBar;
    String mobileNumber, password;

    private POJOClass pojoClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login_btn);
        linkToRegister = (Button) findViewById(R.id.register_btn_from_login);
        loginMobile = findViewById(R.id.loginMobile);
        loginPassowrd = findViewById(R.id.loginPassword);
        progressBar = findViewById(R.id.login_progress);
        context = LoginActivity.this;


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

                showProgressBar();


                mobileNumber = loginMobile.getText().toString();
                password = loginPassowrd.getText().toString();

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setMobile(mobileNumber);
                loginRequest.setPassword(password);

                RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
                Call<POJOClass> Login = retrofitInterface.loginApi(loginRequest);
                Login.enqueue(new Callback<POJOClass>() {
                    @Override
                    public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                    {


                        if (response.code() == 200)
                        {
                            pojoClass = response.body();

                            if (pojoClass.getStatus()!=null)
                            {
                                if (pojoClass.getStatus().equalsIgnoreCase("success"))
                                {
                                    hideProgressBar();

                                    Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();
                                    Session.setToken(pojoClass.getData().getToken(), context);
                                    Session.setUserId(pojoClass.getUser_id(),context);
                                    Toast.makeText(getApplicationContext(), "Token is:->" + pojoClass.getData().getToken(),Toast.LENGTH_LONG).show();
                                    Log.e(TAG,"The token is:->"+ Session.getToken(context));

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);

                                }
                                else
                                {
                                    hideProgressBar();
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
    }


    @Override

    public void onBackPressed() {
     final AlertDialog .Builder builder = new AlertDialog.Builder(LoginActivity.this);
     builder.setTitle("Exit App");
     builder.setMessage("Are you sure you want to Exit ?");
     builder.setCancelable(true);
     builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
         }
     });
     builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             finish();
         }
     });
     AlertDialog dialog = builder.create();
     dialog.show();


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
