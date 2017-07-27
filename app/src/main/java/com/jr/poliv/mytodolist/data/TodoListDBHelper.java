package com.jr.poliv.mytodolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jr.poliv.mytodolist.data.TodoListContract.TodoEntry;

/**
 * Created by poliv on 7/26/2017.
 */

public class TodoListDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todolist.db";

    public TodoListDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + TodoEntry.TABLE_NAME + " (" +
                TodoEntry._ID + " INTEGER PRIMARY KEY, " +
                TodoEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                TodoEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                TodoEntry.COLUMN_DONE + " INTEGER, "  +
                "UNIQUE ( " + TodoEntry.COLUMN_DATE + ", " + TodoEntry.COLUMN_DESCRIPTION + " ) ON " +
                "CONFLICT IGNORE" +
                " );";

        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TodoEntry.TABLE_NAME);
        onCreate(db);
    }
}
