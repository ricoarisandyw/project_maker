package com.mrabid.pro_maker.API;

/**
 * Created by Reaper on 10/14/2017.
 */

public class ResponseAPI {
    private boolean result;
    private String message;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
