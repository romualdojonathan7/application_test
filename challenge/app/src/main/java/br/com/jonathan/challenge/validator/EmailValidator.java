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
}