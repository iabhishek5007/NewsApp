package com.rckdeveloper.newsappusingmvvmretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.rckdeveloper.newsappusingmvvmretrofit.R;
import com.rckdeveloper.newsappusingmvvmretrofit.adapter.ArticleAdapter;
import com.rckdeveloper.newsappusingmvvmretrofit.databinding.ActivityMainBinding;
import com.rckdeveloper.newsappusingmvvmretrofit.model.Article;
import com.rckdeveloper.newsappusingmvvmretrofit.view_model.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final String TAG=MainActivity.class.getSimpleName();

    private ArrayList<Article> articleArrayList= new ArrayList<>();
    ArticleViewModel articleViewModel;
    private ArticleAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();

        getArticles();
    }

    private void getArticles() {

        articleViewModel.getBashboardNewsResponseLiveData().observe(this, articleResponse ->{
            if(articleResponse!=null && articleResponse.getArticles()!=null
            && !articleResponse.getArticles().isEmpty()){
                binding.progressBar.setVisibility(View.GONE);
                List<Article> articleList= articleResponse.getArticles();
                articleArrayList.addAll(articleList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {

        layoutManager= new LinearLayoutManager(MainActivity.this);
        binding.recyclerView.setLayoutManager(layoutManager);

        binding.recyclerView.setHasFixedSize(true);
        adapter= new ArticleAdapter(MainActivity.this,articleArrayList);
        binding.recyclerView.setAdapter(adapter);
    //    articleViewModel= new ViewModelProvider(MainActivity.this).get(ArticleViewModel.class);
        articleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ArticleViewModel.class);
//        articleViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel.class);
    }
}