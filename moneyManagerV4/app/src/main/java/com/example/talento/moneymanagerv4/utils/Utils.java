package com.example.talento.moneymanagerv4.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ErickAlejandro on 24/10/2015.
 */
public class Utils {
    public static void showToast(Context c, String msg){
        Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
    }
}
