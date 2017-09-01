package com.yurak.yurii.compon.models;

import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.json_simple.Field;
import com.yurak.yurii.compon.json_simple.Record;

public class ComponentPanel extends BaseComponent {
    @Override
    public void initView() {
    }

    @Override
    public void changeData(Field field) {
        if (field == null) return;
        Record rec = (Record)field.value;
        viewComponent = parentLayout.findViewById(paramMV.paramView.viewId);
//        WorkWithRecordsAndViews rv = new WorkWithRecordsAndViews();
        workWithRecordsAndViews.RecordToView(rec, viewComponent);
    }

    public ComponentPanel(IBase iBase, ParamComponent paramMV) {
        super(iBase, paramMV);
    }
}
