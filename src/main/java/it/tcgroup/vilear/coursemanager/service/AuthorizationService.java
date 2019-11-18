package it.tcgroup.vilear.coursemanager.service;

import java.util.UUID;

public interface AuthorizationService {

    Boolean checkAlive(UUID checkAliveUserId);
}
