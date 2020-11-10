package br.com.jonathan.challenge.validator;


import android.util.Patterns;

import androidx.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

public class EmailValidator extends METValidator {

    public EmailValidator(String email) {
        super(email);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return !isEmpty && Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches();
    }

    public static String isValid(@NonNull String email) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Provided e-mail is not a valid e-mail. Please input a valid e-mail.";
        }

        return null;
    }
}