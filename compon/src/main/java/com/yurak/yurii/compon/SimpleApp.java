package com.yurak.yurii.compon;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.yurak.yurii.compon.json_simple.Field;
import com.yurak.yurii.compon.json_simple.FieldBroadcaster;
import com.yurak.yurii.compon.json_simple.Record;
import com.yurak.yurii.compon.network.CacheWork;
import com.yurak.yurii.compon.tools.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleApp extends Application {
    private static SimpleApp instance;
    public FieldBroadcaster profile;
//    public BaseURLs baseURLs;
    public String baseURL;
    public int progressId;
    public int dialogId;
    public CacheWork cacheWork;
    public List<String> namesParams = new ArrayList<>();
    public List<String> valuesParams = new ArrayList<>();

    public static SimpleApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        profile = new FieldBroadcaster("profile", Field.TYPE_RECORD, null);
//        baseURLs = new BaseURLs();
        cacheWork = new CacheWork(this);

// Delete  *********************************
        String st = readFile("clouds.txt");
        cacheWork.addCasche("clouds", 60000*60*24*365, st);
        st = readFile("profile.txt");
        cacheWork.addCasche("profile", 60000*60*24*365, st);
        st = readFile("friendcast-byprofile.txt");
        cacheWork.addCasche("friendcast/byprofile", 60000*60*24*365, st);
        st = readFile("connections.txt");
        cacheWork.addCasche("connections", 60000*60*24*365, st);
// Delete  *********************************
    }


// Delete  *********************************
    private String readFile(String fileName) {
        File sdPath = Environment.getExternalStorageDirectory();
        File file = new File(sdPath.getAbsolutePath()+"/Download" + "/" + fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
// Delete  *********************************

    public void setParam(Record fields) {
        int ik = namesParams.size();
        for (Field f: fields) {
            String name = f.name;
            for (int i = 0; i < ik; i++) {
                String nameParam = namesParams.get(i);
                if (nameParam.equals(name)) {
                    switch (f.type) {
                        case Field.TYPE_STRING :
                            valuesParams.set(i, new String((String) f.value));
                            break;
                        case Field.TYPE_INTEGER :
                            valuesParams.set(i, String.valueOf((Integer) f.value));
                            break;
                        case Field.TYPE_FLOAT :
                            valuesParams.set(i, String.valueOf((Float) f.value));
                            break;
                        case Field.TYPE_DOUBLE :
                            valuesParams.set(i, String.valueOf((Double) f.value));
                            break;
                        case Field.TYPE_BOOLEAN :
                            valuesParams.set(i, String.valueOf((Boolean) f.value));
                            break;
                    }
                    break;
                }
            }
        }
    }

    public void addParam(String paramName) {
        for (String st : namesParams) {
            if (paramName.equals(st)) {
                return;
            }
        }
        namesParams.add(paramName);
        valuesParams.add("");
    }

    public void addParamValue(String paramName, String paramValue) {
        int ik = namesParams.size();
        for (int i = 0; i < ik; i++) {
            if (paramName.equals(namesParams.get(i))) {
                valuesParams.set(i, paramValue);
                return;
            }
        }
        namesParams.add(paramName);
        valuesParams.add(paramValue);
    }

    public String installParam(String param) {
        String st = "";
        if (param != null && param.length() > 0) {
            String[] paramArray = param.split(Constants.SEPARATOR_LIST);
            int ik = namesParams.size();
            for (String par : paramArray) {
                for (int i = 0; i < ik; i++) {
                    if (par.equals(namesParams.get(i))) {
                        st = st + "/" + valuesParams.get(i);
                        break;
                    }
                }
            }
        }
        return st;
    }

    public String getParamValue(String nameParam) {
        int ik = namesParams.size();
        for (int i = 0; i < ik; i++) {
            if (nameParam.equals(namesParams.get(i))) {
                return valuesParams.get(i);
            }
        }
        return "";
    }
}
