package com.jr.poliv.mytodolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.jr.poliv.mytodolist.data.TodoListContract;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, TodoCursorAdapter.ToggleTodoCheckListener {

    private TodoCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager mLinerLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinerLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);

        cursorAdapter = new TodoCursorAdapter(null, this);

        mRecyclerView.setAdapter(cursorAdapter);

        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.floating_action_btn);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskDialogFragment dialogFragment = new AddTaskDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "addTask");
            }
        });

        getSupportLoaderManager().initLoader(0, null, this);




    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, TodoListContract.TodoEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            cursorAdapter.swapCursor(data); Log.d("Paul", "onLoadFinish");



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
        loader.cancelLoad();
    }

    @Override
    public void onTodoItemChange(int todoID, boolean done) {
        ContentValues values = new ContentValues();
        values.put(TodoListContract.TodoEntry.COLUMN_DONE, done ? 1 : 0);
        String where = TodoListContract.TodoEntry._ID + " = " + todoID + "";
        getContentResolver().update(TodoListContract.TodoEntry.CONTENT_URI, values, where,null);
    }
}
