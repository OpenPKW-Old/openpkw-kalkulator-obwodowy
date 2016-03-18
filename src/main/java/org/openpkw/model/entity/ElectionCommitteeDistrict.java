package org.openpkw.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ELECTION_COMMITTEE_DISTRICT")
public class ElectionCommitteeDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ELECTION_COMMITTEE_DISTRICT_ID")
    private Long id;

    @Column(name = "LIST_NUMBER")
    private Integer listNumber;

    @JoinColumn(name = "ELECTION_COMMITTEE_ID", referencedColumnName = "ELECTION_COMMITTEE_ID")
    @ManyToOne(optional = false)
    private ElectionCommittee electionCommitteeId;

    @JoinColumn(name = "DISTRICT_COMMITTEE_ID", referencedColumnName = "DISTRICT_COMMITTEE_ID")
    @ManyToOne(optional = false)
    private DistrictCommittee districtCommittee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electionCommitteeDistrict")
    private Collection<Candidate> candidateCollection = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getListNumber() {
        return listNumber;
    }

    public void setListNumber(Integer listNumber) {
        this.listNumber = listNumber;
    }

    public ElectionCommittee getElectionCommitteeId() {
        return electionCommitteeId;
    }

    public void setElectionCommitteeId(ElectionCommittee electionCommitteeId) {
        this.electionCommitteeId = electionCommitteeId;
    }

    public DistrictCommittee getDistrictCommittee() {
        return districtCommittee;
    }

    public void setDistrictCommittee(DistrictCommittee districtCommittee) {
        this.districtCommittee = districtCommittee;
    }

    public Collection<Candidate> getCandidateCollection() {
        return candidateCollection;
    }

    public void setCandidateCollection(Collection<Candidate> candidateCollection) {
        this.candidateCollection = candidateCollection;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "[ id=" + id + " ]";
    }
}
