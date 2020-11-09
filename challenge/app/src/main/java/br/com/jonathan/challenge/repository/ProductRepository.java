package br.com.jonathan.challenge.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import br.com.jonathan.challenge.database.AppDatabase;
import br.com.jonathan.challenge.database.AppExecutor;
import br.com.jonathan.challenge.database.dao.ProductDao;
import br.com.jonathan.challenge.model.Product;
import br.com.jonathan.challenge.retrofit.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private final AppDatabase appDatabase;
    private static ProductRepository instance;

    ProductRepository(final AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        loadJobs();
    }

    public static ProductRepository getInstance(final AppDatabase appDatabase){
        if(instance == null) {
            instance = new ProductRepository(appDatabase);
        }

        return instance;
    }

    public void loadJobs(){
        Call<ResponseBody> callLoadProducts = new RetrofitInstance().getProductService().getProducts();

        callLoadProducts.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {

                try {

                    if(response.isSuccessful()){
                        Gson gson = new Gson();

                        if(response.body() != null){
                            List<Product> products = gson.fromJson(response.body().string(), new TypeToken<List<Product>>(){}.getType());

                            insert(products);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {

            }
        });
    }

    public LiveData<List<Product>> getProducts() {
        return appDatabase.productDao().getAllJobs();
    }

    public void insert(final List<Product> products){
        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.productDao().insertAll(products);
            }
        });
    }
}
