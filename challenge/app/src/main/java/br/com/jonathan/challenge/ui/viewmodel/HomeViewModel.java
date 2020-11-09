package br.com.jonathan.challenge.ui.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import br.com.jonathan.challenge.model.Product;
import br.com.jonathan.challenge.repository.ProductRepository;

public class HomeViewModel extends ViewModel {

    private LiveData<List<Product>> products;

    public HomeViewModel(ProductRepository repository) {
        products = repository.getProducts();
    }

    public LiveData<List<Product>> getProducts() { return products; }

}