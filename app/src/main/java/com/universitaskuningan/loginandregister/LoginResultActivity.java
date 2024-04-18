package com.universitaskuningan.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LoginResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView tvPassword = findViewById(R.id.tvPassword);

        tvUsername.setText(username);
        tvPassword.setText(password);
    }
}