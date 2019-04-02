package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface LearnerService {

    IdResponseV1 insertLearner(LearnerRequestV1 learnerInsertRequest);
    LearnerResponseV1 updateLearner(LearnerRequestV1 learnerUpdateRequest, UUID idLearner);
    LearnerResponseV1 getLearner(UUID idLearner);
    LearnerResponseV1 patchLearner(LearnerRequestV1 learnerUpdateRequest, UUID idLearner);
    PaginationResponseV1<LearnerResponseV1> getLearnersPagination(int page, int pageSize, String username, String nome, String cognome, String telefono, String codiceFiscale, String dataDiNascita,
                                                                  String luogoDiNascita, String email, String tipoDiStudio, String indirizzoDiStudio, String citta, String comune, String cap);

    void deleteLearner(UUID idLearner);
    Attachment addLearnerCurriculum(UploadRequestV1 curriculim, UUID idLearner) throws IOException;


    boolean candidateLearnerToCourse(UUID idLearner, UUID idCourse);

    void deleteLearnerCurriculum(UUID idLearner, UUID idAttachment);
}
