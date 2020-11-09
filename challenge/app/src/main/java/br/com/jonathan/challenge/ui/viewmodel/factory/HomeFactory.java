package br.com.jonathan.challenge.ui.viewmodel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import br.com.jonathan.challenge.BaseApp;
import br.com.jonathan.challenge.repository.ProductRepository;
import br.com.jonathan.challenge.ui.viewmodel.HomeViewModel;

public class HomeFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application mApplication;
    private final ProductRepository productRepository;

    public HomeFactory(@NonNull Application application) {
        mApplication = application;
        productRepository = ((BaseApp) application).getProductRepository();
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(productRepository);
    }
}