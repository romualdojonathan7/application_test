package br.com.jonathan.challenge.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import br.com.jonathan.challenge.R;
import br.com.jonathan.challenge.databinding.ActivityLoginBinding;
import br.com.jonathan.challenge.model.User;
import br.com.jonathan.challenge.ui.viewmodel.LoginViewModel;
import br.com.jonathan.challenge.ui.viewmodel.SignupViewModel;
import br.com.jonathan.challenge.ui.viewmodel.factory.LoginFactory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity implements LoginViewModel.ViewListener{

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginFactory factory = new LoginFactory(this.getApplication());
        LoginViewModel loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        loginViewModel.setErrorListener(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        TextView txteView = findViewById(R.id.text_view_register_now);
        txteView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        setAutoValidate(loginViewModel);
        subscribeToModel(loginViewModel);
    }

    private void setAutoValidate(LoginViewModel loginViewModel) {
        MaterialEditText emailEditText = binding.editTextNameOrEmail;
        MaterialEditText passwordEditText = binding.editTextPassword;

        emailEditText.setAutoValidate(true);
        passwordEditText.setAutoValidate(true);

        emailEditText.addValidator(loginViewModel.getEmailValidator());
        passwordEditText.addValidator(loginViewModel.getPasswordValidator());
    }

    private void subscribeToModel(LoginViewModel loginViewModel){
        loginViewModel.loginResponse.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean){
                    Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                }
            }
        });

        loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                SharedPreferences sharedPreferences = getSharedPreferences("challengeApplication", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("user.email", user.getEmail());
                editor.putString("user.name", user.getFullName());

                editor.apply();
            }
        });
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onError(String header, String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
