package com.rckdeveloper.newsappusingmvvmretrofit.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rckdeveloper.newsappusingmvvmretrofit.repository.ArticleRepository;
import com.rckdeveloper.newsappusingmvvmretrofit.response.ArticleResponse;

import org.jetbrains.annotations.NotNull;

public class ArticleViewModel extends AndroidViewModel {

    private ArticleRepository articleRepository;
    private LiveData<ArticleResponse> articleResponseLiveData;






    public ArticleViewModel(@NotNull Application application) {
        super(application);

        articleRepository = new ArticleRepository();
        this.articleResponseLiveData= articleRepository.getDashBoardNews();

    }



    public LiveData<ArticleResponse> getBashboardNewsResponseLiveData(){
        return articleResponseLiveData;
    }


}
