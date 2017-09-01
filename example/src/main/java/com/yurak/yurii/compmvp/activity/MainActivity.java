package com.yurak.yurii.compmvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yurak.yurii.compmvp.R;
import com.yurak.yurii.compmvp.network.Api;
import com.yurak.yurii.compon.base.ActivityDrawer;
import com.yurak.yurii.compon.interfaces_classes.Navigator;
import com.yurak.yurii.compon.interfaces_classes.ViewHandler;
import com.yurak.yurii.compon.models.ParamComponent;
import com.yurak.yurii.compon.models.ParamModel;
import com.yurak.yurii.compon.models.ParamView;

public class MainActivity extends ActivityDrawer {
    @Override
    protected void initMenu() {
        addMenuItem("icon_lil_phone", "icon_phone", "Мои цели", getString(R.string.goals));
        addMenuItem("icon_lil_phone", "icon_phone", "Услуги", getString(R.string.category), true);
        addMenuItem("icon_mail", "icon_phone", "Мои занятия", getString(R.string.schedule));
        addMenuItem("icon_lil_phone", "icon_phone", "Абонемент", getString(R.string.abonem));
        addMenuItem("icon_lil_phone", "icon_phone", "Parent", getString(R.string.parent));
        addMenuItem("icon_lil_phone", "icon_phone", "Групповые", getString(R.string.group));
        addDivider();
        addMenuItem("icon_lil_phone", "icon_phone", "Money clouds", getString(R.string.money));
        addMenuItem("icon_lil_phone", "icon_phone", "My cloud", getString(R.string.my_cloud));
        addMenuItem("icon_lil_phone", "icon_phone", "PAGER", getString(R.string.pager_f));
        addMenuItem("icon_lil_phone", "icon_phone", "LOGIN", getString(R.string.login));
    }

    @Override
    protected String getDrawerFragment() {
        return "drawer";
    }

    @Override
    public void initView() {
        super.initView();
        addParamValue("DeviceId", "21391e70-0ef6-4a7d-b3c4-33ec33a3f14c");
        addParamValue("DeviceName", "ELEPHONE Elephone G6");
        addParamValue("DeviceVersion", "1-6-3");
        addParamValue("Nonce", "1493295165641");
    }

    @Override
    protected void initFragments() {

        addFragment("drawer", R.layout.fragment_navigator)
                .addComponent(ParamComponent.TC.PANEL_MULTI, new ParamModel(getProfile()),
                        new ParamView(R.id.panel, R.layout.nav_header_main, R.layout.nav_header_not),
                        new Navigator().add(R.id.enter, getString(R.string.login))
                                .add(R.id.enter, ViewHandler.TYPE.CLOSE_DRAWER))
                .addComponent(ParamComponent.TC.MENU, new ParamModel(menu),
                        new ParamView(R.id.recycler, "select",
                                new int[]{R.layout.item_menu_devider, R.layout.item_menu, R.layout.item_menu_select}),
                        new Navigator().add("nameFunc"));

        addFragment(getString(R.string.goals), R.layout.base_recycler, "Мои цели")
                .addComponent(ParamComponent.TC.RECYCLER, new ParamModel(Api.MY_GOALS));

        addFragment(getString(R.string.group), R.layout.base_recycler, "Групповые занятия")
                .addComponent(ParamComponent.TC.RECYCLER, new ParamModel(Api.GROUP_SCHEDULE),
                        new ParamView(R.id.recycler, "isSelected", new int[]{R.layout.group_lessons_item,
                                R.layout.group_lessons_sel_item, R.layout.group_lessons_sel_item}));


        addFragment(getString(R.string.schedule), R.layout.base_recycler, "Мои занятия")
                .addComponent(ParamComponent.TC.RECYCLER, new ParamModel(Api.MY_NEW_SCHEDULE),
                        new ParamView(R.id.recycler, R.layout.item_schedule));
        addFragment(getString(R.string.abonem), R.layout.abonement, "Мои абонементы")
                .addComponent(ParamComponent.TC.PANEL, new ParamModel(Api.CARD),
                        new ParamView(R.id.panel));
        addFragment(getString(R.string.parent), R.layout.abon_parent, "Мои абонементы parent")
                .addModel(new ParamModel(Api.CARD))
                .addComponent(ParamComponent.TC.PAGER_V, new ParamModel(ParamModel.PARENT, null, "cards"),
                        new ParamView(R.id.cards, R.layout.item_my_card_pager).setIndicator(R.id.indicator).setFurtherView(R.id.further))
                .addComponent(ParamComponent.TC.STATIC_LIST, new ParamModel(ParamModel.PARENT, null, "invoices"),
                        new ParamView(R.id.invoices, R.layout.item_my_card));
        addFragment(getString(R.string.category), R.layout.multi)
                .addComponent(ParamComponent.TC.SPINNER, Api.CLUBS,
                        new ParamView(R.id.spinner, R.layout.club_spin_drop, R.layout.club_spin_hider))
                .addComponent(ParamComponent.TC.RECYCLER, Api.CATEGORY,
                        new ParamView(R.id.recycler, R.layout.item_category),
                        new Navigator().add(0, "multi_category"), R.id.spinner);
        addFragment(getString(R.string.multi_category), R.layout.base_recycler, "%1$s", "title")
                .addComponent(ParamComponent.TC.RECYCLER, new ParamModel(Api.List_CATEGORY, "id"),
                        new ParamView(R.id.recycler, R.layout.item_services));
        addFragment(getString(R.string.money), R.layout.fragment_money, "Money clouds")
                .addComponent(ParamComponent.TC.PANEL, Api.MONEY, new ParamView(R.id.profile))
                .addComponent(ParamComponent.TC.RECYCLER_HORIZONTAL, Api.CLOUDS,
                        new ParamView(R.id.clouds, "Color", new int[]{R.layout.item_clouds_1,
                                R.layout.item_clouds_1, R.layout.item_clouds_2, R.layout.item_clouds_3,
                                R.layout.item_clouds_4, R.layout.item_clouds_1, R.layout.item_clouds_3,
                                R.layout.item_clouds_4}), null, 0, CloudWork.class)
                .addComponent(ParamComponent.TC.RECYCLER_HORIZONTAL, Api.CONNECTION,
                        new ParamView(R.id.connection, R.layout.item_connection_profile))
                .addComponent(ParamComponent.TC.STATIC_LIST, Api.FRIEND_CAST,
                        new ParamView(R.id.friendcast, R.layout.item_friendcast_profile));
        addFragment(getString(R.string.my_cloud), R.layout.fragment_my_cloud)
                .addComponent(ParamComponent.TC.RECYCLER_GRID, Api.CLOUDS,
                        new ParamView(R.id.recycler, R.layout.item_my_cloud));
        addFragment(getString(R.string.pager_f), R.layout.fragment_pager_f)
                .addComponent(ParamComponent.TC.PAGER_F, new ParamView(R.id.pager,
                        new String[] {getString(R.string.cloud_p), getString(R.string.club_p)}).setTab(R.id.tabs, R.array.tab_cloud_club));
        addFragment(getString(R.string.login), R.layout.fragment_login_cloud)
                .addComponent(ParamComponent.TC.PANEL_ENTER, new ParamModel(getProfile()).takeField("Data"), new ParamView(R.id.panel),
                        new Navigator().add(R.id.btn_sign_in, ViewHandler.TYPE.SEND_CHANGE_BACK, Api.LOGIN));

// fragments for PagerF

        addFragment(getString(R.string.cloud_p), R.layout.fragment_cloud_p)
                .addComponent(ParamComponent.TC.RECYCLER_GRID, Api.CLOUDS,
                        new ParamView(R.id.recycler, R.layout.item_my_cloud));
        addFragment(getString(R.string.club_p), R.layout.fragment_club_p)
                .addComponent(ParamComponent.TC.RECYCLER, Api.CLUBS,
                        new ParamView(R.id.recycler, R.layout.item_club));
    }

    @Override
    public int getProgressLayout() {
        return R.layout.dialog_progress;
    }

    @Override
    public int getDialogLayout() {
        return R.layout.base_dialog;
    }

    @Override
    public String getBaseUrl() {
        return "https://aurafit.com.ua";
    }
}
