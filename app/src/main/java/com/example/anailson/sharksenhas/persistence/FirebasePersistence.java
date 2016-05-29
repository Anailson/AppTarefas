package com.example.anailson.sharksenhas.persistence;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.anailson.sharksenhas.R;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;
import com.firebase.client.snapshot.BooleanNode;

import java.util.Map;

/**
 * Created by Anailson on 28/05/2016.
 */
public class FirebasePersistence {

    private Context context;
    private String flagEmail, flagPassword, flagLogged, urlDatabase;
    private Firebase firebase;

    public FirebasePersistence(Context context){
        this.flagEmail = context.getString(R.string.email);
        this.flagPassword = context.getString(R.string.password);
        this.flagLogged = context.getString(R.string.logged);
        this.urlDatabase = context.getString(R.string.url_database);
        this.context = context;

//        createUser("-1", "-1");
    }

    public void createUser(final String email, final String password){
        Firebase.setAndroidContext(context);
        firebase = new Firebase(urlDatabase);
        Log.e("CreateUser", "Yooooo");
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {

            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.e("CreateUser", "Yooooo");
                login(firebase, email, password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {

            }
        });
    }
    public void login(final Firebase firebase, String email, String password){
        Log.e("CreateUser", "Yooooo");
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {

                firebase.child("users").child(authData.getUid()).child("status").setValue("New User");
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {

            }
        });
    }

}
