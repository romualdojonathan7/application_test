package br.com.jonathan.challenge.retrofit;

import java.util.concurrent.TimeUnit;

import br.com.jonathan.challenge.retrofit.service.ProductService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "https://makeup-api.herokuapp.com";

    private Retrofit retrofit;

    public RetrofitInstance(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public ProductService getProductService(){
        return this.retrofit.create(ProductService.class);
    }
}
