package com.example.anailson.sharksenhas.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.anailson.sharksenhas.R;

import java.util.List;

/**
 * Created by Anailson on 28/05/2016.
 */
public class TaskController  {

    private Context context;
    private List<String> itens;

    public TaskController(Context context, List<String> itens) {
        this.context = context;
        this.itens = itens;
    }

    public ArrayAdapter<String> getAdapter(){
        return new TaskListAdapter(context, itens);
    }

    private class TaskListAdapter extends ArrayAdapter<String> implements AdapterView.OnItemClickListener{

        public TaskListAdapter(Context context, List<String> itens) {
            super(context, R.layout.list_tasks, itens);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_tasks, parent, false);

            TextView txtTask = (TextView) view.findViewById(R.id.task);
            txtTask.setText(itens.get(position));

            return view;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        }
    }
}
