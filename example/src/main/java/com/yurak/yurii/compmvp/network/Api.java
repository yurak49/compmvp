package com.yurak.yurii.compmvp.network;

import com.yurak.yurii.compon.models.ParamModel;

public class Api {

    public static String BASE_URL = "https://aurafit.com.ua/android";
    public static String BASE_API = "https://aurafit.com.ua/android/api";
    public static String BASE_URL_IMG = "https://aurafit.com.ua";

    public static String GROUP_SCHEDULE = BASE_URL + "/api/schedule/week/1";
    public static String SERVICE_CATEGORY = BASE_URL + "/serviceGroups/5";
    public static String List_CATEGORY = BASE_URL + "/servicesInGroup/5";
    public static String List_CATEGORY_CLUB = BASE_URL + "/serviceGroups";
    public static String MY_NEW_SCHEDULE = BASE_URL + "/api/soap/getClientSchedule/2017-03-03/2017-11-23";
    public static String MY_GOALS = BASE_URL + "/api/goal/collection";
    public static String CLUB_LIST = BASE_URL + "/clubs";
    public static String CARD = BASE_URL + "/api/soap/getAbonnements";
    public static ParamModel CLUBS = new ParamModel("/android/clubs").changeNameField("id", "clubId");
    public static ParamModel CATEGORY = new ParamModel("/android/serviceGroups", "clubId");
    public static ParamModel MONEY = new ParamModel(ParamModel.POST, "profile", "", 60000*60*24*365);
    public static ParamModel CLOUDS = new ParamModel(ParamModel.POST, "clouds", "", 60000*60*24*365);
    public static ParamModel CONNECTION = new ParamModel(ParamModel.POST, "connections", "", 60000*60*24*365);
    public static ParamModel FRIEND_CAST = new ParamModel(ParamModel.POST, "friendcast/byprofile", "", 60000*60*24*365);
    public static ParamModel LOGIN = new ParamModel(ParamModel.POST, "https://apidev.moneyclouds.com/auth/token",
            "DeviceId,DeviceName,DeviceVersion,Nonce,Email,Password");
}
