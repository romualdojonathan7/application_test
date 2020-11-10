package br.com.jonathan.challenge.ui.viewmodel;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.jonathan.challenge.model.User;
import br.com.jonathan.challenge.repository.UserRepository;
import br.com.jonathan.challenge.validator.EmailValidator;
import br.com.jonathan.challenge.validator.NameValidator;
import br.com.jonathan.challenge.validator.PasswordValidator;

public class SignupViewModel extends ViewModel {

    private EmailValidator emailValidator;
    private NameValidator nameValidator;
    private PasswordValidator passwordValidator;

    private View.OnFocusChangeListener onFocusEmail;
    private View.OnFocusChangeListener onFocusName;
    private View.OnFocusChangeListener onFocusPassword;

    private ViewListener errorViewListener;

    public String name, email, password, confirmationPassword;

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private UserRepository userRepository;

    public SignupViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
        this.name = "";
        this.email = "";
        this.password = "";
        this.confirmationPassword = "";
        isLoading.setValue(false);

        emailValidator = new EmailValidator(email);
        nameValidator = new NameValidator(name);
        passwordValidator = new PasswordValidator(password);
    }

    public EmailValidator getEmailValidator() {
        return emailValidator;
    }

    public NameValidator getNameValidator() {
        return nameValidator;
    }

    public PasswordValidator getPasswordValidator() {
        return passwordValidator;
    }

    public Boolean checkInputs(){

        if(TextUtils.isEmpty(this.email) && TextUtils.isEmpty(this.name) && TextUtils.isEmpty(this.password) && TextUtils.isEmpty(this.confirmationPassword)){
            errorViewListener.onError("Blank fields", "Please complete all fields.");
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            errorViewListener.onError("Email Error", "Provided e-mail is not a valid e-mail. Please input a valid e-mail.");
            return false;
        }

        if(name.length() < 3){
            errorViewListener.onError("Name Error", "Provided name is too short.");
            return false;
        }

        if(!(password.length() >= 8 && password.length() <= 16)) {
            errorViewListener.onError("Password Error", "Password must be between 8 and 16 characters.");
            return false;
        }

        if(!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$")) {
            errorViewListener.onError("Password Error", "Password must contain at least one lower character, one upper character, one digit and one special character.");
            return false;
        }

        return true;
    }

    public void setErrorListener(ViewListener errorViewListener) {
        this.errorViewListener = errorViewListener;
    }

    public void register() {
        isLoading.setValue(true);

        if (checkInputs()) {
            // Save the user in DB
            try {
                userRepository.insert(new User(name, email, password));
                errorViewListener.onLoginSuccess();
            } catch (Exception ex) {
                errorViewListener.onError("User Already Exists", "User with given mEmail already exists.");
            } finally {
                isLoading.setValue(false);
            }
        } else {
            isLoading.setValue(false);
            errorViewListener.onError("Credenciais invalidas", "Verifique os dados inseridos");
        }
    }

    public interface ViewListener {
        void onLoginSuccess();
        void onError(String header, String message);
    }
}
