package it.tcgroup.vilear.dummy.service;

import it.tcgroup.vilear.dummy.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.dummy.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.PaginationResponseV1;
import java.util.UUID;

public interface LearnerService {

    IdResponseV1 insertLearner(LearnerRequestV1 discenteInsertRequest);
    LearnerResponseV1 updateLearner(LearnerRequestV1 discenteUpdateRequest, UUID idLearner);
    LearnerResponseV1 getLearner(UUID idLearner);
    LearnerResponseV1 patchLearner(LearnerRequestV1 discenteUpdateRequest, UUID idLearner);
    PaginationResponseV1<LearnerResponseV1> getLearnersPagination(int page, int pageSize, String username, String nome, String cognome, String telefono, String codiceFiscale, String dataDiNascita,
                                                                  String luogoDiNascita, String email, String tipoDiStudio, String indirizzoDiStudio, String citta, String comune, String cap);

    void deleteLearner(UUID idLearner);
}
