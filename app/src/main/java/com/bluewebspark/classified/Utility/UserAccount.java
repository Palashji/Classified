package com.bluewebspark.classified.Utility;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

public class UserAccount {


    public static EditText EditTextPointer;
    public static String errorMessage;
    private EditText userName, password;
    private Context mCont;

    public UserAccount(Context mCont, EditText un, EditText pw) {
        this.userName = un;
        this.password = pw;
        this.mCont = mCont;
    }

    public static boolean isEmailValid(EditText tv) {
        //add your own logic
        if (TextUtils.isEmpty(tv.getText())) {
            EditTextPointer = tv;
            EditTextPointer.requestFocus();
            errorMessage = "This field can't be empty.!";
            return false;
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(tv.getText()).matches()) {
                return true;
            } else {
                EditTextPointer = tv;
                EditTextPointer.requestFocus();
                EditTextPointer.setError("enter valid email");
                errorMessage = "invalid email id.!";
                return false;
            }
        }
    }

    public static boolean isPasswordValid(EditText tv) {
        //add your own logic
        if (tv.getText().toString().length() >= 6) {
            return true;
        } else {
            EditTextPointer = tv;
            errorMessage = "Plz enter greater than 6 char";
            EditTextPointer.requestFocus();
            EditTextPointer.setError(" Plz enter greater than 6 char");
            return false;
        }
    }

    public static boolean isPhoneLengthValid(EditText tv) {
        //add your own logic
        if (tv.getText().toString().length() >= 10) {
            if (!tv.getText().toString().contains("+")) {
                return true;
            } else {
                EditTextPointer = tv;
                EditTextPointer.requestFocus();
                EditTextPointer.setError("Remove + or county code");
                errorMessage = "Remove + or county code";
                return false;
            }
        } else {
            EditTextPointer = tv;
            EditTextPointer.requestFocus();
            EditTextPointer.setError("enter 10 digit number");
            errorMessage = "greater than 10 char";
            return false;
        }
    }

    public static boolean isValidPhoneNumber(EditText tv) {
        if (tv.getText() == null || TextUtils.isEmpty(tv.getText())) {
            return false;
        } else {
            if (android.util.Patterns.PHONE.matcher(tv.getText()).matches()) {
                return true;
            } else {
                EditTextPointer = tv;
                EditTextPointer.requestFocus();
                EditTextPointer.setError(" Plz enter valid number");
                errorMessage = "invalid mobile number.";
                return false;
            }
        }
    }

    public static boolean isEmpty(EditText... arg) {
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].getText().length() <= 0) {
                EditTextPointer = arg[i];
                EditTextPointer.requestFocus();
                EditTextPointer.setError("this can't be empty");
                return false;
            }

        }
        return true;
    }
}



