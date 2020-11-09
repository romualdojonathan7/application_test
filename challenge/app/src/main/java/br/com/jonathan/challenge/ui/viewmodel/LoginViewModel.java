package br.com.jonathan.challenge.ui.viewmodel;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;

import java.util.Objects;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.jonathan.challenge.database.AppExecutor;
import br.com.jonathan.challenge.model.User;
import br.com.jonathan.challenge.repository.UserRepository;
import br.com.jonathan.challenge.validator.EmailValidator;
import br.com.jonathan.challenge.validator.PasswordValidator;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> usernameOrEmail = new MutableLiveData<>("teste@gmail.com");
    public MutableLiveData<String> password = new MutableLiveData<>("passworD@1");
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> loginResponse = new MutableLiveData<>();

    private UserRepository userRepository;

    private MutableLiveData<User> mutableUser;
    private User mUser;

    public MutableLiveData<User> getUser(){
        if(mutableUser == null) {
            mUser = new User();
            mutableUser = new MutableLiveData<>();
        }

        return mutableUser;
    }

    public LoginViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
        isLoading.setValue(false);
    }

    public void authenticate(){

        isLoading.setValue(true);

        getUser();
        mUser.setEmail(Objects.requireNonNull(usernameOrEmail.getValue()));
        mUser.setPassword(Objects.requireNonNull(password.getValue()));

        if((new EmailValidator(usernameOrEmail.getValue())).isValid(usernameOrEmail.getValue(), TextUtils.isEmpty(usernameOrEmail.getValue())) &&
                (new PasswordValidator(password.getValue())).isValid(password.getValue(), TextUtils.isEmpty(password.getValue()))){

            AppExecutor.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                   User user = userRepository.findByEmailAndPassword(mUser.getEmail(), mUser.getPassword());

                    mutableUser.postValue(user);

                    isLoading.postValue(false);
                    loginResponse.postValue(user != null);
                }
            });
        } else {
            isLoading.setValue(false);
            loginResponse.setValue(false);
        }
    }
}
