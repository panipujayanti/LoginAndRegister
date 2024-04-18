package com.universitaskuningan.loginandregister;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    EditText etUsername,etPass;
    Button btnLoginResult,btnLogin,btnRegister;

    ImageView imgGoogleLogin, imgTwitterLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btnLoginResult = view.findViewById(R.id.btn_login_result);
        btnLogin = view.findViewById(R.id.btn_login_1);
        btnRegister = view.findViewById(R.id.btn_register_1);

        etUsername = view.findViewById(R.id.et_username);
        etPass = view.findViewById(R.id.et_password);

        btnLoginResult.setOnClickListener(v -> {
            login();
        });

        btnLogin.setOnClickListener(v -> {
            login();
        });

        btnRegister.setOnClickListener(v -> {
            navigateToRegister();
        });

        imgGoogleLogin = view.findViewById(R.id.img_google_login_1);
        imgGoogleLogin.setOnClickListener(v -> {
            onGoogleLoginClick();
        });

        imgTwitterLogin = view.findViewById(R.id.img_twitter_login_1);
        imgTwitterLogin.setOnClickListener(v -> {
            onTwitterLoginClick();
        });
        return view;
    }

    private void login(){
        String username = etUsername.getText().toString();
        String password = etPass.getText().toString();
        
        if (username.isEmpty() || password.isEmpty()) {
            // Menampilkan peringatan bahwa semua kolom harus diisi
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Semua kolom form telah diisi, lanjutkan dengan proses login
            Intent intent = new Intent(getActivity(), LoginResultActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            startActivity(intent);
        }
    }

    private void navigateToRegister(){
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, registerFragment);
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