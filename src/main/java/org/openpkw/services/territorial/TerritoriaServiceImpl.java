package org.openpkw.services.territorial;

import org.openpkw.model.repositories.CommunityRepository;
import org.openpkw.model.repositories.CountyRepository;
import org.openpkw.model.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
/**
 * Created by mrozi on 3/19/2016.
 */
@Service
public class TerritoriaServiceImpl implements TerritorialService{


    String territorialFile="/TERC.xml";
    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public void initTerritorialService() throws Exception {
         this.getClass().getResourceAsStream(territorialFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse( this.getClass().getResourceAsStream(territorialFile));
        //doc.getElementsByTagName("catalog").item(0)
    }
}
