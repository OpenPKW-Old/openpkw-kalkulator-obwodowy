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
public interface CountyRepository extends JpaRepository<County, Long> {

    @Query("SELECT cn "
            + "FROM "
            + "County cn ,"
            + "Community co , "
            + "Province pr "
            + "where "
            + "cn.community = co "
            + "and co.province = pr"
            +" and pr.code = ?1 "
            +" and co.code = ?2"
    )
    List<County> getCountyByProvinceCodeAndByCommunityCode(String provinceCode,String communityCode);

}
