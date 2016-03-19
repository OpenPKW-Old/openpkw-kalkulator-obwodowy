package org.openpkw.service.structure;

/**
 * Created by Remigiusz.Mrozek on 2016-03-19.
 */
public enum DistrictCsvLine {
    City(2),
    Number(1),
    Name(3);

    private int lineNumber;

    private DistrictCsvLine(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}