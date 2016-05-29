package com.example.anailson.sharksenhas.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anailson.sharksenhas.R;
import com.example.anailson.sharksenhas.controller.TaskController;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends Fragment {

    private TaskController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_task, container, false);

        List<String> listTasks = getListTasks();
        controller = new TaskController(this.getContext(), listTasks);

        ListView listView = (ListView) view.findViewById(R.id.list_tasks);
        listView.setAdapter(controller.getAdapter());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_task, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_new_task:

                Intent intent = new Intent(this.getContext(), NewTaskActivity.class);
                startActivityForResult(intent, 1);

                return true;

            default:
                return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            String flag = getString(R.string.menu_new_task);
            String newTask = data.getStringExtra(flag);
            Toast.makeText(this.getContext(), newTask, Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<String> getListTasks(){

        String [] arrayTasks = getResources().getStringArray(R.array.list_tasks);
        ArrayList <String> list = new ArrayList<>();
        for(int i = 0; i < arrayTasks.length; i++){
            list.add(new String(arrayTasks[i]));
        }
        return list;
    }
}
