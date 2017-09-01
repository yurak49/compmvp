package com.yurak.yurii.compon.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurak.yurii.compon.base.BaseActivity;
import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.interfaces_classes.ViewHandler;
import com.yurak.yurii.compon.json_simple.Field;
import com.yurak.yurii.compon.json_simple.Record;

import java.util.List;

public class ComponentMultiPanel extends BaseComponent {

    private WorkWithRecordsAndViews workWithRecordsAndViews = new WorkWithRecordsAndViews();
//    private ViewGroup view;
    private LayoutInflater mInflater;

    public ComponentMultiPanel(IBase iBase, ParamComponent paramMV) {
        super(iBase, paramMV);
    }

    @Override
    public void initView() {
        mInflater = (LayoutInflater) (iBase.getBaseActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewComponent = (ViewGroup) parentLayout.findViewById(paramMV.paramView.viewId);
        if (viewComponent == null) {
            Log.i("SMPL", "Не найдена панель в " + paramMV.nameParentComponent);
        }
    }

    @Override
    public void changeData(Field field) {
        setView();
    }

    private void setView() {
        Record record = (Record) paramMV.paramModel.field.value;
        ((ViewGroup)viewComponent).removeAllViews();
        if (record != null && record.size() > 0) {
            ((ViewGroup)viewComponent).addView(mInflater.inflate(paramMV.paramView.layoutTypeId[0], null));
            workWithRecordsAndViews.RecordToView((Record) paramMV.paramModel.field.value,
                    viewComponent, paramMV.navigator, click);
        } else {
            ((ViewGroup)viewComponent).addView(mInflater.inflate(paramMV.paramView.layoutFurtherTypeId[0], null));
            workWithRecordsAndViews.RecordToView((Record) paramMV.paramModel.field.value,
                    viewComponent, paramMV.navigator, click);
        }
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int vId = v.getId();
            List<ViewHandler> viewHandlers = paramMV.navigator.viewHandlers;
            for (ViewHandler vh : viewHandlers) {
                if (vId == vh.viewId) {
                    switch (vh.type) {
                        case SEND_CHANGE_BACK :

                            break;
                        case CLOSE_DRAWER :
                            ((BaseActivity) activity).closeDrawer();
                            break;
                        case NAME_FRAGMENT :
                            iBase.startFragment(vh.nameFragment, false);
                            break;
                    }
                }
            }
        }
    };
}
