package com.ninositsolution.packandmove.retrofit;
import com.ninositsolution.packandmove.Login.pojo.LoginRequest;
import com.ninositsolution.packandmove.Otp.pojo.OTPRequest;
import com.ninositsolution.packandmove.Registration.pojo.RegistrationRequest;
import com.ninositsolution.packandmove.doortodoorservices.pojo.DoorServiceRequest;
import com.ninositsolution.packandmove.laboursupply.pojo.LabourSupplyRequest;
import com.ninositsolution.packandmove.packaging.pojo.PackageRequest;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.temporarystorage.pojo.StorageRequest;
import com.ninositsolution.packandmove.truckrental.pojo.TruckRentalRequest;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface RetrofitInterface {


@POST("user/register")
Call<POJOClass> registerationApi (@Body RegistrationRequest registrationRequest);

@POST("user/otp_verification")
Call<POJOClass> otpVerifyApi (@Body OTPRequest otpRequest);

@POST("user/login")
Call<POJOClass> loginApi (@Body LoginRequest loginRequest);

@POST("insert/package")
Call<POJOClass> packageApi (@Header("Authorization") String token, @Body PackageRequest packageRequest);

@POST("insert/storage")
Call<POJOClass> storageApi  (@Header("Authorization") String token, @Body StorageRequest storageRequest);

@POST("insert/door_delivery_service")
Call<POJOClass> doorServiceApi (@Header("Authorization") String token, @Body DoorServiceRequest doorServiceRequest);

@POST("insert/men_power_supply")
Call<POJOClass> labourSupplyApi(@Header("Authorization") String token, @Body LabourSupplyRequest labourSupplyRequest);

@POST("insert/truck_rental")
Call<POJOClass> truckRentalApi(@Header("Authorization") String token, @Body TruckRentalRequest truckRentalRequest);







}
