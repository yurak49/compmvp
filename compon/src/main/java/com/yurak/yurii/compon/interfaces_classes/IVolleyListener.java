package com.yurak.yurii.compon.interfaces_classes;

import com.android.volley.VolleyError;

public interface IVolleyListener<T> {
        public void onErrorResponse(VolleyError error);

        public void onResponse(T response);
}
