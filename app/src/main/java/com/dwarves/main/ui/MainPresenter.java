package com.dwarves.main.ui;

import android.content.Context;
import com.dwarves.main.utils.CommonUtils;

import java.util.ArrayList;

public class MainPresenter implements MainMvpPresenter {
    private MainMvpView mvpView;
    private Context mContext;

    MainPresenter(MainMvpView mvpView, Context context) {
        this.mvpView = mvpView;
        this.mContext = context;
    }

    @Override
    public void getSubMessageList(String message) {
        ArrayList<String> subMessageList = CommonUtils.splitMessage(message);
        mvpView.showSubMessageView(subMessageList);
    }
}
