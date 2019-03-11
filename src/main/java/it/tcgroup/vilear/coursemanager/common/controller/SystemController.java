package it.tcgroup.vilear.coursemanager.common.controller;

import it.tcgroup.vilear.coursemanager.common.controller.payload.PingResponse;
import it.tcgroup.vilear.coursemanager.common.controller.payload.VersionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping(value = "/system")
public class SystemController {

    @Value("${info.app.groupId}")
    private String groupId;

    @Value("${info.app.artifactId}")
    private String artifactId;

    @Value("${info.app.name}")
    private String name;

    @Value("${info.app.version}")
    private String version;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<PingResponse> getPing() {

        return new ResponseEntity<PingResponse>(new PingResponse(new Date()), HttpStatus.OK);
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public ResponseEntity<VersionResponse> getVersion() {

        return new ResponseEntity<VersionResponse>(new VersionResponse(groupId, artifactId, name, version), HttpStatus.OK);
    }

}
