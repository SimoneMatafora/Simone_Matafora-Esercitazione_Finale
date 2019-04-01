package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.common.exception.BadRequestException;
import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.common.util.HttpUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CheckAliveRequestV1;
import it.tcgroup.vilear.coursemanager.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private DateUtil dateUtil;

    @Value("${auth.api.endpooint}")
    private String endpointUrl;

    @Value("${auth.api.checkAlive}")
    private String checkAliveUrl;

    @Override
    public Boolean checkAlive(String checkAlive){

        try {

            Boolean result = httpUtil.callWithoutCert(endpointUrl + checkAliveUrl, HttpMethod.POST, new CheckAliveRequestV1(checkAlive), null, new ParameterizedTypeReference<Boolean>() {});

            if(result)
                throw new BadRequestException("Session expired, Please login again");

            return result;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }




}
