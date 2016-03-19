package org.openpkw.service.structure;

/**
 * Created by Remigiusz.Mrozek on 2016-03-19.
 */
public enum CandidateCsvLine {
    DistrictNumber(0),
    ElectionCommitteeListNumber(1),
    ElectionCommiteeListName(2),
    Number(3),
    Surname(4),
    Name(5);
    private int lineNumber;
    CandidateCsvLine(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
