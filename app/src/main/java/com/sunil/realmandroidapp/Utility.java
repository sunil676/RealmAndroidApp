package com.sunil.realmandroidapp;

import android.support.design.widget.TextInputLayout;
import android.view.View;

/**
 * Created by sunil on 12/14/16.
 */

public class Utility {

    public static boolean nullCheck(TextInputLayout layout , String label) {
        label = label.replace("*","");
        String name = layout.getEditText().getText().toString().trim();
        if(name.isEmpty()){
            showErrorMsg(layout);
            layout.setError(label+ " should not be empty");
            try {
                //In Case Of Edit Text With POP - UP
                if (layout.getEditText().isFocusableInTouchMode()) {
                    layout.getEditText().requestFocus();
                } else {
                    layout.getEditText().setFocusableInTouchMode(true);
                    layout.getEditText().requestFocus();
                    layout.getEditText().setFocusableInTouchMode(false);
                }
            }
            catch (Exception e){
                layout.getEditText().requestFocus();
            }
            return true;
        }
        else{
            removeErrorMsg(layout);
            return false;
        }
    }

    public static void showErrorMsg(TextInputLayout layout){
        if(layout.getChildCount()==2){
            layout.getChildAt(1).setVisibility(View.VISIBLE);
        }
    }

    public static void removeErrorMsg(TextInputLayout layout){
        layout.setError(null);
        layout.setErrorEnabled(false);
        if(layout.getChildCount()==2){
            layout.getChildAt(1).setVisibility(View.GONE);
        }
    }

}