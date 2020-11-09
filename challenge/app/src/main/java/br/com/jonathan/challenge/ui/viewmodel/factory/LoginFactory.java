package br.com.jonathan.challenge.ui.viewmodel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.jonathan.challenge.BaseApp;
import br.com.jonathan.challenge.repository.UserRepository;
import br.com.jonathan.challenge.ui.viewmodel.LoginViewModel;

public class LoginFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application mApplication;
    private final UserRepository userRepository;

    public LoginFactory(@NonNull Application application) {
        mApplication = application;
        userRepository = ((BaseApp) application).getUserRepository();
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(userRepository);
    }
}