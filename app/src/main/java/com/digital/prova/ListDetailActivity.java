package com.digital.prova;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digital.prova.adapters.DetailAdapter;
import com.digital.prova.interfaces.GetDetailService;
import com.digital.prova.R;
import com.digital.prova.jsndata.JsnDetail;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDetailActivity extends AppCompatActivity {
    private static final String TAG = "ListDetail";
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        progressDoalog = new ProgressDialog(ListDetailActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDetailService service = RetrofitClientInstance.getRetrofitInstance().create(GetDetailService.class);
        String getQuery = "contents.getListAdv?fw=kidzinmind&vh=it.kidzinmind.com&lang=it&white_label=it_kidzinmind&real_customer_id=it_kim&tld=it&formats=video&category=";
        String queryId = Objects.requireNonNull(getIntent().getExtras()).getString("getIdCategory");
        String url = getQuery + queryId;

        Call<List<JsnDetail>> call = service.getDetail(url);
        call.enqueue(new Callback<List<JsnDetail>>() {
            @Override
            public void onResponse(@NonNull Call<List<JsnDetail>> call, @NonNull Response<List<JsnDetail>> response) {
                progressDoalog.dismiss();
                assert response.body() != null;
                generateDataList(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<JsnDetail>> call, @NonNull Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(ListDetailActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<JsnDetail> detailList) {
        if (detailList.isEmpty()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListDetailActivity.this);
            alertDialogBuilder.setTitle("Error")
                    .setMessage("The selected item is empty!")
                    .setCancelable(false)
                    .setPositiveButton("OK!", (dialog, which) -> {
                        dialog.cancel();
                        ListDetailActivity.this.finish();
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            @SuppressLint("WrongViewCast") RecyclerView recyclerView = findViewById(R.id.detail_row);
            DetailAdapter adapter = new DetailAdapter(this, detailList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDetailActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

}
