package com.example.anailson.sharksenhas.controller;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import com.example.anailson.sharksenhas.R;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Anailson on 24/05/2016.
 */
public class LoginController {

    private Context context;

    public LoginController(Context context){
        this.context = context;
    }

    public String validatePassword(EditText edtPassword, EditText edtPasswordConfirm){

        String password = edtPassword.getText().toString();
        String passwordConfirm = edtPasswordConfirm.getText().toString();

        if(password.equals("") || password.equals(null)) {

            return context.getString(R.string.err_password_null);

        } else if (passwordConfirm.equals("") || passwordConfirm.equals(null)){

            return context.getString(R.string.err_confirm_password_null);

        } else if(!password.equals(passwordConfirm)){

            return context.getString(R.string.err_password_not_validate);

        } else {

            return context.getString(R.string.err_login_validate);
        }
    }
}
