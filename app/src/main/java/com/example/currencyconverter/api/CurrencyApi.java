package com.example.currencyconverter.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrencyApi {

    @GET("v6/latest/{base}")
    Call<CurrencyResponse> getRates(@Path("base") String base);

}