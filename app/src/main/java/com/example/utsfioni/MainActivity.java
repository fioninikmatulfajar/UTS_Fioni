package com.example.utsfioni;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.utsfioni.Adapters.ListUsersAdapter;
import com.example.utsfioni.Api.ApiConfig;
import com.example.utsfioni.databinding.ActivityMainBinding;
import com.example.utsfioni.Models.GitHubResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListUsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adapter = new ListUsersAdapter();
        String nama = "Fioni";

        Call<GitHubResponse> api = ApiConfig.service().getUsers(nama);
        api.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(@NonNull Call<GitHubResponse> call, @NonNull Response<GitHubResponse> response) {
                binding.rvList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.rvList.setAdapter(adapter);
                if (response.isSuccessful()){
                    adapter.submitList(response.body().getItems());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GitHubResponse> call, @NonNull Throwable t) {
                Log.e("error", Objects.requireNonNull(t.getMessage()));
            }
        });

    }
}