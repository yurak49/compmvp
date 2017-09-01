package com.yurak.yurii.compon.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.Request;
import com.yurak.yurii.compon.SimpleApp;
import com.yurak.yurii.compon.interfaces_classes.EventComponent;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.interfaces_classes.ParentModel;
import com.yurak.yurii.compon.json_simple.Field;
import com.yurak.yurii.compon.models.MultiComponents;
import com.yurak.yurii.compon.tools.StaticVM;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends Fragment implements IBase {
    public abstract void initView();
    protected View parentLayout;
    private Object mObject;
    public List<Request> listRequests;
    public MultiComponents mComponent;
    public List<EventComponent> listEvent;
    public List<ParentModel> parentModelList;

    public BaseFragment() {
        mObject = null;
        listRequests = new ArrayList<>();
        listEvent = new ArrayList<>();
        parentModelList = new ArrayList<>();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentLayout = inflater.inflate(mComponent.fragmentLayoutId, null, false);
        TextView title = (TextView) StaticVM.findViewByName(parentLayout, "title");
        if (title != null) {
            if (mComponent.args != null && mComponent.args.length > 0) {
                title.setText(String.format(mComponent.title, setFormatParam(mComponent.args)));
            } else {
                title.setText(mComponent.title);
            }
        }
        initView();
        return parentLayout;
    }

    public void setModel(MultiComponents mComponent) {
        this.mComponent = mComponent;
    }

    public String setFormatParam(String[] args) {
        String st = "";
        BaseActivity ba = (BaseActivity) getActivity();
        List<String> namesParams = SimpleApp.getInstance().namesParams;
        List<String> valuesParams = SimpleApp.getInstance().valuesParams;
        String sep = "";
        int ik = namesParams.size();
        for (String arg : args) {
            String value = "";
            for (int i = 0; i < ik; i++) {
                if (arg.equals(namesParams.get(i))) {
                    st = sep + valuesParams.get(i);
                    sep = ",";
                    break;
                }
            }
        }
        return st;
    }

    @Override
    public void onStop() {
        for (Request request : listRequests) {
            request.cancel();
        }
        mObject = null;
        super.onStop();
    }

    @Override
    public void addEvent(int sender, BaseComponent receiver) {
        listEvent.add(new EventComponent(sender, receiver));
    }

    @Override
    public Field getProfile() {
        return SimpleApp.getInstance().profile;
    }

    @Override
    public void backPressed() {
        ((BaseActivity) getActivity()).onBackPressed();
    }

    @Override
    public BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }

    @Override
    public BaseFragment getBaseFragment() {
        return this;
    }

    @Override
    public void addRequest(Request request) {
        listRequests.add(request);
    }

    @Override
    public void sendEvent(int sender) {
        for (EventComponent ev : listEvent) {
            if (ev.eventSenderId == sender) {
                ev.eventReceiverComponent.actual();
            }
        }
    }

    public ParentModel getParentModel(String name) {
        if (parentModelList.size() > 0) {
            for (ParentModel pm : parentModelList) {
                if (pm.nameParentModel.equals(name)) {
                    return pm;
                }
            }
        }
        ParentModel pm = new ParentModel(name);
        parentModelList.add(pm);
        return pm;
    }

    public String getName() {
        return "Base";
    }

    public void setObject(Object o) {
        mObject = o;
    }

    public Object getObject() {
        return mObject;
    }

    @Override
    public View getParentLayout() {
        return parentLayout;
    }

    @Override
    public void startActivitySimple(Object clazz) {

    }

    @Override
    public void startFragment(String nameMVP, boolean startFlag) {
        getBaseActivity().startFragment(nameMVP, startFlag);
    }

    @Override
    public void progressStart() {
        getBaseActivity().progressStart();
    }

    @Override
    public void progressStop() {
        getBaseActivity().progressStop();
    }

    @Override
    public void showDialog(String title, String message, View.OnClickListener click) {
        getBaseActivity().showDialog(title, message, click);
    }

    @Override
    public boolean isViewActive() {
        return false;
    }
}
