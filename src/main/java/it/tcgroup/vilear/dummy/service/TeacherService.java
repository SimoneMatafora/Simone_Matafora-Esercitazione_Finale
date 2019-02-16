package it.tcgroup.vilear.dummy.service;

import it.tcgroup.vilear.dummy.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.dummy.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.TeacherResponseV1;

import java.util.UUID;

public interface TeacherService {

    IdResponseV1 insertTeacher(TeacherRequestV1 teacherInsertRequest);
    TeacherResponseV1 updateTeacher(TeacherRequestV1 teacherUpdateRequest, UUID idTeacher);
    TeacherResponseV1 getTeacher(UUID idTeacher);
    TeacherResponseV1 patchTeacher(TeacherRequestV1 teacherUpdateRequest, UUID idTeacher);
    PaginationResponseV1<TeacherResponseV1> getTeachersPagination(int page, int pageSize, String username, String nome, String cognome, String telefono, String codiceFiscale, String dataDiNascita,
                                                                 String luogoDiNascita, String email, String areaProfessionale, Boolean dipendentePubblico, Boolean accreditatoFt, String codiceAccredidatoFt,
                                                                 Boolean autorizzato, Boolean iscrizioneOrdineProfessionale, String albo, Boolean titolarePiva, String settore, String citta, String comune, String cap);
    void deleteTeacher(UUID idTeacher);
}
