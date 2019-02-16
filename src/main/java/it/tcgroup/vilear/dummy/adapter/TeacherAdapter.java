package it.tcgroup.vilear.dummy.adapter;

import it.tcgroup.vilear.dummy.controller.payload.request.TeacherRequestV1;
import it.tcgroup.vilear.dummy.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.dummy.controller.payload.response.TeacherResponseV1;
import it.tcgroup.vilear.dummy.entity.AddressEntity;
import it.tcgroup.vilear.dummy.entity.TeacherEntity;
import it.tcgroup.vilear.dummy.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TeacherAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    public TeacherEntity adptTeacherRequestToTeacher(TeacherRequestV1 teacherInsertRequest){

        TeacherEntity teacher = new TeacherEntity();

        AddressEntity address = null;
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

    public IdResponseV1 adptTeacherIdToTeacherIdResponse(TeacherEntity teacher){

        IdResponseV1 teacherIdResponse = new IdResponseV1();
        teacherIdResponse.setId(teacher.getId());

        return teacherIdResponse;
    }

    public TeacherResponseV1 adptTeacherToTeacherResponse(TeacherEntity teacher){

        TeacherResponseV1 teacherResponse = new TeacherResponseV1();

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

    public List<TeacherResponseV1> adptTeacherToTeacherResponse(List<TeacherEntity> docentiList){

        List<TeacherResponseV1> docentiResponseList= new LinkedList<>();
        for (TeacherEntity att : docentiList){
            docentiResponseList.add(this.adptTeacherToTeacherResponse(att));
        }
        return docentiResponseList;
    }

    public PaginationResponseV1<TeacherResponseV1> adpTeacherPaginationToTeacherPaginationResposne(Pagination teacherPagination){

        PaginationResponseV1<TeacherResponseV1> teacherPaginationResponse = new PaginationResponseV1<>();

        teacherPaginationResponse.setItems(this.adptTeacherToTeacherResponse(teacherPagination.getItems()));
        teacherPaginationResponse.setStats(teacherPagination.getStats());

        return teacherPaginationResponse;
    }

}
