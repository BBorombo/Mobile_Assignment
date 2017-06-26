package com.borombo.mobileassignment.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * Created by Borombo on 25/06/2017.
 * Singleton that handle the creation of the dialogs in the app
 */

public class DialogManager {

    private static final DialogManager instance = new DialogManager();

    private DialogManager(){}

    public static DialogManager getInstance(){ return instance;}

    /**
     * Create a simple dialog
     * @param context The context
     * @param title The title of the dialog
     * @param text The text of the dialog
     * @param negativeButtonText The text for the negative button
     * @param positiveButtonText The text for the positive button
     * @param negativeButton The onClick function for the negative button
     * @param positiveButton The onClick function for the positive button
     * @return The Dialog builder to show
     */
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

    /**
     * Create a simple dialog
     * @param activity The activity
     * @param title The title of the dialog
     * @param text The text of the dialog
     * @param negativeButtonText The text for the negative button
     * @param positiveButtonText The text for the positive button
     * @param negativeButton The onClick function for the negative button
     * @param positiveButton The onClick function for the positive button
     * @return The Dialog builder to show
     */
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
