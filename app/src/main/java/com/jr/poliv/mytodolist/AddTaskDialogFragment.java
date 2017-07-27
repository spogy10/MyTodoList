package com.jr.poliv.mytodolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jr.poliv.mytodolist.data.TodoListContract;
import com.jr.poliv.mytodolist.service.TodoListService;

/**
 * Created by poliv on 7/26/2017.
 */

public class AddTaskDialogFragment extends DialogFragment {

    public static AddTaskDialogFragment getInstance(String idTask){
        Bundle bundle = new Bundle();
        bundle.putString("ID_TASK", idTask);
        AddTaskDialogFragment fragment = new AddTaskDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
        builder.setView(R.layout.task_create_dialog);
        builder.setTitle(R.string.add_task_dialog_tittle);
        builder.setPositiveButton(R.string.add_task_dialog_postive_button_tittle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText ed = (EditText)((android.support.v7.app.AlertDialog) dialog).findViewById(R.id.edit_add_dialog);
                Intent intent = new Intent(getContext(), TodoListService.class);
                Log.d("Paul", String.valueOf(ed.getText()));
                intent.putExtra(TodoListService.EXTRA_TASK_DESCRIPTION, String.valueOf(ed.getText()));
                getActivity().startService(intent);
            }
        }).setNegativeButton(R.string.add_task_dialog_negative_button_tittle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        return builder.create();
    }
}
