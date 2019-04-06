package com.ninositsolution.packandmove.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://192.168.1.23/packandmove/public/api/";

    private static Retrofit getRetrofit()
    {
      return new Retrofit.Builder().baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .addConverterFactory(ScalarsConverterFactory.create()).build();
    }

public static RetrofitInterface getRetrofitInterface()
{
    return getRetrofit().create(RetrofitInterface.class);
}

}
