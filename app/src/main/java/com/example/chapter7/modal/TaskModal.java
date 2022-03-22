package com.example.chapter7.modal;

public class TaskModal {
    private  Integer id;
    private String task;
    private String isComplete;

    public TaskModal(Integer id, String task, String isComplete){
        this.id = id;
        this.task = task;
        this.isComplete = isComplete;
    }

    public int getId(){
        return id;
    }


    public String getTask(){
        return task;
    }


    public String getIsComplete(){
        return isComplete;
    }



}
