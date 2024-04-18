package com.universitaskuningan.loginandregister;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    EditText etUsername, etEmail, etPass;
    Button btnRegisterResult,btnLogin,btnRegister;

    ImageView imgGoogleLogin, imgTwitterLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        btnRegisterResult = view.findViewById(R.id.btn_register_result);
        btnLogin = view.findViewById(R.id.btn_login_2);
        btnRegister = view.findViewById(R.id.btn_register_2);

        etUsername = view.findViewById(R.id.et_username);
        etEmail = view.findViewById(R.id.et_email);
        etPass = view.findViewById(R.id.et_password);

        btnRegisterResult.setOnClickListener(v -> {
            register();
        });

        btnRegister.setOnClickListener(v -> {
            register();
        });

        btnLogin.setOnClickListener(v -> {
            navigateToLogin();
        });

        imgGoogleLogin = view.findViewById(R.id.img_google_login_2);
        imgGoogleLogin.setOnClickListener(v -> {
            onGoogleLoginClick();
        });

        imgTwitterLogin = view.findViewById(R.id.img_twitter_login_2);
        imgTwitterLogin.setOnClickListener(v -> {
            onTwitterLoginClick();
        });

        return view;
    }

    private void register() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPass.getText().toString().trim();

        // Memeriksa apakah semua kolom form telah diisi
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // Menampilkan peringatan bahwa semua kolom harus diisi
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Semua kolom form telah diisi, lanjutkan dengan proses registrasi
            Intent intent = new Intent(getActivity(), RegisterResultActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            startActivity(intent);
        }
    }

    private void navigateToLogin(){
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onGoogleLoginClick() {
        // Buat Intent untuk membuka aplikasi Google
        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.googlequicksearchbox");

        if (intent != null) {
            // Jika aplikasi Google terinstal, buka aplikasi tersebut
            startActivity(intent);
        } else {
            // Jika aplikasi Google tidak terinstal, beri tahu pengguna atau berikan alternatif lain
            // Misalnya, buka browser dengan halaman login Google
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com"));
            startActivity(browserIntent);
        }
    }

    public void onTwitterLoginClick(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com"));
        startActivity(intent);
    }
}