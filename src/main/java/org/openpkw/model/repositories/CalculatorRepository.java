package org.openpkw.model.repositories;

import org.openpkw.controllers.dto.CommiteeResultRow;
import org.openpkw.model.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculatorRepository extends JpaRepository<Community, Long> {

    @Query("SELECT NEW org.openpkw.controllers.dto.CommiteeResultRow(pc.territorialCode, pc.number, pc.address, dc.number, dc.city) "
            + "FROM "
            + "PeripheralCommittee as pc, "
            + "DistrictCommittee as dc "
            + "WHERE "
            + "pc.districtCommittee = dc and "
            + "pc.territorialCode = ?1 || ?2 || ?3 "
    )
    List<CommiteeResultRow> getCommitee(String provinceCode, String communityCode, String countyCode);

}
