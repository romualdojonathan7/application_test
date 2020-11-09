package br.com.jonathan.challenge;

import android.app.Application;

import br.com.jonathan.challenge.database.AppDatabase;
import br.com.jonathan.challenge.database.AppExecutor;
import br.com.jonathan.challenge.repository.ProductRepository;
import br.com.jonathan.challenge.repository.UserRepository;

public class BaseApp extends Application {

    private AppExecutor mAppExecutor;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutor = AppExecutor.getInstance();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public ProductRepository getProductRepository() {
        return ProductRepository.getInstance(getDatabase());
    }

    public UserRepository getUserRepository(){
        return UserRepository.getInstance(getDatabase());
    }
}
