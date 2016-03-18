package org.openpkw.model.entity;

import javax.persistence.*;

/**
 * Created by mrozi on 3/19/2016.
 */

@Entity
@Table(name = "COUNTY")
public class County {


    @Id
    @Column(name = "COUNTY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name="NAME")
    private String name;

    @JoinColumn(name = "COMMUNITY_ID", referencedColumnName = "COMMUNITY_ID")
    @ManyToOne(optional = false)
    private Community community;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
