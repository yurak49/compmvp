package com.yurak.yurii.compon.interfaces_classes;

import android.view.View;
import com.android.volley.Request;
import com.yurak.yurii.compon.base.BaseActivity;
import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.base.BaseFragment;
import com.yurak.yurii.compon.json_simple.Field;

public interface IBase<T> {
    public BaseActivity getBaseActivity();
    public BaseFragment getBaseFragment();
    public View getParentLayout();
    public void addEvent(int sender, BaseComponent receiver);
    public void sendEvent(int sender);
    public ParentModel getParentModel(String name);
    public Field getProfile();
    public void startActivitySimple(T clazz);
    public void startFragment(String nameMVP, boolean startFlag);
    public void backPressed();
    public void progressStart();
    public void progressStop();
    public void showDialog(String title, String message, View.OnClickListener click);
    public boolean isViewActive();
    public void addRequest(Request request);
}
