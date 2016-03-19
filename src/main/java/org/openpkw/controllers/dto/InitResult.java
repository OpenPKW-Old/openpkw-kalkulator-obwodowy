package org.openpkw.controllers.dto;

/**
 * Created by mrozi on 3/19/2016.
 */
public class InitResult {

    private boolean initResult;
    private String error;

    public InitResult(boolean initResult , String error)
    {
        this.initResult = initResult;
        this.error = error;
    }

    public boolean isInitResult() {
        return initResult;
    }

    public void setInitResult(boolean initResult) {
        this.initResult = initResult;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
