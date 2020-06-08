package com.digital.prova.interfaces;

import com.digital.prova.jsndata.JsnData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("categories.getList?fw=kidzinmind&vh=it.kidzinmind.com&lang=it&white_label=it_kidzinmind&real_customer_id=it_kim&tld=it&foremats=vido")
    Call<List<JsnData>> getPosts();
}
