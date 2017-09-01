package com.yurak.yurii.compon.models;
import android.util.Log;

import com.yurak.yurii.compon.adapters.StaticListAdapter;
import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.base.BaseProvider;
import com.yurak.yurii.compon.components.StaticList;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.json_simple.Field;
import com.yurak.yurii.compon.json_simple.ListRecords;
import com.yurak.yurii.compon.tools.StaticVM;

public class ComponentStaticList extends BaseComponent {
    StaticList staticList;
    ListRecords listData;
//    BaseProvider provider;
    StaticListAdapter adapter;

    public ComponentStaticList(IBase iBase, ParamComponent paramMV) {
        super(iBase, paramMV);
    }

    @Override
    public void initView() {
        if (paramMV.paramView == null || paramMV.paramView.viewId == 0) {
            staticList = (StaticList) StaticVM.findViewByName(parentLayout, "baseStaticList");
        } else {
            staticList = (StaticList) parentLayout.findViewById(paramMV.paramView.viewId);
        }
        if (staticList == null) {
            Log.i("SMPL", "Не найден StaticList в " + paramMV.nameParentComponent);
            return;
        }
        listData = new ListRecords();
        provider = new BaseProvider(listData);
//        provider.setData(listData);
//        provider.setNavigator(paramMV.navigator);
        adapter = new StaticListAdapter(this);
        staticList.setAdapter(adapter, false);
    }

    @Override
    public void changeData(Field field) {
        listData.clear();
        listData.addAll((ListRecords) field.value);
        provider.setData(listData);
        adapter.notifyDataSetChanged();
    }
}
