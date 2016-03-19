package org.openpkw.controllers.dto;

public class CommiteeResultRow {

    private String territorialCode;
    private Integer peripheralNumber;
    private String peripheralAddress;
    private Integer districtNumber;
    private String districtCity;

    public CommiteeResultRow(String territorialCode, Integer peripheralNumber, String peripheralAddress, Integer districtNumber, String districtCity) {
        this.territorialCode = territorialCode;
        this.peripheralNumber = peripheralNumber;
        this.peripheralAddress = peripheralAddress;
        this.districtNumber = districtNumber;
        this.districtCity = districtCity;
    }

    public String getTerritorialCode() {
        return territorialCode;
    }

    public void setTerritorialCode(String territorialCode) {
        this.territorialCode = territorialCode;
    }

    public Integer getPeripheralNumber() {
        return peripheralNumber;
    }

    public void setPeripheralNumber(Integer peripheralNumber) {
        this.peripheralNumber = peripheralNumber;
    }

    public String getPeripheralAddress() {
        return peripheralAddress;
    }

    public void setPeripheralAddress(String peripheralAddress) {
        this.peripheralAddress = peripheralAddress;
    }

    public Integer getDistrictNumber() {
        return districtNumber;
    }

    public void setDistrictNumber(Integer districtNumber) {
        this.districtNumber = districtNumber;
    }

    public String getDistrictCity() {
        return districtCity;
    }

    public void setDistrictCity(String districtCity) {
        this.districtCity = districtCity;
    }

}
