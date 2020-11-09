package br.com.jonathan.challenge.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import br.com.jonathan.challenge.R;
import br.com.jonathan.challenge.databinding.ActivitySignupBinding;
import br.com.jonathan.challenge.ui.viewmodel.SignupViewModel;
import br.com.jonathan.challenge.ui.viewmodel.factory.SignupFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class SignupActivity extends AppCompatActivity implements SignupViewModel.ViewListener {

    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SignupFactory factory = new SignupFactory(this.getApplication());
        final SignupViewModel signupViewModel = new ViewModelProvider(this, factory).get(SignupViewModel.class);

        signupViewModel.setErrorListener(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.setLifecycleOwner(this);
        binding.setSignupViewModel(signupViewModel);

        TextView txteView = findViewById(R.id.text_login_already_member);
        txteView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });

        setAutoValidate(signupViewModel);
    }

    private void setAutoValidate(SignupViewModel signupViewModel) {
        MaterialEditText emailEditText = binding.editTextSignUpEmail;
        MaterialEditText nameEditText = binding.editTextSignUpName;
        MaterialEditText passwordEditText = binding.editTextSignUpPassword;

        emailEditText.setAutoValidate(true);
        nameEditText.setAutoValidate(true);
        passwordEditText.setAutoValidate(true);

        emailEditText.addValidator(signupViewModel.getEmailValidator());
        nameEditText.addValidator(signupViewModel.getNameValidator());
        passwordEditText.addValidator(signupViewModel.getPasswordValidator());
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String header, String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
