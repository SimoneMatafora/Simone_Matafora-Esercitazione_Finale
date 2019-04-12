package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1;
import it.tcgroup.vilear.coursemanager.entity.TeacherEntity;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;

import java.io.IOException;
import java.util.List;
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
    Attachment addTeacherCurriculum(UploadRequestV1 curriculim, UUID idTeacher) throws IOException;
    void deleteTeacherCurriculum(UUID idTeacher);
    List<TeacherResponseV1> getCandidateTeacher();
    TeacherResponseV1 updateIdTeacher(String email, String userId);

}
