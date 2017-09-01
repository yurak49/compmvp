package com.yurak.yurii.compon.base;

import android.text.Html;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.yurak.yurii.compon.SimpleApp;
import com.yurak.yurii.compon.interfaces_classes.DataResponse;
import com.yurak.yurii.compon.interfaces_classes.IBase;
import com.yurak.yurii.compon.interfaces_classes.VolleyListener;
import com.yurak.yurii.compon.json_simple.Field;
import com.yurak.yurii.compon.json_simple.JsonSimple;
import com.yurak.yurii.compon.json_simple.Record;
import com.yurak.yurii.compon.models.ParamModel;
import com.yurak.yurii.compon.network.SimpleRequest;
import com.yurak.yurii.compon.tools.PreferenceTool;
import com.yurak.yurii.compon.volley.VolleyProvider;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePresenter <T> implements Response.Listener<T>, Response.ErrorListener {

    protected int method;
    protected String url;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    protected Object object;
    protected IBase iBase;
    protected VolleyListener listener;
    protected boolean progress;
    protected DataResponse dataResponse;
    protected long currentTime;
    protected VolleyError error;
    protected JsonSimple jsonSimple = new JsonSimple();
    protected long beginTime, endTime;
    protected Calendar calendar;
//    protected BaseComponent baseComponent;
    protected ParamModel paramModel;
    protected String nameJson;
    protected String json;
    protected boolean simpleRequest;

    public BasePresenter(int method, IBase iBase) {
        this.method = method;
        this.iBase = iBase;
        url = null;
        headers = null;
        params = null;
        object = null;
        progress = true;
        dataResponse = null;
        currentTime = 0;
        simpleRequest = false;
    }

    public static BasePresenter requestGet(IBase vs) {
        return new BasePresenter(Request.Method.GET, vs);
    }

    public static BasePresenter requestPost(IBase vs) {
        return new BasePresenter(Request.Method.POST, vs);
    }

    public static BasePresenter requestDelete(IBase vs) {
        return new BasePresenter(Request.Method.DELETE, vs);
    }

    public static BasePresenter requestPut(IBase vs) {
        return new BasePresenter(Request.Method.PUT, vs);
    }

    public BasePresenter withUrl(String url) {
        this.url = url;
        return this;
    }

    public BasePresenter into(DataResponse dr) {
        dataResponse = dr;
        return this;
    }

    public BasePresenter withParams(Map<String, String> params) {
        this.params = params;
//        Log.d("QWERT","withParams params="+params);
        return this;
    }

    public BasePresenter noProgress() {
        progress = false;
        return this;
    }

    public BasePresenter withHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public BasePresenter withData(Object data) {
        object = data;
        return this;
    }

    public BasePresenter(BaseComponent baseComponent, VolleyListener listener) {
        this(baseComponent.iBase, baseComponent.paramMV.paramModel, null, listener);
    }

    public BasePresenter(IBase iBase, ParamModel paramModel, Record object, VolleyListener listener) {
//        this.baseComponent = baseComponent;
        this.paramModel = paramModel;
        this.object = object;
//        this.metod = baseComponent.paramMV.paramModel.method;
        method = paramModel.method;
        this.iBase = iBase;
        this.listener = listener;
//        String st = iBase.getBaseActivity().installParam(baseComponent.paramMV.paramModel.param);
//        url = baseComponent.paramMV.paramModel.url + st;
        if (method == ParamModel.GET) {
            String st = SimpleApp.getInstance().installParam(paramModel.param);
            url = paramModel.url + st;
        } else {
            url = paramModel.url;
        }
//        Log.d("SMPL","url="+url);
        headers = null;
        params = null;
//        this.object = null;
        progress = true;
        simpleRequest = true;
//        long duration = baseComponent.paramMV.paramModel.duration;
        long duration = paramModel.duration;
        if (duration > 0) {
            nameJson = url;
            json = SimpleApp.getInstance().cacheWork.getJson(nameJson);
            Log.d("SMPL","URL="+url+" JSON="+json);
            listener.onResponse(jsonSimple.jsonToModel(Html.fromHtml(json).toString().replaceAll("u0027", "'")));
            if (json == null) {
                getVolley();
            }
        } else {
            getVolley();
        }
    }

    private void getVolley() {
        if (headers == null) {
            headers = new HashMap<>();
        }
        String st = PreferenceTool.getUserKey();
        if (st.length() > 0) {
            headers.put("authorization", "key=" + st);
        }
        SimpleRequest request = new SimpleRequest(method, url, this, params, headers, object);
        iBase.addRequest(request);
        if (progress) {
            iBase.progressStart();
        }
        VolleyProvider.getInstance().addToRequestQueue(request);
    }

//    public void obtain(VolleyRequest.VolleyListener listener) {
//        if (dataResponse != null) {
//            Calendar c = new GregorianCalendar();
//            currentTime = c.getTime().getTime();
//            if (dataResponse.response == null) {
//                obtainVolley(listener);
//            } else {
//                if (dataResponse.time < currentTime) {
//                    obtainVolley(listener);
//                } else {
//                    listener.onResponse(dataResponse.response);
//                }
//            }
//        } else {
//            obtainVolley(listener);
//        }
//    }

//    public void obtainVolley(VolleyRequest.VolleyListener listener) {
//        this.listener = listener;
//        if (headers == null) {
//            headers = new HashMap<>();
//        }
//        BaseActivity context = iBase.getBaseActivity();
//        if (context == null) return;
//        String st = PreferenceTool.getUserKey();
//        if (st.length() > 0) {
//            headers.put("authorization", "key=" + st);
//        }
//        calendar = new GregorianCalendar();
//        beginTime = calendar.getTimeInMillis();
//
//        VolleyRequest request = new VolleyRequest(metod, url, Field.class, this, params, headers, object);
//        iBase.addRequest(request);
//        if (progress) {
//            iBase.getBaseActivity().progressStart();
//        }
//        VolleyProvider.getInstance().addToRequestQueue(request);
//    }

    @Override
    public void onErrorResponse(VolleyError error) {
//        Log.d("ASDFG","BasePresenter onErrorResponse error="+error);
        if (progress) {
            iBase.progressStop();
        }
        dialogError(error);
    }

    @Override
    public void onResponse(T response) {
        if (progress) {
            iBase.progressStop();
        }
        if (response == null) {
            iBase.showDialog("", "no response", null);
        }
        if (simpleRequest) {
            String st = (String) response;
            if (paramModel.duration > 0) {
                SimpleApp.getInstance().cacheWork.addCasche(url,
                        paramModel.duration, st);
            }
            listener.onResponse(jsonSimple.jsonToModel(Html.fromHtml(st).toString().replaceAll("u0027", "'")));
        } else {
            endTime = new GregorianCalendar().getTimeInMillis();
            if (dataResponse != null) {
                dataResponse.response = response;
                if (dataResponse.delta == Long.MAX_VALUE) {
                    dataResponse.time = dataResponse.delta;
                } else {
                    dataResponse.time = currentTime + dataResponse.delta;
                }
            }
            listener.onResponse(response);
        }
    }

    public void dialogError(VolleyError error) {
        endTime = new GregorianCalendar().getTimeInMillis();
        this.error = error;
        Log.d("SMPL","dialogError ERROR="+error);
        Field errorMessage = null;
        int statusCode = 0;
        String message = null;
        if (error.networkResponse != null) {
            statusCode = error.networkResponse.statusCode;
            if (error.networkResponse.data != null) {
                try {
                    String st  = new String(error.networkResponse.data,
                            HttpHeaderParser.parseCharset(error.networkResponse.headers));
                    if (st.startsWith("{")) {
                        errorMessage = jsonSimple.jsonToModel(st);
                    }
                } catch (UnsupportedEncodingException e) {
                    message = e.toString();
                    e.printStackTrace();
                }
            }
        }
        if (message == null) {
            message = error.getMessage();
        }

        String st = error.toString();
        if (st != null) {
            st = st.toUpperCase();
        }
        if (st != null) {
            if (st.contains("TIMEOUT")) {
                message = st;
            } else {
                if (st.contains("NOCONNECTIONERROR")) {
                    message = st;
                } else {
                    if (st.contains("SERVERERROR")) {
                        message = st;
                    } else {
                        if (st.contains("AUTHFAILURE")) {
                            message = st;
                        }
                    }
                }
            }
        }

        if (errorMessage == null) {
            List<Field> lf = new ArrayList<>();
            lf.add(new Field("title", Field.TYPE_STRING, "error"));
            lf.add(new Field("message", Field.TYPE_STRING, message));
            errorMessage = new Field("", Field.TYPE_LIST, lf);
        }
        List<Field> list = (List<Field>) errorMessage.value;
        iBase.showDialog(getValueString(list, "title"), getValueString(list, "message"), clickError);
    }

    private String getValueString(List<Field> list, String name) {
        String st = "";
        for (Field f : list) {
            if (f.name.equals(name)) {
                return (String) f.value;
            }
        }
        return st;
    }

    View.OnClickListener clickError = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onErrorResponse(error);
            }
        }
    };
}
