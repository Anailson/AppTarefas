package com.example.anailson.sharksenhas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anailson.sharksenhas.R;

public class NewTaskActivity extends AppCompatActivity {

    private EditText edtNewTask;
    private Button btnConfirmNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        edtNewTask = (EditText) findViewById(R.id.edt_new_task);
        btnConfirmNewTask = (Button) findViewById(R.id.btn_confirm_new_task);

        btnConfirmNewTask.setOnClickListener(new BtnConfirmNewTaskClickListener());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9) , (int)(height * .5));
    }

    private class BtnConfirmNewTaskClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            String flag = getString(R.string.menu_new_task);
            String text = edtNewTask.getText().toString();

            Intent intent = new Intent();
            intent.putExtra(flag, text);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
