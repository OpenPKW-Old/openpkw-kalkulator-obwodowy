package org.openpkw.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "DISTRICT_COMMITTEE")
public class DistrictCommittee {

    @Id
    @Column(name = "DISTRICT_COMMITTEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NUNBER")
    private int number;

    @Basic(optional = false)
    @NotNull
    @Column(name = "NAME",columnDefinition = "TEXT")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtCommittee")
    private Collection<ElectionCommitteeDistrict> districtCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtCommittee")
    private Collection<PeripheralCommittee> peripheralCommitteeCollection;

    @Column(name = "CITY", columnDefinition = "TEXT")
    private String city;

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

    public Collection<ElectionCommitteeDistrict> getDistrictCollection() {
        return districtCollection;
    }

    public void setDistrictCollection(Collection<ElectionCommitteeDistrict> districtCollection) {
        this.districtCollection = districtCollection;
    }

    public Collection<PeripheralCommittee> getPeripheralCommitteeCollection() {
        return peripheralCommitteeCollection;
    }

    public void setPeripheralCommitteeCollection(Collection<PeripheralCommittee> peripheralCommitteeCollection) {
        this.peripheralCommitteeCollection = peripheralCommitteeCollection;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + " [id=" + id + ", number="
                + number + ", name=" + name + ", districtCollection="
                + districtCollection + ", peripheralCommitteeCollection="
                + peripheralCommitteeCollection + ", address=" + address + "]";
    }
}