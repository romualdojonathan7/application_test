package br.com.jonathan.challenge.ui.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.jonathan.challenge.MainActivity;
import br.com.jonathan.challenge.R;

public class SplashScreenActivity extends Activity {

    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {

                  startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                  finish();
              }
          }, 2000);
    }
}