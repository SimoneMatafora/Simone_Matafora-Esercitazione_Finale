package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.PartnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PartnerResponseV1;

import java.util.UUID;

public interface PartnerService {


    IdResponseV1 insertPartner(PartnerRequestV1 partnerInsertRequest);
    PartnerResponseV1 updatePartner(PartnerRequestV1 partnerInsertRequest, UUID idPartner);
    PartnerResponseV1 getPartner(UUID idPartner);
    PartnerResponseV1 patchPartner(PartnerRequestV1 partnerInsertRequest, UUID idPartner);
    PaginationResponseV1<PartnerResponseV1> getPartnersPagination(int page, int pageSize, String businessName, String company, String managerName, String accreditedFt, String teacherName, String teacherSurname,
                                                                  String teacherProfessionalArea, String teacherPublicEmployee, String citta, String comune, String cap);
    void deletePartner(UUID idPartner);
}
