package org.openpkw.controllers.dto;

import java.util.List;

/**
 * Created by mrozi on 3/19/2016.
 */
public class TerritorialResult {

    private boolean isResult;
    private String error;
    private List<TerritorialResultRow> rows;

    public TerritorialResult(boolean isResult,String error , List<TerritorialResultRow> rows)
    {
        this.isResult=isResult;
        this.error=error;
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

    public List<TerritorialResultRow> getRows() {
        return rows;
    }

    public void setRows(List<TerritorialResultRow> rows) {
        this.rows = rows;
    }
}
