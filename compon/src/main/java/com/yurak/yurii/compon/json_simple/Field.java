package com.yurak.yurii.compon.json_simple;

import com.yurak.yurii.compon.interfaces_classes.IBase;

public class Field {
    public static final int TYPE_LIST = 0;
    public static final int TYPE_CLASS = 1;
    public static final int TYPE_STRING = 2;
    public static final int TYPE_INTEGER = 3;
    public static final int TYPE_LONG = 4;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_BOOLEAN = 7;
    public static final int TYPE_DATE = 8;
    public static final int TYPE_RECORD = 9;

    public static final int TYPE_NULL = 20;

    public String name;
    public int type;
    public Object value;

    public Field() {
    }

    public Field(String name, int type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public void setValue(Object value, int viewId, IBase iBase) {
        this.value = value;
    }
}
