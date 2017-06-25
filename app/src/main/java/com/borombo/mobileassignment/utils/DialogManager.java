package com.borombo.mobileassignment.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * Created by Borombo on 25/06/2017.
 */

public class DialogManager {

    private static final DialogManager instance = new DialogManager();

    private DialogManager(){}

    public static DialogManager getInstance(){ return instance;}

    public AlertDialog.Builder getSimpleDialog(Context context, String title, String text,
                                               String negativeButtonText, String positiveButtonText,
                                               DialogInterface.OnClickListener negativeButton,
                                               DialogInterface.OnClickListener positiveButton){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title)
                .setMessage(text)
                .setNegativeButton(negativeButtonText, negativeButton)
                .setPositiveButton(positiveButtonText, positiveButton);

        return builder;
    }

    public AlertDialog.Builder getDialogWithInput(Activity activity, String title, String text,
                                                  EditText input,
                                                  String negativeButtonText, String positiveButtonText,
                                                  DialogInterface.OnClickListener negativeButton,
                                                  DialogInterface.OnClickListener positiveButton){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        builder.setTitle(title)
                .setView(input)
                .setMessage(text)
                .setNegativeButton(negativeButtonText, negativeButton)
                .setPositiveButton(positiveButtonText, positiveButton);

        return builder;
    }
}
