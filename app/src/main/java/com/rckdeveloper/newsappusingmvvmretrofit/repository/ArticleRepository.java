package com.rckdeveloper.newsappusingmvvmretrofit.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rckdeveloper.newsappusingmvvmretrofit.response.ArticleResponse;
import com.rckdeveloper.newsappusingmvvmretrofit.retrofit.ApiRequest;
import com.rckdeveloper.newsappusingmvvmretrofit.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ArticleRepository {

    private static final String TAG= ArticleRepository.class.getSimpleName();
    private final ApiRequest apiRequest;


    public ArticleRepository(){
        apiRequest= RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ArticleResponse> getDashBoardNews(){
        final MutableLiveData<ArticleResponse> data= new MutableLiveData<>();
        apiRequest.getTopHeadlines()
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        if (response.body()!=null){
                            data.setValue(response.body());
                            Log.d(TAG,response.body().toString());
                        }


                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        data.setValue(null);
                        Log.d(TAG,String.valueOf(t.getMessage()));
                    }
                });
return data;
    }


}
