package org.openpkw.model.repositories;

import org.openpkw.model.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mrozi on 3/19/2016.
 */
@Repository
public interface CountyRepository extends JpaRepository<County, Long> {
}
