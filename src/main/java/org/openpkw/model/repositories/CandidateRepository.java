package org.openpkw.model.repositories;

import org.openpkw.model.entity.Candidate;
import org.openpkw.model.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Remigiusz.Mrozek on 2016-03-19.
 */
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
