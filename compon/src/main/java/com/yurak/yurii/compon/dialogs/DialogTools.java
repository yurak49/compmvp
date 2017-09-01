package com.yurak.yurii.compon.dialogs;

import android.app.Activity;
import android.view.View;

public class DialogTools {
    public static void  showDialog(Activity activity, String title, String msg,
                                   BaseDialog.OnClickListener clickPositive,
                                   BaseDialog.OnClickListener clickNegative,
                                   BaseDialog.OnClickListener clickCancel) {
        BaseDialog baseDialog = new BaseDialog();
        baseDialog.setTitle(title);
        baseDialog.setMessage(msg);
        baseDialog.setOnClickListener(clickPositive, clickNegative, clickCancel);
        baseDialog.show(activity.getFragmentManager(), "dialog");
    }

    public static void  showDialog(Activity activity, String title, String msg,
                                   View.OnClickListener clickPositive) {
        BaseDialog baseDialog = new BaseDialog();
        baseDialog.setTitle(title);
        baseDialog.setMessage(msg);
        baseDialog.setOnClickListener(clickPositive);
        baseDialog.show(activity.getFragmentManager(), "dialog");
    }
}
