package org.openpkw.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by mrozi on 3/19/2016.
 */

@Entity
@Table(name = "COMMUNITY")
public class Community {

    @Id
    @Column(name = "COMMUNITY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name="NAME")
    private String name;


    @JoinColumn(name = "PROVINCE_ID", referencedColumnName = "PROVINCE_ID")
    @ManyToOne(optional = false)
    private Province province;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "community")
    private Collection<County> countyCollection;

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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Collection<County> getCountyCollection() {
        return countyCollection;
    }

    public void setCountyCollection(Collection<County> countyCollection) {
        this.countyCollection = countyCollection;
    }

    public Community()
    {

    }
    public Community (String code , String name,Province province)
    {
        this.code= code;
        this.name = name;
        this.province = province;
    }
}
