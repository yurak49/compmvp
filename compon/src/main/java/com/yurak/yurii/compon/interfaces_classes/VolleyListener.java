package com.yurak.yurii.compon.interfaces_classes;

import com.android.volley.VolleyError;

public interface VolleyListener<T> {
    public void onErrorResponse(VolleyError error);

    public void onResponse(T response);
}
