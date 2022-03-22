package com.example.chapter7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.chapter7.modal.TaskModal;

public class MainActivity extends AppCompatActivity {
EditText taskEntry;
Button taskBtn;
DbHelper dbHelper;
FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskEntry= findViewById(R.id.createTask);
        taskBtn= findViewById(R.id.button);
        frameLayout = findViewById(R.id.frameLayout);
        dbHelper = new DbHelper(MainActivity.this);
        dbHelper.getWritableDatabase();
        displayList();
        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insertData(new TaskModal(null,taskEntry.getText().toString(),"False"));

                displayList();

            }
        });
    }


    void displayList(){
        FragmentManager fragmentManager  = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        TaskListFragment taskListFragment = new TaskListFragment(MainActivity.this);

        transaction.replace(R.id.frameLayout, taskListFragment);
        transaction.commit();
    }




}