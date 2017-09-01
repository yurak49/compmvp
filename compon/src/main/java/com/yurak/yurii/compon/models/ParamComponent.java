package com.yurak.yurii.compon.models;


import com.yurak.yurii.compon.base.BaseComponent;
import com.yurak.yurii.compon.interfaces_classes.MoreWork;
import com.yurak.yurii.compon.interfaces_classes.Navigator;

public class ParamComponent <T>{
    public static enum TC {PANEL, PANEL_ENTER, PANEL_MULTI, SPINNER,
        RECYCLER, RECYCLER_HORIZONTAL, RECYCLER_GRID, RECYCLER_LEVEL, RECYCLER_STICKY,
        MENU,
        STATIC_LIST, MODEL, PAGER_V, PAGER_F};
    public ParamComponent () {
        additionalWork = null;
    }
    public String nameParentComponent;
    public String name;
    public TC type;
//    public BaseComponent component;
    public int eventComponent;
    public MoreWork moreWork;
    public BaseComponent baseComponent;
//    public String additionalWork;
    public ParamModel paramModel;
    public ParamView paramView;
    public Navigator navigator;
    public Class<T> additionalWork;
}
