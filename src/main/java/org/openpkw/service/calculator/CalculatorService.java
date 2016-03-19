package org.openpkw.service.calculator;

import org.openpkw.controllers.dto.CommiteeResultRow;
import org.openpkw.controllers.dto.TerritorialResultRow;
import org.openpkw.model.entity.Community;
import org.openpkw.model.entity.County;
import org.openpkw.model.entity.Province;
import org.openpkw.model.repositories.CommunityRepository;
import org.openpkw.model.repositories.CountyRepository;
import org.openpkw.model.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrozi on 3/19/2016.
 */
public interface CalculatorService  {

    List<TerritorialResultRow> getProvinces();
    List<TerritorialResultRow> getComuties(String provinceCode);
    List<TerritorialResultRow> getCounties(String provinceCode, String communityCode);
    List<CommiteeResultRow> getCommitee(String provinceCode, String communityCode, String countyCode);

}
