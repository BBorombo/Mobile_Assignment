package com.borombo.mobileassignment.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.utils.DialogManager;
import com.borombo.mobileassignment.utils.LocationsManager;

/**
 * Created by Borombo on 25/06/2017.
 */

public class MyPreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            Preference button = findPreference(getString(R.string.reset));
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {


                    final DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LocationsManager.getInstance().deleteAll();
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);
                        }
                    };

                    AlertDialog.Builder builder = DialogManager.getInstance().getSimpleDialog(getActivity(),
                            getString(R.string.deleteAllLocationTitle),
                            getString(R.string.deleteAllLocationText),
                            getString(R.string.cancel),
                            getString(R.string.ok),
                            null, positiveButton);

                    builder.show();
                    return true;
                }
            });
        }
    }
}
