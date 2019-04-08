package com.ninositsolution.packandmove.retrofit;
import com.ninositsolution.packandmove.Otp.pojo.OTPRequest;
import com.ninositsolution.packandmove.Registration.pojo.RegistrationRequest;
import com.ninositsolution.packandmove.pojo.POJOClass;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface RetrofitInterface {


@POST("user/register")
Call<POJOClass> registerationApi (@Body RegistrationRequest registrationRequest);

@POST("user/otp_verification")
Call<POJOClass> otpVerifyApi (@Body OTPRequest otpRequest);





}
