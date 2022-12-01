package com.example.alertsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.AdapterView;

public interface DialogeClass {
    public static void showDD(AdapterView.OnItemClickListener context, String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder((Context) context);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
