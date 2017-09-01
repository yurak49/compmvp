package com.yurak.yurii.compon.models;

import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.interfaces_classes.ParentModel;
import com.yurak.yurii.compon.json_simple.Field;

public class ComponentModel extends BaseComponent {

    @Override
    public void initView() {

    }

    @Override
    public void changeData(Field field) {
        ParentModel pm = iBase.getParentModel(paramMV.name);
        pm.field = field;
        for (BaseComponent bc : pm.componentList) {
            bc.setParentData(field);
        }
    }

    public ComponentModel(IBase iBase, ParamComponent paramMV) {
        super(iBase, paramMV);
    }
}
