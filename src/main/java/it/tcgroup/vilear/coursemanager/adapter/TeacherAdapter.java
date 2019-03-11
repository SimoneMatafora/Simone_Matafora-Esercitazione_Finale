package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.entity.TeacherEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.dto.AddressDto;
import it.tcgroup.vilear.coursemanager.entity.dto.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TeacherAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    public TeacherEntity adptTeacherRequestToTeacher(TeacherRequestV1 teacherInsertRequest){

        if(teacherInsertRequest == null)
            return null;

        TeacherEntity teacher = new TeacherEntity();

        Address address = null;
        if (teacherInsertRequest.getAddress() != null)
            address = addressAdapter.adptAddressRequestToAddress(teacherInsertRequest.getAddress());

        teacher.setAccreditedFt(teacherInsertRequest.getAccreditedFt());
        teacher.setRegister(teacherInsertRequest.getRegister());
        teacher.setProfessionalArea(teacherInsertRequest.getProfessionalArea());
        teacher.setAuthorized(teacherInsertRequest.getAuthorized());
        teacher.setAccreditedFtCode(teacherInsertRequest.getAccreditedFtCode());
        teacher.setFiscalCode(teacherInsertRequest.getFiscalCode());
        teacher.setSurname(teacherInsertRequest.getSurname());
        teacher.setCurriculumVitae(teacherInsertRequest.getCurriculumVitae());
        teacher.setDateOfBirth(teacherInsertRequest.getDateOfBirth());
        teacher.setPublicEmployee(teacherInsertRequest.getPublicEmployee());
        teacher.setEmail(teacherInsertRequest.getEmail());
        teacher.setProfessionalOrderRegistration(teacherInsertRequest.getProfessionalOrderRegistration());
        teacher.setBirthPlace(teacherInsertRequest.getBirthPlace());
        teacher.setName(teacherInsertRequest.getName());
        teacher.setNote(teacherInsertRequest.getNote());
        teacher.setVatNumber(teacherInsertRequest.getVatNumber());
        teacher.setSector(teacherInsertRequest.getSector());
        teacher.setPhone(teacherInsertRequest.getPhone());
        teacher.setVatHolder(teacherInsertRequest.getVatHolder());
        teacher.setUsername(teacherInsertRequest.getUsername());
        teacher.setAddress(address);

        return teacher;
    }

    public TeacherDto adptTeacherRequestToTeacherDto(TeacherRequestV1 teacherInsertRequest){

        if(teacherInsertRequest == null)
            return null;

        TeacherDto teacher = new TeacherDto();

        AddressDto address = null;
        if (teacherInsertRequest.getAddress() != null)
            address = addressAdapter.adptAddressRequestToAddressDto(teacherInsertRequest.getAddress());

        teacher.setId(teacherInsertRequest.getId());
        teacher.setAccreditedFt(teacherInsertRequest.getAccreditedFt());
        teacher.setRegister(teacherInsertRequest.getRegister());
        teacher.setProfessionalArea(teacherInsertRequest.getProfessionalArea());
        teacher.setAuthorized(teacherInsertRequest.getAuthorized());
        teacher.setAccreditedFtCode(teacherInsertRequest.getAccreditedFtCode());
        teacher.setFiscalCode(teacherInsertRequest.getFiscalCode());
        teacher.setSurname(teacherInsertRequest.getSurname());
        teacher.setCurriculumVitae(teacherInsertRequest.getCurriculumVitae());
        teacher.setDateOfBirth(teacherInsertRequest.getDateOfBirth());
        teacher.setPublicEmployee(teacherInsertRequest.getPublicEmployee());
        teacher.setEmail(teacherInsertRequest.getEmail());
        teacher.setProfessionalOrderRegistration(teacherInsertRequest.getProfessionalOrderRegistration());
        teacher.setBirthPlace(teacherInsertRequest.getBirthPlace());
        teacher.setName(teacherInsertRequest.getName());
        teacher.setNote(teacherInsertRequest.getNote());
        teacher.setVatNumber(teacherInsertRequest.getVatNumber());
        teacher.setSector(teacherInsertRequest.getSector());
        teacher.setPhone(teacherInsertRequest.getPhone());
        teacher.setVatHolder(teacherInsertRequest.getVatHolder());
        teacher.setUsername(teacherInsertRequest.getUsername());
        teacher.setAddress(address);

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
        teacherResponse.setUsername(teacher.getUsername());

        teacherResponse.setAddress(addressAdapter.adptAddressToAddressResponse(teacher.getAddress()));

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
        teacherResponse.setUsername(teacherDto.getUsername());

        teacherResponse.setAddress(addressAdapter.adptAddressDtoToAddressResponse(teacherDto.getAddress()));

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
