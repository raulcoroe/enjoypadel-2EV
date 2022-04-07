package com.svalero.enjoypadel.api;

import static com.svalero.enjoypadel.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnjoyPadelApi {

    public static EnjoyPadelApiInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EnjoyPadelApiInterface.class);
    }
}
