package com.yurak.yurii.compon.models;

import android.widget.EditText;

import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.json_simple.Field;

public class ComponentSearch extends BaseComponent {

    EditText search;

    public ComponentSearch(IBase iBase, ParamComponent paramMV) {
        super(iBase, paramMV);
    }

    @Override
    public void initView() {
        search = (EditText) parentLayout.findViewById(paramMV.paramView.viewId);
    }

    @Override
    public void changeData(Field field) {

    }
}
