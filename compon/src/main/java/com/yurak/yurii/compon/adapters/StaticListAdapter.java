package com.yurak.yurii.compon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.base.BaseProvider;
import com.yurak.yurii.compon.components.BaseStaticListAdapter;
import com.yurak.yurii.compon.models.ParamComponent;
import com.yurak.yurii.compon.models.WorkWithRecordsAndViews;

public class StaticListAdapter extends BaseStaticListAdapter {

    private ParamComponent mvParam;
    private BaseProvider provider;
    private WorkWithRecordsAndViews modelToView;
    private Context context;

    public StaticListAdapter(BaseComponent baseComponent) {
        this.provider = baseComponent.provider;
        context = baseComponent.iBase.getBaseActivity();
        mvParam = baseComponent.paramMV;
        modelToView = new WorkWithRecordsAndViews();
    }
    @Override
    public int getCount() {
        return provider.getCount();
    }

    @Override
    public View getView(int position) {
        View view = LayoutInflater.from(context).inflate(mvParam.paramView.layoutTypeId[0], null);
        modelToView.RecordToView(provider.get(position), view, null, null);
        return view;
    }

    @Override
    public void onClickView(View view, View viewElrment, int position) {

    }
}
