package org.openpkw.service.structure;

/**
 * Created by Remigiusz.Mrozek on 2016-03-19.
 */
public enum PeripheralCsvLine {
    DistrictNumber(0),
    Name(4),
    Number(3),
    TeritorialCode(1),
    AddressName(4);

    private int lineNumber;

    private PeripheralCsvLine(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}