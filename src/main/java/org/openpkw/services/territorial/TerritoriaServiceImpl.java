package org.openpkw.services.territorial;

import org.openpkw.model.entity.Community;
import org.openpkw.model.entity.County;
import org.openpkw.model.entity.Province;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        provinceRepository.deleteAll();
        ArrayList<Province> provinceList = new ArrayList<Province>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse( this.getClass().getResourceAsStream(territorialFile));
        NodeList nodeListRoot = (((Element)doc.getElementsByTagName("catalog").item(0)).getElementsByTagName("row"));
        for (int i=0 ; i<nodeListRoot.getLength();i++)
        {
            String woj = null;
            String gmi= null;
            String pow = null;
            String nazwa = null;
            Element elementRoot = (Element) nodeListRoot.item(i);
            NodeList nodeList= elementRoot.getElementsByTagName("col");
            for (int j=0;j<nodeList.getLength();j++)
            {
                Element element = (Element)nodeList.item(j);
                if (element.getAttribute("name").equals("WOJ"))
                    woj = element.getTextContent();
                if (element.getAttribute("name").equals("GMI"))
                    gmi = element.getTextContent();

                if (element.getAttribute("name").equals("POW"))
                    pow = element.getTextContent();

                if (element.getAttribute("name").equals("NAZWA"))
                    nazwa = element.getTextContent();

            }

            boolean isGmi = (woj!=null && !woj.equals("")) && (pow !=null && !pow.equals("")) &&( gmi!=null && !gmi.equals(""));
            boolean isPow = (woj!=null && !woj.equals("")) && (pow !=null && !pow.equals("")) && !isGmi;
            boolean isWoj = (woj!=null && !woj.equals("")) && !isPow;
            if (isGmi)
            {
                String wojIn = woj;
                String powIn = pow;
                String gmiIn = gmi;
                Optional<Province> provinceFind = provinceList.stream().filter(a->a.getCode().equals(wojIn)).findFirst();
                if (provinceFind.isPresent())
                {
                    Optional<Community> communityFind = provinceFind.get().getCommunityCollection().stream().filter(a->a.getCode().equals(powIn)).findFirst();
                    if (communityFind.isPresent())
                    {
                        if (communityFind.get().getCountyCollection()==null || !communityFind.get().getCountyCollection().stream().filter(a->a.getCode().equals(powIn)).findFirst().isPresent())
                        {
                            if (communityFind.get().getCountyCollection() ==null)
                                communityFind.get().setCountyCollection(new ArrayList<>());

                            communityFind.get().getCountyCollection().add(new County(gmiIn,nazwa));
                        }
                    }
                }
            }
            if (isPow)
            {
                String wojIn = woj;
                String powIn = pow;
                Optional<Province> provinceFind = provinceList.stream().filter(a->a.getCode().equals(wojIn)).findFirst();
                if (provinceFind.isPresent())
                {
                    if (provinceFind.get().getCommunityCollection()==null || !provinceFind.get().getCommunityCollection().stream().filter(a->a.getCode().equals(powIn)).findFirst().isPresent())
                    {
                        if (provinceFind.get().getCommunityCollection()==null)
                            provinceFind.get().setCommunityCollection(new ArrayList<>());

                        provinceFind.get().getCommunityCollection().add(new Community(powIn,nazwa));
                    }
                }
            }

            if (isWoj)
            {
                String wojIn = woj;
                if (!provinceList.stream().filter(a->a.getCode().equals(wojIn)).findFirst().isPresent())
                {
                    provinceList.add(new Province(wojIn,nazwa));
                }
            }

        }

        for (Province province: provinceList)
            provinceRepository.save(province);

    }
}
