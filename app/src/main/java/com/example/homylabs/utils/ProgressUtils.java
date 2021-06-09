package com.example.homylabs.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;

import com.kaopiz.kprogresshud.KProgressHUD;

public class ProgressUtils {

    KProgressHUD hud = null;

    public void showProgressDialog(Context context) {

        if (hud == null) {
            hud = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setBackgroundColor(Color.parseColor("#FCA311"))
                    .setCancellable(false)
                    .setLabel("Please Wait...")
                    .show();
        }
    }

    public void dismissProgressDialog() {
        if (hud != null) {
            if (hud.isShowing()) {
                hud.dismiss();
                hud = null;
            }
        }
    }

    public void alertDialog(Context context, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("HomyLabs");
        builder.setMessage(msg);
        builder.setPositiveButton("Ok", (dialog, which) ->
                dialog.dismiss()
        );

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
