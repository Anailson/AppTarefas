package com.example.anailson.sharksenhas.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anailson.sharksenhas.R;
import com.example.anailson.sharksenhas.controller.LoginController;
import com.example.anailson.sharksenhas.persistence.FirebasePersistence;


public class LoginActivity extends AppCompatActivity  {

    private LoginController controller;
    private FirebasePersistence persistence;
    private Toast toast = null;
    private EditText edtEmail, edtPassword, edtPasswordConfirm;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        persistence = new FirebasePersistence(this);
        controller = new LoginController(this);

        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtPasswordConfirm = (EditText) findViewById(R.id.edt_password_confirm);
        btnLogin = (Button) findViewById(R.id.btn_save);

        btnLogin.setOnClickListener(new BtnSalvar());
    }

    private class BtnSalvar implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String message = controller.validatePassword(edtPassword, edtPasswordConfirm);
            if (message.equals(getString(R.string.err_login_validate))){

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                persistence.createUser(email, password);

                startMainActivity();
            }
            toast = getToast(message);
            toast.show();
        }
    }

    private void startMainActivity(){

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private Toast getToast(String message){

        if(toast == null){
            return Toast.makeText(this, message, Toast.LENGTH_SHORT);
        }

        if(toast.getView().isShown()){
            toast.cancel();
        }
        return Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }
}

