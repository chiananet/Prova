package com.digital.prova.interfaces;

import com.digital.prova.jsndata.JsnDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetDetailService {
    @GET
    Call<List<JsnDetail>> getDetail(@Url String url);
}
