package com.rckdeveloper.newsappusingmvvmretrofit.retrofit;

import com.rckdeveloper.newsappusingmvvmretrofit.constants.AppConstant;
import com.rckdeveloper.newsappusingmvvmretrofit.response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("top-headlines?country=in&category=business&apiKey="+ AppConstant.API_KEY)
    Call<ArticleResponse> getTopHeadlines();
}
