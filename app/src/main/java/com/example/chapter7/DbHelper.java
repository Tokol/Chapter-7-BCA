package com.example.chapter7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.chapter7.modal.TaskModal;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TODO";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "task";


    private static final String TABLE_COLUMN_ID = "id";
    private static final String TABLE_COLUMN_NAME = "taskName";
    private static final String TABLE_COLUMN_IS_COMPLETE = "taskComplete";



    DbHelper(AppCompatActivity context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //onCreate();
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creatingTaskTable = "CREATE TABLE "+TABLE_NAME+ "( "+TABLE_COLUMN_ID +" INTEGER Primary KEY ,"+
                TABLE_COLUMN_NAME + " TEXT ,"+
                TABLE_COLUMN_IS_COMPLETE + " TEXT"+ " )";


        Log.d("CREATINGTABLE", creatingTaskTable);
        sqLiteDatabase.execSQL(creatingTaskTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+  DATABASE_NAME);
        onCreate(sqLiteDatabase);

    }


    public ArrayList<TaskModal> selectTask(){
        ArrayList<TaskModal> taskModals = new ArrayList<TaskModal>();
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = database.rawQuery(query,null);
        if(cursor != null && cursor.getCount()>0){


            while (cursor.moveToNext()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String isCompelete = cursor.getString(2);

                taskModals.add(new TaskModal(Integer.parseInt(id), name, isCompelete));

                Log.d("id",id.toString());
            }

            Log.d("Length of Task", taskModals.size()+"");
        } else{
            Log.d("empty", "empty");

        }

        Log.d("SELECTCURSOR", cursor.toString());
        return taskModals;
    }



    public  void insertData (TaskModal taskModal){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(TABLE_COLUMN_NAME, taskModal.getTask());
        contentValues.put(TABLE_COLUMN_IS_COMPLETE, taskModal.getIsComplete());

        database.insert(TABLE_NAME, null, contentValues);
        database.close();

    }


    public void updateData (int id, TaskModal taskModal){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



        contentValues.put(TABLE_COLUMN_NAME, taskModal.getTask());
        contentValues.put(TABLE_COLUMN_IS_COMPLETE, taskModal.getIsComplete());

        database.update(TABLE_NAME, contentValues, "id=?", new String[] {(String.valueOf(id))});
        database.close();
    }

    public void deleteDAta(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,"id=?", new String[] {(String.valueOf(id))});
        database.close();
    }




}
