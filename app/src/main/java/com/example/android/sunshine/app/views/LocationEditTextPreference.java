package com.example.android.sunshine.app.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.sunshine.app.R;

/**
 * Created by Jayson Dela Cruz on 10/25/2016.
 */

public class LocationEditTextPreference extends EditTextPreference{
    static final String LOG_TAG = LocationEditTextPreference.class.getSimpleName();
    int mMinLength;
    public static final int DEFAULT_MINIMUM_LOCATION_LENGTH = 3;

    public LocationEditTextPreference(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LocationEditTextPreference,
                0,
                0);
        try{
            mMinLength = a.getInteger(R.styleable.LocationEditTextPreference_minLength,
                    DEFAULT_MINIMUM_LOCATION_LENGTH);
            Log.d(LOG_TAG, "Min length is " + mMinLength);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        // Get edit text box
        EditText editText = getEditText();

        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Dialog d = getDialog();
                if(d instanceof android.app.AlertDialog){
                    android.app.AlertDialog dialog = (android.app.AlertDialog) d;
                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    // Check if the EditText is empty
                    if(editable.length() < mMinLength){
                        // Disable OK button
                        positiveButton.setEnabled(false);
                    } else {
                        // Re-enable the button
                        positiveButton.setEnabled(true);
                    }
                }
            }
        });

    }
}
