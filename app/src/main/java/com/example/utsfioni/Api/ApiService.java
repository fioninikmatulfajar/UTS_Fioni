package com.example.utsfioni.Api;

import com.example.utsfioni.Models.GitHubResponse;
import com.example.utsfioni.Models.ItemUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    @Headers("Authorization: Bearer " + TOKEN)
    Call<GitHubResponse> getUsers(@Query("q") String username);

    @GET("users/{username}")
    Call<ItemUser> getDetailUser(@Path("username") String username);

    String TOKEN = "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";
}
