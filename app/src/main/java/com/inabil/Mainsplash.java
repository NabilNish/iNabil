package com.inabil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.inabil.helper.AuthLogin;

/**
 * Created by NABIL on 28-11-2017.
 */

public class Mainsplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainsplash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = null;

                if (AuthLogin.getUser(Mainsplash.this) == null)
                    intent = new Intent(Mainsplash.this, MainActivity.class);
                else
                    intent = new Intent(Mainsplash.this, HomeActivity.class);

                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}

