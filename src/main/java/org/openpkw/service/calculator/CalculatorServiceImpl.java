package org.openpkw.service.calculator;

import org.openpkw.controllers.dto.TerritorialResultRow;
import org.openpkw.model.entity.Community;
import org.openpkw.model.entity.County;
import org.openpkw.model.entity.Province;
import org.openpkw.model.repositories.CommunityRepository;
import org.openpkw.model.repositories.CountyRepository;
import org.openpkw.model.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrozi on 3/19/2016.
 */
@Service
public class CalculatorServiceImpl  implements  CalculatorService {

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    ProvinceRepository provinceRepository;


    public List<TerritorialResultRow> getProvinces()
    {
        List<TerritorialResultRow> territorialResults = new ArrayList<>();
        List<Province> provinceList = provinceRepository.findAll();
        for (Province province:provinceList)
        {
            territorialResults.add(new TerritorialResultRow(province.getId(),province.getCode(),province.getName()));
        }
        return territorialResults;
    }

    public List<TerritorialResultRow> getComuties(String provinceCode)
    {
        List<TerritorialResultRow> territorialResults = new ArrayList<>();
        List<Community> communityList = communityRepository.getCommutieByProvinceCode(provinceCode);
        for (Community community:communityList)
        {
            territorialResults.add(new TerritorialResultRow(community.getId(),community.getCode(),community.getName()));
        }
        return territorialResults;
    }


    public List<TerritorialResultRow> getCounties(String provinceCode, String communityCode)
    {
        List<TerritorialResultRow> territorialResults = new ArrayList<>();
        List<County> countyyList = countyRepository.getCountyByProvinceCodeAndByCommunityCode(provinceCode,communityCode);
        for (County county:countyyList)
        {
            territorialResults.add(new TerritorialResultRow(county.getId(),county.getCode(),county.getName()));
        }
        return territorialResults;
    }
}
