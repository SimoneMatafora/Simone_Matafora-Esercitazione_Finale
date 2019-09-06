package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;
import it.tcgroup.vilear.coursemanager.common.exception.BadRequestException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.TeacherCourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRegistrationRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.entity.TeacherEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;
import it.tcgroup.vilear.coursemanager.entity.dto.TeacherDto;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class TeacherAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    @Autowired
    private AttachmentAdapter attachmentAdapter;

    @Autowired
    private FilemanagerService filemanagerService;

    public TeacherRequestV1 adptTeacherRegisterRequestToTeacherRequest(TeacherRegistrationRequestV1 teacherInsertRequest) {

        try {

            if (teacherInsertRequest == null)
                return null;

            TeacherRequestV1 teacherRequest = new TeacherRequestV1();

            teacherRequest.setRegister(teacherInsertRequest.getRegister());
            teacherRequest.setProfessionalArea(teacherInsertRequest.getProfessionalArea());
            teacherRequest.setAccreditedFtCode(teacherInsertRequest.getAccreditedFtCode());
            teacherRequest.setFiscalCode(teacherInsertRequest.getFiscalCode());
            teacherRequest.setSurname(teacherInsertRequest.getSurname());
            teacherRequest.setDateOfBirth(teacherInsertRequest.getDateOfBirth());
            teacherRequest.setEmail(teacherInsertRequest.getEmail());
            teacherRequest.setBirthPlace(teacherInsertRequest.getBirthPlace());
            teacherRequest.setName(teacherInsertRequest.getName());
            teacherRequest.setNote(teacherInsertRequest.getNote());
            teacherRequest.setVatNumber(teacherInsertRequest.getVatNumber());
            teacherRequest.setSector(teacherInsertRequest.getSector());
            teacherRequest.setPhone(teacherInsertRequest.getPhone());
            teacherRequest.setResidentialAddress(teacherInsertRequest.getResidentialAddress());
            teacherRequest.setDomicileEqualsResidential(teacherInsertRequest.getDomicileEqualsResidential());
            teacherRequest.setDomicileAddress(teacherInsertRequest.getDomicileAddress());
            teacherRequest.setVatHolder(teacherInsertRequest.getVatHolder());
            teacherRequest.setProfessionalOrderRegistration(teacherInsertRequest.getProfessionalOrderRegistration());
            teacherRequest.setAuthorized(teacherInsertRequest.getAuthorized());
            teacherRequest.setAccreditedFt(teacherInsertRequest.getAccreditedFt());
            teacherRequest.setPublicEmployee(teacherInsertRequest.getPublicEmployee());

            return teacherRequest;

        }catch (Exception e) {
            throw new RuntimeException("On filemanager: " + e.getMessage());
        }
    }

    public TeacherEntity adptTeacherRequestToTeacher(TeacherRequestV1 teacherInsertRequest) {

        try {

            if (teacherInsertRequest == null)
                return null;

            TeacherEntity teacher = new TeacherEntity();

            System.out.println("1");

            Address residentialAddress = null;
            if (teacherInsertRequest.getResidentialAddress() != null)
                residentialAddress = addressAdapter.adptAddressRequestToAddress(teacherInsertRequest.getResidentialAddress());

            System.out.println("2");

            Address domicileAddress = null;
            if (teacherInsertRequest.getDomicileAddress() != null)
                domicileAddress = addressAdapter.adptAddressRequestToAddress(teacherInsertRequest.getDomicileAddress());

            System.out.println("3");

            teacher.setId(UUID.fromString(teacherInsertRequest.getId()));
            System.out.println("4");
            teacher.setCurriculumVitae(teacherInsertRequest.getCurriculum());
            System.out.println("5");
            teacher.setRegister(teacherInsertRequest.getRegister());
            System.out.println("6");
            teacher.setProfessionalArea(teacherInsertRequest.getProfessionalArea());
            System.out.println("7");
            teacher.setAccreditedFtCode(teacherInsertRequest.getAccreditedFtCode());
            System.out.println("8");
            teacher.setFiscalCode(teacherInsertRequest.getFiscalCode());
            System.out.println("9");
            teacher.setSurname(teacherInsertRequest.getSurname());
            System.out.println("10");
            teacher.setDateOfBirth(teacherInsertRequest.getDateOfBirth());
            System.out.println("11");
            teacher.setEmail(teacherInsertRequest.getEmail());
            System.out.println("12");
            teacher.setBirthPlace(teacherInsertRequest.getBirthPlace());
            System.out.println("13");
            teacher.setName(teacherInsertRequest.getName());
            System.out.println("14");
            teacher.setNote(teacherInsertRequest.getNote());
            System.out.println("15");
            teacher.setVatNumber(teacherInsertRequest.getVatNumber());
            System.out.println("16");
            teacher.setSector(teacherInsertRequest.getSector());
            System.out.println("17");
            teacher.setPhone(teacherInsertRequest.getPhone());
            System.out.println("18");
            teacher.setResidentialAddress(residentialAddress);
            System.out.println("19");

            teacher.setDomicileEqualsResidential(teacherInsertRequest.getDomicileEqualsResidential());
            System.out.println("20");

            teacher.setDomicileAddress(domicileAddress);
            System.out.println("21");

            if (teacherInsertRequest.getVatHolder() == null)
                teacher.setVatHolder(false);
            else
                teacher.setVatHolder(teacherInsertRequest.getVatHolder());

            System.out.println("22");
            if (teacherInsertRequest.getProfessionalOrderRegistration() == null)
                teacher.setProfessionalOrderRegistration(false);
            else
                teacher.setProfessionalOrderRegistration(teacherInsertRequest.getProfessionalOrderRegistration());

            System.out.println("23");
            if (teacherInsertRequest.getAuthorized() == null)
                teacher.setAuthorized(false);
            else
                teacher.setAuthorized(teacherInsertRequest.getAuthorized());

            System.out.println("24");
            if (teacherInsertRequest.getAccreditedFt() == null)
                teacher.setAccreditedFt(false);
            else
                teacher.setAccreditedFt(teacherInsertRequest.getAccreditedFt());

            System.out.println("25");
            if (teacherInsertRequest.getPublicEmployee() == null)
                teacher.setPublicEmployee(false);
            else
                teacher.setPublicEmployee(teacherInsertRequest.getPublicEmployee());

            return teacher;

        }catch (Exception e) {
            throw new RuntimeException("On filemanager: " + e.getMessage());
        }
    }

    public TeacherDto adptTeacherRequestToTeacherDto(TeacherRequestV1 teacherInsertRequest){

        if(teacherInsertRequest == null)
            return null;

        TeacherDto teacher = new TeacherDto();

        AddressDto residentialAddress = null;
        if (teacherInsertRequest.getResidentialAddress() != null)
            residentialAddress = addressAdapter.adptAddressRequestToAddressDto(teacherInsertRequest.getResidentialAddress());

        AddressDto domicileAddress = null;
        if (teacherInsertRequest.getDomicileAddress() != null)
            domicileAddress = addressAdapter.adptAddressRequestToAddressDto(teacherInsertRequest.getDomicileAddress());

        teacher.setId(teacherInsertRequest.getId());
        teacher.setRegister(teacherInsertRequest.getRegister());
        teacher.setAccreditedFtCode(teacherInsertRequest.getAccreditedFtCode());
        teacher.setProfessionalArea(teacherInsertRequest.getProfessionalArea());
        teacher.setFiscalCode(teacherInsertRequest.getFiscalCode());
        teacher.setSurname(teacherInsertRequest.getSurname());
        teacher.setDateOfBirth(teacherInsertRequest.getDateOfBirth());
        teacher.setEmail(teacherInsertRequest.getEmail());
        teacher.setBirthPlace(teacherInsertRequest.getBirthPlace());
        teacher.setName(teacherInsertRequest.getName());
        teacher.setNote(teacherInsertRequest.getNote());
        teacher.setVatNumber(teacherInsertRequest.getVatNumber());
        teacher.setSector(teacherInsertRequest.getSector());
        teacher.setPhone(teacherInsertRequest.getPhone());
        teacher.setResidentialAddress(residentialAddress);

        teacher.setDomicileEqualsResidential(teacherInsertRequest.getDomicileEqualsResidential());

        teacher.setDomicileAddress(domicileAddress);

        if(teacherInsertRequest.getVatHolder() == null)
            teacher.setVatHolder(false);
        else
            teacher.setVatHolder(teacherInsertRequest.getVatHolder());

        if(teacherInsertRequest.getProfessionalOrderRegistration() == null)
            teacher.setProfessionalOrderRegistration(false);
        else
            teacher.setProfessionalOrderRegistration(teacherInsertRequest.getProfessionalOrderRegistration());

        if(teacherInsertRequest.getAuthorized() == null)
            teacher.setAuthorized(false);
        else
            teacher.setAuthorized(teacherInsertRequest.getAuthorized());

        if(teacherInsertRequest.getAccreditedFt() == null)
            teacher.setAccreditedFt(false);
        else
            teacher.setAccreditedFt(teacherInsertRequest.getAccreditedFt());

        if(teacherInsertRequest.getPublicEmployee() == null)
            teacher.setPublicEmployee(false);
        else
            teacher.setPublicEmployee(teacherInsertRequest.getPublicEmployee());

        return teacher;
    }

    public TeacherDto adptTeacherRequestDtoToTeacherDto(TeacherRequestDtoV1 teacherInsertRequest){

        if(teacherInsertRequest == null)
            return null;

        TeacherDto teacher = new TeacherDto();

        AddressDto residentialAddress = null;
        if (teacherInsertRequest.getResidentialAddress() != null)
            residentialAddress = addressAdapter.adptAddressRequestToAddressDto(teacherInsertRequest.getResidentialAddress());

        AddressDto domicileAddress = null;
        if (teacherInsertRequest.getDomicileAddress() != null)
            domicileAddress = addressAdapter.adptAddressRequestToAddressDto(teacherInsertRequest.getDomicileAddress());

        teacher.setId(teacherInsertRequest.getId());
        teacher.setRegister(teacherInsertRequest.getRegister());
        teacher.setAccreditedFtCode(teacherInsertRequest.getAccreditedFtCode());
        teacher.setProfessionalArea(teacherInsertRequest.getProfessionalArea());
        teacher.setFiscalCode(teacherInsertRequest.getFiscalCode());
        teacher.setSurname(teacherInsertRequest.getSurname());
        teacher.setDateOfBirth(teacherInsertRequest.getDateOfBirth());
        teacher.setEmail(teacherInsertRequest.getEmail());
        teacher.setBirthPlace(teacherInsertRequest.getBirthPlace());
        teacher.setName(teacherInsertRequest.getName());
        teacher.setNote(teacherInsertRequest.getNote());
        teacher.setVatNumber(teacherInsertRequest.getVatNumber());
        teacher.setSector(teacherInsertRequest.getSector());
        teacher.setPhone(teacherInsertRequest.getPhone());
        teacher.setCurriculumVitae(teacherInsertRequest.getCurriculum());
        teacher.setResidentialAddress(residentialAddress);
        teacher.setDomicileEqualsResidential(teacherInsertRequest.getDomicileEqualsResidential());
        teacher.setDomicileAddress(domicileAddress);

        if(teacherInsertRequest.getVatHolder() == null)
            teacher.setVatHolder(false);
        else
            teacher.setVatHolder(teacherInsertRequest.getVatHolder());

        if(teacherInsertRequest.getProfessionalOrderRegistration() == null)
            teacher.setProfessionalOrderRegistration(false);
        else
            teacher.setProfessionalOrderRegistration(teacherInsertRequest.getProfessionalOrderRegistration());

        if(teacherInsertRequest.getAuthorized() == null)
            teacher.setAuthorized(false);
        else
            teacher.setAuthorized(teacherInsertRequest.getAuthorized());

        if(teacherInsertRequest.getAccreditedFt() == null)
            teacher.setAccreditedFt(false);
        else
            teacher.setAccreditedFt(teacherInsertRequest.getAccreditedFt());

        if(teacherInsertRequest.getPublicEmployee() == null)
            teacher.setPublicEmployee(false);
        else
            teacher.setPublicEmployee(teacherInsertRequest.getPublicEmployee());

        return teacher;
    }

    public IdResponseV1 adptTeacherIdToTeacherIdResponse(TeacherEntity teacher){

        if(teacher == null)
            return null;

        IdResponseV1 teacherIdResponse = new IdResponseV1();
        teacherIdResponse.setId(teacher.getId());

        return teacherIdResponse;
    }

    public TeacherResponseV1 adptTeacherToTeacherResponse(TeacherEntity teacher){

        if(teacher == null)
            return null;

        TeacherResponseV1 teacherResponse = new TeacherResponseV1();

        teacherResponse.setId(teacher.getId().toString());
        teacherResponse.setAccreditedFt(teacher.getAccreditedFt());
        teacherResponse.setRegister(teacher.getRegister());
        teacherResponse.setProfessionalArea(teacher.getProfessionalArea());
        teacherResponse.setAuthorized(teacher.getAuthorized());
        teacherResponse.setAccreditedFtCode(teacher.getAccreditedFtCode());
        teacherResponse.setFiscalCode(teacher.getFiscalCode());
        teacherResponse.setSurname(teacher.getSurname());
        teacherResponse.setCurriculumVitae(teacher.getCurriculumVitae());
        teacherResponse.setDateOfBirth(teacher.getDateOfBirth());
        teacherResponse.setPublicEmployee(teacher.getPublicEmployee());
        teacherResponse.setEmail(teacher.getEmail());
        teacherResponse.setProfessionalOrderRegistration(teacher.getProfessionalOrderRegistration());
        teacherResponse.setBirthPlace(teacher.getBirthPlace());
        teacherResponse.setName(teacher.getName());
        teacherResponse.setNote(teacher.getNote());
        teacherResponse.setVatNumber(teacher.getVatNumber());
        teacherResponse.setSector(teacher.getSector());
        teacherResponse.setPhone(teacher.getPhone());
        teacherResponse.setVatHolder(teacher.getVatHolder());

        teacherResponse.setResidentialAddress(addressAdapter.adptAddressToAddressResponse(teacher.getResidentialAddress()));
        teacherResponse.setDomicileAddress(addressAdapter.adptAddressToAddressResponse(teacher.getDomicileAddress()));
        teacherResponse.setDomicileEqualsResidential(teacher.getDomicileEqualsResidential());

        return  teacherResponse;
    }

    public TeacherResponseV1 adptTeacherDtoToTeacherResponse(TeacherDto teacherDto){

        if(teacherDto == null)
            return null;

        TeacherResponseV1 teacherResponse = new TeacherResponseV1();

        teacherResponse.setId(teacherDto.getId());
        teacherResponse.setAccreditedFt(teacherDto.getAccreditedFt());
        teacherResponse.setRegister(teacherDto.getRegister());
        teacherResponse.setProfessionalArea(teacherDto.getProfessionalArea());
        teacherResponse.setAuthorized(teacherDto.getAuthorized());
        teacherResponse.setAccreditedFtCode(teacherDto.getAccreditedFtCode());
        teacherResponse.setFiscalCode(teacherDto.getFiscalCode());
        teacherResponse.setSurname(teacherDto.getSurname());
        teacherResponse.setCurriculumVitae(teacherDto.getCurriculumVitae());
        teacherResponse.setDateOfBirth(teacherDto.getDateOfBirth());
        teacherResponse.setPublicEmployee(teacherDto.getPublicEmployee());
        teacherResponse.setEmail(teacherDto.getEmail());
        teacherResponse.setProfessionalOrderRegistration(teacherDto.getProfessionalOrderRegistration());
        teacherResponse.setBirthPlace(teacherDto.getBirthPlace());
        teacherResponse.setName(teacherDto.getName());
        teacherResponse.setNote(teacherDto.getNote());
        teacherResponse.setVatNumber(teacherDto.getVatNumber());
        teacherResponse.setSector(teacherDto.getSector());
        teacherResponse.setPhone(teacherDto.getPhone());
        teacherResponse.setVatHolder(teacherDto.getVatHolder());

        teacherResponse.setResidentialAddress(addressAdapter.adptAddressDtoToAddressResponse(teacherDto.getResidentialAddress()));
        teacherResponse.setDomicileAddress(addressAdapter.adptAddressDtoToAddressResponse(teacherDto.getDomicileAddress()));
        teacherResponse.setDomicileEqualsResidential(teacherDto.getDomicileEqualsResidential());

        return  teacherResponse;
    }

    public List<TeacherResponseV1> adptTeacherToTeacherResponse(List<TeacherEntity> teacherList){

        if(teacherList == null)
            return null;

        List<TeacherResponseV1> teacherResponseList= new LinkedList<>();
        for (TeacherEntity att : teacherList){
            teacherResponseList.add(this.adptTeacherToTeacherResponse(att));
        }
        return teacherResponseList;
    }

    public PaginationResponseV1<TeacherResponseV1> adpTeacherPaginationToTeacherPaginationResposne(Pagination teacherPagination){

        PaginationResponseV1<TeacherResponseV1> teacherPaginationResponse = new PaginationResponseV1<>();

        teacherPaginationResponse.setItems(this.adptTeacherToTeacherResponse(teacherPagination.getItems()));
        teacherPaginationResponse.setStats(teacherPagination.getStats());

        return teacherPaginationResponse;
    }

}
