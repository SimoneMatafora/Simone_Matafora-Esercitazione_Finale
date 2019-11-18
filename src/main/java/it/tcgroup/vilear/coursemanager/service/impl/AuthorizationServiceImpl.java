package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.common.exception.BadRequestException;
import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.common.util.HttpUtil;
import it.tcgroup.vilear.coursemanager.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

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
    public Boolean checkAlive(UUID checkAliveUserId){

        try {

            Boolean result = null;

            HashMap<String, String> headersparams = new HashMap<>();
            headersparams.put("id-user", checkAliveUserId.toString());

            httpUtil.callWithoutCert(endpointUrl + checkAliveUrl, HttpMethod.GET, null, headersparams,null);

            return result;

        } catch (IOException e) {
            throw new RuntimeException("Connection error with Vilear-Auth");
        }
    }




}
