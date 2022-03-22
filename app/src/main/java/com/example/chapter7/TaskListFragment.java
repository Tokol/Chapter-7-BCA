package com.example.chapter7;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chapter7.modal.TaskModal;

import java.util.ArrayList;
import java.util.List;

public class TaskListFragment extends Fragment {

    AppCompatActivity context;

    TaskListFragment(AppCompatActivity context){
        this.context = context;
    }
RecyclerView recyclerView;
RecyclerView.Adapter adapter;
RecyclerView.LayoutManager layoutManager;

    ArrayList<TaskModal> taskModalList;


    DbHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        recyclerView = view.findViewById(R.id.task_recyclerView);
        dbHelper = new DbHelper(context);
        taskModalList =  dbHelper.selectTask();
        adapter = new TaskAdapter(context,taskModalList);


        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        




        recyclerView.setAdapter(adapter);

        return  view;
    }



}