package com.yurak.yurii.compon.models;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.yurak.yurii.compon.SimpleApp;
import com.yurak.yurii.compon.adapters.SpinnerAdapter;
import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.base.BaseProvider;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.json_simple.Field;
import com.yurak.yurii.compon.json_simple.ListRecords;
import com.yurak.yurii.compon.json_simple.Record;

public class ComponentSpinner extends BaseComponent {
    Spinner spinner;
    ListRecords listSpinner;

    @Override
    public void initView() {
        spinner = (Spinner) parentLayout.findViewById(paramMV.paramView.viewId);
//        String st = baseActivity.installParam(paramMV.paramModel.param);
    }

    @Override
    public void changeData(Field field) {
        listSpinner = (ListRecords) field.value;
        BaseProvider provider = new BaseProvider(listSpinner);
//        provider.setData(listSpinner);
        SpinnerAdapter adapter = new SpinnerAdapter(provider, paramMV);
        spinner.setAdapter(adapter);
//            spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Record record = listSpinner.get(position);
                SimpleApp.getInstance().setParam(record);
                iBase.sendEvent(paramMV.paramView.viewId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public ComponentSpinner(IBase iBase, ParamComponent paramMV) {
        super(iBase, paramMV);
    }
}
