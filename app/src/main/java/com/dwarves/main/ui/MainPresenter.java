package com.dwarves.main.ui;

import com.dwarves.main.utils.CommonUtils;

public class MainPresenter implements MainMvpPresenter {
    private MainMvpView mvpView;

    public MainPresenter(MainMvpView mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void getAndShowSubMessageList(String message) {
        SplitMessageResult subMessageList = CommonUtils.splitMessage(message);
        mvpView.showSubMessageView(subMessageList);
        if (!subMessageList.isSuccess()) {
            mvpView.showError(subMessageList.getErrorCode());
        }
    }


}
