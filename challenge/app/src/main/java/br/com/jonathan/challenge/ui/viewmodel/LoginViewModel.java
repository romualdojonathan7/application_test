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
import br.com.jonathan.challenge.validator.NameValidator;
import br.com.jonathan.challenge.validator.PasswordValidator;

public class LoginViewModel extends ViewModel {

    private EmailValidator emailValidator;
    private PasswordValidator passwordValidator;

    public MutableLiveData<String> usernameOrEmail = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> loginResponse = new MutableLiveData<>();

    private ViewListener errorViewListener;

    private UserRepository userRepository;

    private MutableLiveData<User> mutableUser;

    public MutableLiveData<User> getUser(){
        if(mutableUser == null) {
            mutableUser = new MutableLiveData<>();
        }

        return mutableUser;
    }

    public LoginViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
        isLoading.setValue(false);
        loginResponse.setValue(true);

        emailValidator = new EmailValidator(usernameOrEmail.getValue());
        passwordValidator = new PasswordValidator(password.getValue());
    }

    public EmailValidator getEmailValidator() {
        return emailValidator;
    }

    public PasswordValidator getPasswordValidator() {
        return passwordValidator;
    }

    public void setErrorListener(ViewListener errorViewListener) {
        this.errorViewListener = errorViewListener;
    }

    public void authenticate(){

        isLoading.setValue(true);

        getUser();

        String resultEmailValidation = EmailValidator.isValid(usernameOrEmail.getValue());
        String resultPasswordValidation = PasswordValidator.isValid(password.getValue(), null);

        if(resultEmailValidation != null) {
            errorViewListener.onError("Email Error", resultEmailValidation);
            isLoading.setValue(false);
        }

        if(resultPasswordValidation != null) {
            errorViewListener.onError("Password Error", resultPasswordValidation);
            isLoading.setValue(false);
        }

        if(resultEmailValidation == null && resultPasswordValidation == null){

            AppExecutor.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    User user = userRepository.findByEmailAndPassword(usernameOrEmail.getValue(), password.getValue());

                    if(user != null){
                        errorViewListener.onLoginSuccess();
                        mutableUser.postValue(user);
                    } else {
                        loginResponse.postValue(false);
                    }

                    isLoading.postValue(false);
                }
            });
        }
    }

    public interface ViewListener {
        void onLoginSuccess();
        void onError(String header, String message);
    }
}
