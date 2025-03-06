
package com.softek.codepro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsFragment extends Fragment {

    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    public NewsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        newsRV = view.findViewById(R.id.idRVNews);
        categoryRV = view.findViewById(R.id.idRVCategories);
        loadingPB = view.findViewById(R.id.idPBLoading);

        articlesArrayList = new ArrayList<>();
        categoryRVModalArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList, getContext());
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList, getContext(), this::onCategoryClick);

        newsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);

        getCategories();
        getNews("All");

        return view;
    }

    private void getCategories() {
        categoryRVModalArrayList.add(new CategoryRVModal("All","https://imgs.search.brave.com/SSnYHKRxOtslWWjPyDIzBqU4lxBLGYpETLfvVuHm_9Y/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/cHJlbWl1bS1waG90/by9uZXdzcGFwZXIt/cGFwZXItZ3J1bmdl/LXZpbnRhZ2Utb2xk/LWFnZWQtdGV4dHVy/ZS1iYWNrZ3JvdW5k/XzEyNzcwMzgtMjM2/LmpwZz9zZW10PWFp/c19oeWJyaWQ"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://img.freepik.com/free-vector/circuit-board-tree-symbol_98292-3922.jpg?t=st=1739361341~exp=1739364941~hmac=07dcd7a201ffd917a1790a94808afd993e62ad222ff48b5a53f50518e43dc58e&w=740"));
        categoryRVModalArrayList.add(new CategoryRVModal("Science","https://imgs.search.brave.com/8xAty20CRadCF17f998ooJmpqtEtVvfHUI7xIRWUclw/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9idXJz/dC5zaG9waWZ5Y2Ru/LmNvbS9waG90b3Mv/YmVha2Vycy1mb3It/c2NpZW5jZS13aXRo/LXdhdGVyLmpwZz93/aWR0aD0xMDAwJmZv/cm1hdD1wanBnJmV4/aWY9MCZpcHRjPTA"));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://imgs.search.brave.com/Vlqd7zG8ZwkydqHH1loj5NMoMpuHnAedBCiev5ETdNQ/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly90My5m/dGNkbi5uZXQvanBn/LzAyLzc4LzQyLzc2/LzM2MF9GXzI3ODQy/NzY4M196ZVM5aWhQ/QU82MVFoSHFkVTFm/T2FQazJVQ2xmZ1Bj/Vy5qcGc"));
        categoryRVModalArrayList.add(new CategoryRVModal("General","https://imgs.search.brave.com/07ik2Q79siS1aqAh1qVjTAfkoqh8nvuwyDg9L1gAuWE/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/ZnJlZS1waG90by9v/bGQtbmV3c3BhcGVy/LWJhY2tncm91bmQt/dG9wLXZpZXdfMjMt/MjE0OTMxODg4Ny5q/cGc_c2VtdD1haXNf/aHlicmlk"));
        categoryRVModalArrayList.add(new CategoryRVModal("Business","https://images.unsplash.com/photo-1591696205602-2f950c417cb9?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://imgs.search.brave.com/8dkhb5RwObMYLwwY1POxOgZmztdKHbeSk6fOrBXHWX0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMTMw/NjIxODYzMS9waG90/by9kaWdpdGFsLWNp/bmVtYS1jYW1lcmEt/b24tc2V0LmpwZz9z/PTYxMng2MTImdz0w/Jms9MjAmYz1iT0Ni/V0h2d2g1c3I5R3dL/T3puNV9Zaks4Snhs/MU1vT0NHYktwWU9v/ZEZVPQ"));
        categoryRVModalArrayList.add(new CategoryRVModal("Health","https://imgs.search.brave.com/YIbZOz6EaV7tTgk2Zd9r0PXlYQOMXiBodfGyYepTX0M/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly90NC5m/dGNkbi5uZXQvanBn/LzAxLzgxLzkyLzY5/LzM2MF9GXzE4MTky/Njk5Nl9iRnlLUURD/dHpha09MRGR1aWRK/RDllZFBaUlZvb0Nt/Ry5qcGc"));
        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String category) {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();

        String API_KEY = "38520f0542ef9893582212e9bb07a7ee"; // GNews API key

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gnews.io/api/v4/")  // GNews API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFitAPI retroFitAPI = retrofit.create(RetroFitAPI.class);
        Call<NewsModal> call;

        // Adjust the API call based on the selected category
        if (category.equals("All")) {
            call = retroFitAPI.getTopHeadlines("in", "all", API_KEY); // Fetch all news for India
        } else {
            call = retroFitAPI.getTopHeadlines("in", category.toLowerCase(), API_KEY); // Fetch news based on the category
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                loadingPB.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                NewsModal newsResponse = response.body();
                if (newsResponse != null) {
                    articlesArrayList.addAll(newsResponse.getArticles());
                    newsRVAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Failed to get news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCategoryClick(int position) {
        getNews(categoryRVModalArrayList.get(position).getCategory());
    }
}
