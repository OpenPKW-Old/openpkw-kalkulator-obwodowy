package org.openpkw.model.repositories;

import org.openpkw.model.entity.DistrictCommittee;
import org.openpkw.model.entity.ElectionCommittee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Remigiusz.Mrozek on 2016-03-19.
 */
public interface ElectionCommitteeRepository extends JpaRepository<ElectionCommittee, Long> {
}
