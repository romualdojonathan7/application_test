package br.com.jonathan.challenge.ui.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.jonathan.challenge.R;
import br.com.jonathan.challenge.model.Product;
import br.com.jonathan.challenge.ui.adapter.ProductAdapter;
import br.com.jonathan.challenge.ui.viewmodel.HomeViewModel;
import br.com.jonathan.challenge.ui.viewmodel.factory.HomeFactory;
import br.com.jonathan.challenge.databinding.ProductListBinding;

public class HomeFragment extends Fragment {

    private ProductAdapter productAdapter;
    private ProductListBinding productListBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        productListBinding = DataBindingUtil.inflate(inflater, R.layout.product_list, container, false);

        productAdapter = new ProductAdapter();
        productListBinding.recyclerViewProductList.setAdapter(productAdapter);
        return productListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        HomeFactory factory = new HomeFactory(requireActivity().getApplication());
        final HomeViewModel model = new ViewModelProvider(this, factory).get(HomeViewModel.class);

        productListBinding.setLifecycleOwner(getViewLifecycleOwner());

        subscribeToModel(model);
    }

    private void subscribeToModel(HomeViewModel homeViewModel){
        homeViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> jobs) {
                if (jobs != null){
                    productListBinding.setIsLoading(false);
                    productAdapter.setProducts(jobs);
                } else {
                    productListBinding.setIsLoading(true);
                }

                productListBinding.executePendingBindings();
            }
        });
    }

    @Override
    public void onDestroyView() {
        productListBinding = null;
        productAdapter = null;
        super.onDestroyView();
    }
}
