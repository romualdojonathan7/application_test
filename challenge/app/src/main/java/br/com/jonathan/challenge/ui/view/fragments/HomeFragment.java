package br.com.jonathan.challenge.ui.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import br.com.jonathan.challenge.R;
import br.com.jonathan.challenge.model.Product;
import br.com.jonathan.challenge.ui.adapter.ProductAdapter;
import br.com.jonathan.challenge.ui.viewmodel.HomeViewModel;
import br.com.jonathan.challenge.ui.viewmodel.factory.HomeFactory;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.product_list, container, false);
        recyclerView = root.findViewById(R.id.recycler_view_product_list);

        initRecyclerView();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        HomeFactory factory = new HomeFactory(requireActivity().getApplication());

        final HomeViewModel model = new ViewModelProvider(this, factory).get(HomeViewModel.class);

        subscribeToModel(model);
    }

    private void initRecyclerView(){
        productAdapter = new ProductAdapter(getActivity().getApplicationContext());

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productAdapter);
    }


    private void subscribeToModel(HomeViewModel homeViewModel){
        homeViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> jobs) {
                productAdapter.setProducts(jobs);
            }
        });
    }
}
