package com.dwarves.main.ui;

import java.util.ArrayList;

public interface MainMvpView {
    void showSubMessageView(ArrayList<String> keywordsList);
    String getInputMessage();
    void showError(int errorCode);
}
