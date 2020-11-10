package br.com.jonathan.challenge.validator;

import androidx.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

public class PasswordValidator extends METValidator {

    private String password;
    private String confirmationPassword;

    public PasswordValidator(String password) {
        super(password);
        this.password = password;
    }

    public PasswordValidator(String password, String confirmationPassword) {
        super(password);
        this.password = password;
        this.confirmationPassword = confirmationPassword;
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {

        if(password == null){
            return false;
        }

        if(!(text.length() >= 8 && text.length() <= 16)) {
            return false;
        }

        if(confirmationPassword != null){
            if(!password.equals(confirmationPassword)){
                return false;
            }
        }

        // At least one lower case character, one upper case character, one digit, special characters and 8 to 16
        return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$");
    }

    public static String isValid(@NonNull String password, String confirmationPassword) {

        if(!(password.length() >= 8 && password.length() <= 16)) {
            return "Password must be between 8 and 16 characters.";
        }

        if(confirmationPassword != null){
            if(!password.equals(confirmationPassword)){
                return "Passwords aren't equal";
            }
        }

        // At least one lower case character, one upper case character, one digit, special characters and 8 to 16
        if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$")) {
            return "Password must contain at least one lower character, one upper character, one digit and one special character.";
        }

        return null;
    }
}