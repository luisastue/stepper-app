package com.example.myapplication.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.myapplication.MainActivity;

public class ErrorMessage {

    public void createDialog(String message, String title, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
