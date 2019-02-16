package it.tcgroup.vilear.dummy.service.impl;

import it.tcgroup.vilear.dummy.adapter.TeacherAdapter;
import it.tcgroup.vilear.dummy.common.exception.NotFoundException;
import it.tcgroup.vilear.dummy.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.dummy.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.PaginationResponseV1.*;
import it.tcgroup.vilear.dummy.controller.payload.response.TeacherResponseV1;
import it.tcgroup.vilear.dummy.entity.TeacherEntity;
import it.tcgroup.vilear.dummy.entity.Pagination;
import it.tcgroup.vilear.dummy.repository.TeacherEMRepository;
import it.tcgroup.vilear.dummy.repository.TeacherRepository;
import it.tcgroup.vilear.dummy.repository.AddressRepository;
import it.tcgroup.vilear.dummy.service.TeacherService;
import it.tcgroup.vilear.dummy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private AddressService addressService;

    @Autowired
    private AddressRepository indirizzoRepository;

    @Override
    public IdResponseV1 insertTeacher(TeacherRequestV1 teacherInsertRequest){

        TeacherEntity teacher = teacherAdapter.adptTeacherRequestToTeacher(teacherInsertRequest);

        addressService.insertAddress(teacher.getAddress());
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

        teacher.setAccreditedFt(teacherUpdateRequest.getAccreditedFt());
        teacher.setRegister(teacherUpdateRequest.getRegister());
        teacher.setProfessionalArea(teacherUpdateRequest.getProfessionalArea());
        teacher.setAuthorized(teacherUpdateRequest.getAuthorized());
        teacher.setAccreditedFtCode(teacherUpdateRequest.getAccreditedFtCode());
        teacher.setFiscalCode(teacherUpdateRequest.getFiscalCode());
        teacher.setSurname(teacherUpdateRequest.getSurname());
        teacher.setCurriculumVitae(teacherUpdateRequest.getCurriculumVitae());
        teacher.setDateOfBirth(teacherUpdateRequest.getDateOfBirth());
        teacher.setPublicEmployee(teacherUpdateRequest.getPublicEmployee());
        teacher.setEmail(teacherUpdateRequest.getEmail());
        teacher.setProfessionalOrderRegistration(teacherUpdateRequest.getProfessionalOrderRegistration());
        teacher.setBirthPlace(teacherUpdateRequest.getBirthPlace());
        teacher.setName(teacherUpdateRequest.getName());
        teacher.setNote(teacherUpdateRequest.getNote());
        teacher.setVatNumber(teacherUpdateRequest.getVatNumber());
        teacher.setSector(teacherUpdateRequest.getSector());
        teacher.setPhone(teacherUpdateRequest.getPhone());
        teacher.setVatHolder(teacherUpdateRequest.getVatHolder());
        teacher.setUsername(teacherUpdateRequest.getUsername());

        teacher.setAddress(addressService.updateAddress(teacherUpdateRequest.getAddress(), teacher.getAddress().getId()));

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

        if( teacherPatch.getCurriculumVitae() != null)
            teacher.setCurriculumVitae(teacherPatch.getCurriculumVitae());

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

        if( teacherUpdateRequest.getAddress() != null)
            teacher.setAddress(addressService.updateAddress(teacherUpdateRequest.getAddress(), teacher.getAddress().getId()));

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

}
