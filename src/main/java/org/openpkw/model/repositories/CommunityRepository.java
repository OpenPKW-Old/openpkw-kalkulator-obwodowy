package org.openpkw.model.repositories;

import org.openpkw.model.entity.Community;
import org.openpkw.model.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mrozi on 3/19/2016.
 */
@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    @Query("SELECT co "
            + "FROM "
            + "Community co , "
            + "Province pr "
            + "where "
            + "co.province=pr"
            +" and pr.code = ?1"
           )
    List<Community> getCommutieByProvinceCode(String provinceCode);

}
