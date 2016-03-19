package org.openpkw.controllers;

import org.openpkw.controllers.dto.InitResult;
import org.openpkw.service.structure.StructureService;
import org.openpkw.services.territorial.TerritorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by mrozi on 3/19/2016.
 */

@RestController
public class InitController {

    @Autowired
    private TerritorialService territorialService;

    @Autowired
    private StructureService structureService;

    @RequestMapping(value = "/init", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InitResult> init()
    {
        ResponseEntity<InitResult> result;
        try {
            territorialService.initTerritorialService();
            structureService.initStructureService();
            result = new ResponseEntity<>(new InitResult(false,null), HttpStatus.OK);
        } catch(Exception ex)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            result = new ResponseEntity<>(new InitResult(false,sw.toString()), HttpStatus.NOT_FOUND);

        }
        return result;
    }
}
