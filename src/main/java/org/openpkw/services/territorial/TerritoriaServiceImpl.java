package org.openpkw.services.territorial;

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

            boolean isGmi = woj!=null && pow !=null && gmi!=null;
            boolean isPow = woj!=null && pow!=null && !isGmi;
            boolean isWoj = woj!=null && !isPow;
            if (isGmi)
            {

            }
            if (isPow)
            {

            }

            if (isWoj)
            {
                if (!provinceList.stream().filter(a->a.getCode().equals("")).findFirst().isPresent())
                {
                    provinceList.add(new Province(woj,nazwa));
                }
            }

        }

    }
}
