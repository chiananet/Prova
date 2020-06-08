package com.digital.prova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.digital.prova.R;
import com.digital.prova.adapters.CustomAdapter;
import com.digital.prova.interfaces.GetDataService;
import com.digital.prova.jsndata.JsnData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
        private static final String TAG = "MainActivity";
        ProgressDialog progressDoalog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            progressDoalog = new ProgressDialog(MainActivity.this);
            progressDoalog.setMessage("Loading....");
            progressDoalog.show();

            /*Create handle for the RetrofitInstance interface*/
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

            Call<List<JsnData>> call = service.getPosts();
            call.enqueue(new Callback<List<JsnData>>() {

                @Override
                public void onResponse(@NonNull Call<List<JsnData>> call, @NonNull Response<List<JsnData>> response) {
                    progressDoalog.dismiss();
                    generateDataList(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<List<JsnData>> call, @NonNull Throwable t) {
                    progressDoalog.dismiss();
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }

            });
        }

        /*Method to generate List of data using RecyclerView with custom adapter*/
        private void generateDataList(List<JsnData> dataList) {
            RecyclerView recyclerView = findViewById(R.id.customRecyclerView);
            CustomAdapter adapter = new CustomAdapter(this, dataList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

}