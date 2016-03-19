package org.openpkw.controllers;

import org.openpkw.controllers.dto.InitResult;
import org.openpkw.controllers.dto.TerritorialResult;
import org.openpkw.controllers.dto.TerritorialResultRow;
import org.openpkw.model.entity.Province;
import org.openpkw.service.calculator.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by mrozi on 3/19/2016.
 */
@RestController
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @RequestMapping(value = "/province", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerritorialResult> getProvinces()
    {
        ResponseEntity<TerritorialResult> result;
        try {
           List<TerritorialResultRow> provinceList= calculatorService.getProvinces();
            result = new ResponseEntity<>(new TerritorialResult(true,null,provinceList), HttpStatus.OK);
        } catch(Exception ex)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result = new ResponseEntity<>(new TerritorialResult(false,sw.toString(),null), HttpStatus.NOT_FOUND);

        }
        return result;
    }

    @RequestMapping(value = "/communities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerritorialResult> getCommunities(@RequestParam("provinceCode") String provinceCode)
    {
        ResponseEntity<TerritorialResult> result;
        try {
            List<TerritorialResultRow> provinceList= calculatorService.getComuties(provinceCode);
            result = new ResponseEntity<>(new TerritorialResult(true,null,provinceList), HttpStatus.OK);
        } catch(Exception ex)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result = new ResponseEntity<>(new TerritorialResult(false,sw.toString(),null), HttpStatus.NOT_FOUND);

        }
        return result;
    }


    @RequestMapping(value = "/counties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerritorialResult> getCounties(@RequestParam("provinceCode") String provinceCode, @RequestParam("communityCode") String communityCode)
    {
        ResponseEntity<TerritorialResult> result;
        try {
            List<TerritorialResultRow> provinceList= calculatorService.getCounties(provinceCode,communityCode);
            result = new ResponseEntity<>(new TerritorialResult(true,null,provinceList), HttpStatus.OK);
        } catch(Exception ex)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result = new ResponseEntity<>(new TerritorialResult(false,sw.toString(),null), HttpStatus.NOT_FOUND);

        }
        return result;
    }

}
