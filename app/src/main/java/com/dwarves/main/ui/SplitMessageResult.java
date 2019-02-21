package com.dwarves.main.ui;

import java.util.ArrayList;

public class SplitMessageResult extends ArrayList<String> {
    private boolean isSuccess;
    private int errorCode;

    public SplitMessageResult() {
        super();
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSucess(boolean success) {
        this.isSuccess = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int error_code) {
        this.errorCode = error_code;
    }
}
