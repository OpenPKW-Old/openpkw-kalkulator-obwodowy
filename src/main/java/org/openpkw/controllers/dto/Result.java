package org.openpkw.controllers.dto;

import java.util.List;

public class Result<T> {

    private boolean isResult;
    private String error;
    private List<T> rows;

    public Result(boolean isResult, String error, List<T> rows) {
        this.isResult = isResult;
        this.error = error;
        this.rows = rows;
    }

    public boolean isResult() {
        return isResult;
    }

    public void setResult(boolean result) {
        isResult = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
