package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    EditText editEmail, editPass;
    TextView btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m001_act_login);
        initControl();
    }

    private void initControl(){
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        btnLogin = (TextView) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            if (!editEmail.getText().toString().isEmpty() && !editEmail.getText().toString().isEmpty()) {
                showToastMessage(editEmail.getText().toString(), editPass.getText().toString());
            }
        }
    }

    private void showToastMessage(String email, String password){

    }
}