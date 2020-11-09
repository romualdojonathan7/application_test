package br.com.jonathan.challenge.validator;

import androidx.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

public class NameValidator extends METValidator {

    public NameValidator(String name) {
        super(name);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return !isEmpty;
    }
}
