package org.openpkw.model.repositories;

import org.openpkw.model.entity.Community;
import org.openpkw.model.entity.DistrictCommittee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Remigiusz.Mrozek on 2016-03-19.
 */
public interface DistrictCommitteeRepository  extends JpaRepository<DistrictCommittee, Long> {
}
