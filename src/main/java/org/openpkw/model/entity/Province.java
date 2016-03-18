package org.openpkw.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by mrozi on 3/19/2016.
 */

@Entity
@Table(name = "PROVINCE")
public class Province {

    @Id
    @Column(name = "PROVINCE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name="NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "province")
    private Collection<Community> communityCollection;

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

    public Collection<Community> getCommunityCollection() {
        return communityCollection;
    }

    public void setCommunityCollection(Collection<Community> communityCollection) {
        this.communityCollection = communityCollection;
    }
}
