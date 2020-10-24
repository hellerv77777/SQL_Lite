package com.sebastian.sql_lite.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Services {

    @GET
    Call<String> getDataApi(@Url String url);

}
