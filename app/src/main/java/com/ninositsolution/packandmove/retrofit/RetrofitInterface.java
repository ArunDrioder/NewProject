package com.ninositsolution.packandmove.retrofit;
import com.ninositsolution.packandmove.Registration.pojo.RegistrationRequest;
import com.ninositsolution.packandmove.pojo.POJOClass;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RetrofitInterface {
     String BASE_URL = "http://192.168.1.23/packandmove/public/api/";

@POST("user/register")
Call<POJOClass> registerationApi (@Body RegistrationRequest registrationRequest);






}
