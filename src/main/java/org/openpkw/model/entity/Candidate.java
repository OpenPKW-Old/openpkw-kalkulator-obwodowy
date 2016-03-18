package org.openpkw.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CANDIDATE")
public class Candidate implements Serializable {

    @Id
    @Column(name = "CANDIDATE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "POSITION_ON_LIST")
    private Integer positionOnList;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @JoinColumn( name = "ELECTION_COMMITTEE_DISTRICT_ID", referencedColumnName = "ELECTION_COMMITTEE_DISTRICT_ID")
    @ManyToOne(optional = false)
    private ElectionCommitteeDistrict electionCommitteeDistrict;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getPositionOnList() {
        return positionOnList;
    }

    public void setPositionOnList(Integer positionOnList) {
        this.positionOnList = positionOnList;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ElectionCommitteeDistrict getElectionCommitteeDistrict() {
        return electionCommitteeDistrict;
    }

    public void setElectionCommitteeDistrict(ElectionCommitteeDistrict electionCommitteeDistrict) {
        this.electionCommitteeDistrict = electionCommitteeDistrict;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "[ id=" + id + " ]";
    }

}
