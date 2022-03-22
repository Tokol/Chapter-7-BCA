package com.example.chapter7;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.chapter7.modal.TaskModal;


public class EditFragment extends DialogFragment {

    DbHelper dbHelper; TaskModal taskModal;
    EditFragment(DbHelper dbHelper, TaskModal taskModal){
        this.dbHelper = dbHelper;
        this.taskModal = taskModal;
    }


    EditText taskText;

    Button editButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        taskText = view.findViewById(R.id.edit_task);
        editButton = view.findViewById(R.id.edit_btn_task);

        TaskModal editTaskModal = new TaskModal(1,taskText.getText().toString(),"False" );


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateData(taskModal.getId(), editTaskModal);
                getDialog().dismiss();
            }
        });
        return view;
    }
}