package com.yurak.yurii.compon.models;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yurak.yurii.compon.SimpleApp;
import com.yurak.yurii.compon.base.BaseActivity;
import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.base.BaseProvider;
import com.yurak.yurii.compon.base.BaseProviderAdapter;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.interfaces_classes.ViewHandler;
import com.yurak.yurii.compon.json_simple.Field;
import com.yurak.yurii.compon.json_simple.ListRecords;
import com.yurak.yurii.compon.json_simple.Record;
import com.yurak.yurii.compon.presenter.ListPresenter;
import com.yurak.yurii.compon.tools.StaticVM;

public class ComponentMenu extends BaseComponent {
    RecyclerView recycler;
    ListRecords listData;
    BaseProviderAdapter adapter;

    public ComponentMenu(IBase iBase, ParamComponent paramMV) {
        super(iBase, paramMV);
    }

    @Override
    public void initView() {
        if (paramMV.paramView == null || paramMV.paramView.viewId == 0) {
            recycler = (RecyclerView) StaticVM.findViewByName(parentLayout, "recycler");
        } else {
            recycler = (RecyclerView) parentLayout.findViewById(paramMV.paramView.viewId);
        }
        if (recycler == null) {
            Log.i("SMPL", "Не найден RecyclerView для Menu в " + paramMV.nameParentComponent);
        }

        for (ViewHandler vh : navigator.viewHandlers) {
            if (vh.viewId == 0 && vh.type == ViewHandler.TYPE.FIELD_WITH_NAME_FRAGMENT) {
                selectViewHandler = vh;
                break;
            }
        }
        Log.d("QWERT","selectViewHandler="+selectViewHandler);
        listData = new ListRecords();
        listPresenter = new ListPresenter(this);
        provider = new BaseProvider(listData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recycler.setLayoutManager(layoutManager);
        adapter = new BaseProviderAdapter(this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void changeData(Field field) {
        listData.clear();
        listData.addAll((ListRecords) field.value);
        provider.setData(listData);
        int selectStart = -1;
        int ik = listData.size();
        for (int i = 0; i < ik; i++) {
            Record r = listData.get(i);
            int j = (Integer) r.getValue("select");
            if (j > 1) {
                selectStart = i;
                break;
            }
        }
        listPresenter.changeData(listData, selectStart);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changeDataPosition(int position, boolean select) {
        adapter.notifyItemChanged(position);
        if (select && selectViewHandler != null) {
            ((BaseActivity) activity).closeDrawer();
            Record record = listData.get(position);
            SimpleApp.getInstance().setParam(record);
            iBase.startFragment((String) record.getValue(selectViewHandler.nameFragment), true);
        }
    }

}
