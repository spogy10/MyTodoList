package com.jr.poliv.mytodolist.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.Nullable;

import com.jr.poliv.mytodolist.data.TodoListContract;

import java.util.Date;

/**
 * Created by poliv on 7/27/2017.
 */

public class TodoListService extends IntentService {
    public static final String EXTRA_TASK_DESCRIPTION = "EXTRA_TASK_DESCRIPTION";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public TodoListService() {
        super("TodoListService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String taskDescription = intent.getStringExtra(EXTRA_TASK_DESCRIPTION);

        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoListContract.TodoEntry.COLUMN_DESCRIPTION, taskDescription);
        Date date = new Date();
        contentValues.put(TodoListContract.TodoEntry.COLUMN_DATE,date.toString());
        contentValues.put(TodoListContract.TodoEntry.COLUMN_DONE,0);


        getContentResolver().insert(TodoListContract.TodoEntry.CONTENT_URI, contentValues);

        
    }
}
