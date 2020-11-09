package br.com.jonathan.challenge.retrofit.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("api/v1/products.json")
    Call<ResponseBody> getProducts();
}
