package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.AttachmentAdapter;
import it.tcgroup.vilear.coursemanager.adapter.TeacherAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.TeacherEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.repository.TeacherEMRepository;
import it.tcgroup.vilear.coursemanager.repository.TeacherRepository;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import it.tcgroup.vilear.coursemanager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Transactional
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherEMRepository teacherEMRepository;

    @Autowired
    private TeacherAdapter teacherAdapter;

    @Autowired
    private FilemanagerService filemanagerService;

    @Autowired
    private AttachmentAdapter attachmentAdapter;

    @Override
    public IdResponseV1 insertTeacher(TeacherRequestV1 teacherInsertRequest){

        TeacherEntity teacher = teacherAdapter.adptTeacherRequestToTeacher(teacherInsertRequest);
        teacherRepository.save(teacher);

        return teacherAdapter.adptTeacherIdToTeacherIdResponse(teacher);

    }

    @Override
    public TeacherResponseV1 getTeacher(UUID idTeacher){

        Optional<TeacherEntity> teacher = teacherRepository.findById(idTeacher);
        if(!teacher.isPresent()){
            throw new NotFoundException("Teacher with id " + idTeacher+ " not found");
        }

        return teacherAdapter.adptTeacherToTeacherResponse(teacher.get());
    }

    @Modifying
    @Override
    public TeacherResponseV1 updateTeacher(TeacherRequestV1 teacherUpdateRequest, UUID idTeacher){

        Optional<TeacherEntity> optTeacher = teacherRepository.findById(idTeacher);
        if(!optTeacher.isPresent()){
            throw new NotFoundException("Teacher with id " + idTeacher+ " not found");
        }

        TeacherEntity teacher = optTeacher.get();

        TeacherEntity teacherUpdate = teacherAdapter.adptTeacherRequestToTeacher(teacherUpdateRequest);

        teacher.setAccreditedFt(teacherUpdate.getAccreditedFt());
        teacher.setRegister(teacherUpdate.getRegister());
        teacher.setProfessionalArea(teacherUpdate.getProfessionalArea());
        teacher.setAuthorized(teacherUpdate.getAuthorized());
        teacher.setAccreditedFtCode(teacherUpdate.getAccreditedFtCode());
        teacher.setFiscalCode(teacherUpdate.getFiscalCode());
        teacher.setSurname(teacherUpdate.getSurname());
        //teacher.setCurriculumVitae(teacherUpdate.getCurriculumVitae());
        teacher.setDateOfBirth(teacherUpdate.getDateOfBirth());
        teacher.setPublicEmployee(teacherUpdate.getPublicEmployee());
        teacher.setEmail(teacherUpdate.getEmail());
        teacher.setProfessionalOrderRegistration(teacherUpdate.getProfessionalOrderRegistration());
        teacher.setBirthPlace(teacherUpdate.getBirthPlace());
        teacher.setName(teacherUpdate.getName());
        teacher.setNote(teacherUpdate.getNote());
        teacher.setVatNumber(teacherUpdate.getVatNumber());
        teacher.setSector(teacherUpdate.getSector());
        teacher.setPhone(teacherUpdate.getPhone());
        teacher.setVatHolder(teacherUpdate.getVatHolder());
        teacher.setUsername(teacherUpdate.getUsername());
        teacher.setAddress(teacherUpdate.getAddress());

        teacherRepository.save(teacher);

        return  teacherAdapter.adptTeacherToTeacherResponse(teacher);
    }

    @Modifying
    @Override
    public TeacherResponseV1 patchTeacher(TeacherRequestV1 teacherUpdateRequest, UUID idTeacher){

        Optional<TeacherEntity> optTeacher = teacherRepository.findById(idTeacher);
        if(!optTeacher.isPresent()){
            throw new NotFoundException("Teacher with id " + idTeacher+ " not found");
        }
        TeacherEntity teacher = optTeacher.get();

        TeacherEntity teacherPatch = teacherAdapter.adptTeacherRequestToTeacher(teacherUpdateRequest);

        if( teacherPatch.getAccreditedFt() != null)
            teacher.setAccreditedFt(teacherPatch.getAccreditedFt());

        if( teacherPatch.getRegister() != null)
            teacher.setRegister(teacherPatch.getRegister());

        if( teacherPatch.getProfessionalArea()!= null)
            teacher.setProfessionalArea(teacherPatch.getProfessionalArea());

        if( teacherPatch.getAuthorized() != null)
            teacher.setAuthorized(teacherPatch.getAuthorized());

        if( teacherPatch.getAccreditedFtCode() != null)
            teacher.setAccreditedFtCode(teacherPatch.getAccreditedFtCode());

        if( teacherPatch.getFiscalCode() != null)
            teacher.setFiscalCode(teacherPatch.getFiscalCode());

        if( teacherPatch.getSurname() != null)
            teacher.setSurname(teacherPatch.getSurname());

        /*if( teacherPatch.getCurriculumVitae() != null)
            teacher.setCurriculumVitae(teacherPatch.getCurriculumVitae());*/

        if( teacherPatch.getDateOfBirth() != null)
            teacher.setDateOfBirth(teacherPatch.getDateOfBirth());

        if( teacherPatch.getPublicEmployee() != null)
            teacher.setPublicEmployee(teacherPatch.getPublicEmployee());

        if( teacherPatch.getEmail() != null)
            teacher.setEmail(teacherPatch.getEmail());

        if( teacherPatch.getProfessionalOrderRegistration() != null)
            teacher.setProfessionalOrderRegistration(teacherPatch.getProfessionalOrderRegistration());

        if( teacherPatch.getBirthPlace() != null)
            teacher.setBirthPlace(teacherPatch.getBirthPlace());

        if( teacherPatch.getName() != null)
            teacher.setName(teacherPatch.getName());

        if( teacherPatch.getNote() != null)
            teacher.setNote(teacherPatch.getNote());

        if( teacherPatch.getVatNumber() != null)
            teacher.setVatNumber(teacherPatch.getVatNumber());

        if( teacherPatch.getSector() != null)
            teacher.setSector(teacherPatch.getSector());

        if( teacherPatch.getPhone() != null)
            teacher.setPhone(teacherPatch.getPhone());

        if( teacherPatch.getVatHolder() != null)
            teacher.setVatHolder(teacherPatch.getVatHolder());

        if( teacherPatch.getUsername() != null)
            teacher.setUsername(teacherPatch.getUsername());

        if( teacherPatch.getAddress() != null)
            teacher.setAddress(teacherPatch.getAddress());

        teacherRepository.save(teacher);

        return teacherAdapter.adptTeacherToTeacherResponse(teacher);
    }

    @Override
    public PaginationResponseV1<TeacherResponseV1> getTeachersPagination(int page, int pageSize, String username, String name, String surname, String phone, String fiscalCode, String dateOfBirth,
                                                                        String birthPlace, String email, String professionalArea, Boolean publicEmployee, Boolean accreditedFt, String accreditedFtCode,
                                                                        Boolean authorized, Boolean professionalOrderRegistration, String register, Boolean vatHolder, String sector, String city, String region, String province){

        Pagination<TeacherEntity> teachersPagination = new Pagination<>();
        //List<TeacherEntity> docentiList = teacherRepository.findAll();

        List<TeacherEntity> teachersList = teacherEMRepository.getTeachersForPagination(username, name, surname, phone, fiscalCode, dateOfBirth,
                birthPlace, email, professionalArea, publicEmployee, accreditedFt, accreditedFtCode, authorized,
                professionalOrderRegistration, register, vatHolder, sector, city, region, province);

        teachersPagination.setStats(new InfoPagination(teachersList.size(), page, pageSize));

        int start = teachersPagination.getStats().getStartPage();
        int count = 0;
        List<TeacherEntity> docentiForPagination = new LinkedList<>();

        while (count < teachersPagination.getStats().getPageSize() && ((start - 1) + count) < teachersList.size()) {
            docentiForPagination.add((teachersList.get((start - 1) + count)));
            count++;
        }

        teachersPagination.setItems(docentiForPagination);

        return teacherAdapter.adpTeacherPaginationToTeacherPaginationResposne(teachersPagination);
    }

    @Override
    public void deleteTeacher(UUID idTeacher){
        teacherRepository.deleteById(idTeacher);
    }

    @Override
    public TeacherResponseV1 addTeacherCurriculum(UploadRequestV1 curriculim, UUID idTeacher) throws IOException {

        Optional<TeacherEntity> optTeacher = teacherRepository.findById(idTeacher);
        if(!optTeacher.isPresent()){
            throw new NotFoundException("Tacher with id " + idTeacher+ " not found");
        }

        TeacherEntity teacher = optTeacher.get();

        curriculim.setResourceId(idTeacher.toString());
        curriculim.setResourceType("curriculum");

        UploadResponseV1 response = filemanagerService.uploadFile(curriculim);

        teacher.setCurriculumVitae(attachmentAdapter.adptUploadResponseToAttachment(response));

        teacherRepository.save(teacher);

        return teacherAdapter.adptTeacherToTeacherResponse(teacher);

    }

    @Override
    public void deleteTeacherCurriculum(UUID idTeacher){

        Optional<TeacherEntity> optTeacher = teacherRepository.findById(idTeacher);
        if(!optTeacher.isPresent()){
            throw new NotFoundException("Tacher with id " + idTeacher+ " not found");
        }

        TeacherEntity teacher = optTeacher.get();
        teacher.setCurriculumVitae(null);
        teacherRepository.save(teacher);
    }

}
