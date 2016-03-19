package org.openpkw.model.repositories;

import org.openpkw.model.entity.Community;
import org.openpkw.model.entity.ElectionCommitteeDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Remigiusz.Mrozek on 2016-03-19.
 */
public interface ElectionCommitteeDistrictRepository extends JpaRepository<ElectionCommitteeDistrict, Long> {
}
