package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface LearnerService {

    IdResponseV1 insertLearner(LearnerRequestV1 filialeInsertRequest);
    LearnerResponseV1 getLearner(UUID idLearner);
    List<LearnerResponseV1> getAllLearner();
    void deleteLeraner(UUID idLearner);
    LearnerResponseV1 updateLearner(LearnerRequestV1 learnerUpdateRequest, UUID idLearner);
    LearnerResponseV1 patchLearner(LearnerRequestV1 learnerUpdateRequest, UUID idLearner);
    PaginationResponseV1<LearnerResponseV1> getLearnersPagination(int page, int pageSize, String name, String surname,
                                                                  String fiscalCode, String dateOfBirth, String birthPlace, String email, String phone,
                                                                  String note, String degreeOfStudies, String courseOfStudy, String residentialNation,
                                                                  String residentialRegion, String residentialProvince, String residentialCity,
                                                                  String residentialStreet, String residentialNumber, String domicileNation,
                                                                  String domicileRegion, String domicileProvince, String domicileCity,
                                                                  String domicileStreet, String domicileNumber, Boolean domicileEqualsResidential) throws ParseException;
}
