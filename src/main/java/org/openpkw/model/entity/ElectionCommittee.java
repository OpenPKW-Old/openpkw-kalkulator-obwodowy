package org.openpkw.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ELECTION_COMMITTEE")
public class ElectionCommittee {

    @Id
    @Column(name = "ELECTION_COMMITTEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electionCommitteeId")
    private Collection<ElectionCommitteeDistrict> electionCommitteeDistrictCollection = new ArrayList<>();

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

    public Collection<ElectionCommitteeDistrict> getElectionCommitteeDistrictCollection() {
        return electionCommitteeDistrictCollection;
    }

    public void setElectionCommitteeDistrictCollection(
            Collection<ElectionCommitteeDistrict> electionCommitteeDistrictCollection) {
        this.electionCommitteeDistrictCollection = electionCommitteeDistrictCollection;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "[ id=" + id + " ]";
    }
}