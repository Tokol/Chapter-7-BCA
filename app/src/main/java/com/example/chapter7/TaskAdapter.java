package com.example.chapter7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter7.modal.TaskModal;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    AppCompatActivity context;
    ArrayList<TaskModal> taskModalList;
    DbHelper dbHelper;

    TaskAdapter(AppCompatActivity context, ArrayList<TaskModal> taskModalList){
        this.context = context;
        this.taskModalList = taskModalList;
        this.dbHelper =  new DbHelper(context);

    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.task_item,parent, false);

        ViewHolder viewHolder = new ViewHolder(listItem);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TaskModal taskModal = taskModalList.get(position);
            holder.taskName.setText(taskModal.getTask());
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fragmentManager = context.getSupportFragmentManager();
                    EditFragment editFragment = new EditFragment(dbHelper, taskModal);
                    editFragment.show(fragmentManager,"MyDialogFragment");


                }
            });


            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DbHelper dbHelper =  new DbHelper(context);
                    dbHelper.deleteDAta(taskModal.getId()+"");
                }
            });



    }

    @Override
    public int getItemCount() {
        return taskModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        Button edit, delete;

        public ViewHolder(View listItem) {
            super(listItem);
            taskName = listItem.findViewById(R.id.taskNameText);
            edit = listItem.findViewById(R.id.editBtn);
            delete = listItem.findViewById(R.id.delete_btn);
        }
    }
}
