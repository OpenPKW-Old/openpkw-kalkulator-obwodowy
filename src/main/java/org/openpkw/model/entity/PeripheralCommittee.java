package org.openpkw.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PERIPHERAL_COMMITTEE")
public class PeripheralCommittee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERIPHERAL_COMMITTEE_ID")
    private Long id;

    @NotNull
    @Column(name = "NUMBER")
    private int number;

    @JoinColumn(name = "DISTRICT_COMMITTEE_ID", referencedColumnName = "DISTRICT_COMMITTEE_ID")
    @ManyToOne(optional = false)
    private DistrictCommittee districtCommittee;

    @Column(name = "NAME",columnDefinition = "TEXT")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "ALLOWED_TO_VOTE")
    private Long allowedToVote;

    @Column(name = "TERRITORIAL_CODE")
    private String territorialCode;

    @Column(name = "PERIPHERAL_CODE")
    private String peripheralCode;

    @Column(name = "ADDRESS", columnDefinition = "TEXT")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAllowedToVote() {
        return allowedToVote;
    }

    public void setAllowedToVote(Long allowedToVote) {
        this.allowedToVote = allowedToVote;
    }

    public String getTerritorialCode() {
        return territorialCode;
    }

    public void setTerritorialCode(String territorialCode) {
        this.territorialCode = territorialCode;
    }

    public String getPeripheralCode() {
        return peripheralCode;
    }

    public void setPeripheralCode(String peripheralCode) {
        this.peripheralCode = peripheralCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DistrictCommittee getDistrictCommittee() {
        return districtCommittee;
    }

    public void setDistrictCommittee(DistrictCommittee districtCommittee) {
        this.districtCommittee = districtCommittee;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "[ id=" + id + ", number="
                + number + ", districtCommittee=" + districtCommittee + ", name=" + name
                + ", type=" + type + ", allowedToVote=" + allowedToVote + ", territorialCode=" + territorialCode
                + ", peripheralCode=" + peripheralCode + ", address=" + address
                + "]";
    }
}
