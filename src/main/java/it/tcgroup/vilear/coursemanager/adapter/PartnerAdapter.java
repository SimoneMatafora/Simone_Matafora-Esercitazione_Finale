package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.PartnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.PartnerRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PartnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PartnerResponseV1.*;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.PartnerDto;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.AddressPartner;
import it.tcgroup.vilear.coursemanager.entity.jsonb.partner.TeacherPartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PartnerAdapter {

    @Autowired
    private TeacherPartnerAdapter teacherPartnerAdapter;

    @Autowired
    private AddressPartnerAdapter addressPartnerAdapter;

    public IdResponseV1 adptPartnerIdToPartnerIdResponse(PartnerEntity partner){

        if(partner == null)
            return null;

        IdResponseV1 idResponseV1 = new IdResponseV1();
        idResponseV1.setId(partner.getId());

        return idResponseV1;
    }

    public PartnerResponseV1 adptPartnerToPartnerResponse(PartnerEntity partner){

        if(partner == null)
            return null;

        PartnerResponseV1 partnerResponse = new PartnerResponseV1();

        partnerResponse.setId(partner.getId().toString());
        partnerResponse.setAccreditedFt(partner.getAccreditedFt());
        partnerResponse.setAccreditedFtCode(partner.getAccreditedFtCode());
        partnerResponse.setBusinessName(partner.getBusinessName());
        partnerResponse.setCompany(partner.getCompany());
        partnerResponse.setCostElement(partner.getCostElement());
        partnerResponse.setEmail(partner.getEmail());
        partnerResponse.setFax(partner.getFax());
        partnerResponse.setManagerName(partner.getManagerName());
        partnerResponse.setManagerNumber(partner.getManagerNumber());
        partnerResponse.setNote(partner.getNote());
        partnerResponse.setPhone(partner.getPhone());
        partnerResponse.setVatNumber(partner.getVatNumber());
        partnerResponse.setTeacherList(teacherPartnerAdapter.adptTeacherPartnerToTeacherPartnerResponse(partner.getTeacherList()));
        partnerResponse.setAddressList(addressPartnerAdapter.adptAddressPartnerToAddressPartnerResponse(partner.getAddressList()));

        return partnerResponse;

    }

    public List<PartnerResponseV1> adptPartnerToPartnerResponse(List<PartnerEntity> partnerList){

        if(partnerList == null)
            return null;

        List<PartnerResponseV1> partnerResponseList = new LinkedList<>();

        for (PartnerEntity att : partnerList){
            partnerResponseList.add(this.adptPartnerToPartnerResponse(att));
        }
        return partnerResponseList;
    }

    public PartnerEntity adptPartnerRequestToPartner(PartnerRequestV1 partnerRequest){

        if(partnerRequest == null)
            return null;

        PartnerEntity partner = new PartnerEntity();

        partner.setAccreditedFt(partnerRequest.getAccreditedFt());
        partner.setAccreditedFtCode(partnerRequest.getAccreditedFtCode());
        partner.setBusinessName(partnerRequest.getBusinessName());
        partner.setCompany(partnerRequest.getCompany());
        partner.setCostElement(partnerRequest.getCostElement());
        partner.setEmail(partnerRequest.getEmail());
        partner.setFax(partnerRequest.getFax());
        partner.setManagerName(partnerRequest.getManagerName());
        partner.setManagerNumber(partnerRequest.getManagerNumber());
        partner.setNote(partnerRequest.getNote());
        partner.setPhone(partnerRequest.getPhone());
        partner.setVatNumber(partnerRequest.getVatNumber());
        partner.setTeacherList(teacherPartnerAdapter.adptTeacherPartnerRequestToTeacherPartner(partnerRequest.getTeacherList()));
        partner.setAddressList(addressPartnerAdapter.adptAddressPartnerRequestToAddressPartner(partnerRequest.getAddressList()));

        return partner;

    }

    public List<PartnerEntity> adptPartnerRequestToPartner(List<PartnerRequestV1> partnerRequestList){

        if(partnerRequestList == null)
            return null;

        List<PartnerEntity> partnerList = new LinkedList<>();

        for (PartnerRequestV1 att : partnerRequestList){
            partnerList.add(this.adptPartnerRequestToPartner(att));
        }
        return partnerList;
    }

    public PartnerDto adptPartnerRequestToPartnerDto(PartnerRequestV1 partnerRequest){

        if(partnerRequest == null)
            return null;

        PartnerDto partner = new PartnerDto();

        partner.setId(partnerRequest.getId());
        partner.setAccreditedFt(partnerRequest.getAccreditedFt());
        partner.setAccreditedFtCode(partnerRequest.getAccreditedFtCode());
        partner.setBusinessName(partnerRequest.getBusinessName());
        partner.setCompany(partnerRequest.getCompany());
        partner.setCostElement(partnerRequest.getCostElement());
        partner.setEmail(partnerRequest.getEmail());
        partner.setFax(partnerRequest.getFax());
        partner.setManagerName(partnerRequest.getManagerName());
        partner.setManagerNumber(partnerRequest.getManagerNumber());
        partner.setNote(partnerRequest.getNote());
        partner.setPhone(partnerRequest.getPhone());
        partner.setVatNumber(partnerRequest.getVatNumber());
        partner.setTeacherList(teacherPartnerAdapter.adptTeacherPartnerRequestToTeacherPartner(partnerRequest.getTeacherList()));
        partner.setAddressList(addressPartnerAdapter.adptAddressPartnerRequestToAddressPartner(partnerRequest.getAddressList()));

        return partner;
    }

    public List<PartnerDto> adptPartnerRequestToPartnerDto(List<PartnerRequestV1> partnerRequestList){

        if(partnerRequestList == null)
            return null;

        List<PartnerDto> partnerList = new LinkedList<>();

        for (PartnerRequestV1 att : partnerRequestList){
            partnerList.add(this.adptPartnerRequestToPartnerDto(att));
        }
        return partnerList;
    }

    public PartnerResponseV1 adptPartnerDtoToPartnerResponse(PartnerDto partnerDto){

        if(partnerDto == null)
            return null;

        PartnerResponseV1 partnerResponse = new PartnerResponseV1();

        partnerResponse.setId(partnerDto.getId());
        partnerResponse.setAccreditedFt(partnerDto.getAccreditedFt());
        partnerResponse.setAccreditedFtCode(partnerDto.getAccreditedFtCode());
        partnerResponse.setBusinessName(partnerDto.getBusinessName());
        partnerResponse.setCompany(partnerDto.getCompany());
        partnerResponse.setCostElement(partnerDto.getCostElement());
        partnerResponse.setEmail(partnerDto.getEmail());
        partnerResponse.setFax(partnerDto.getFax());
        partnerResponse.setManagerName(partnerDto.getManagerName());
        partnerResponse.setManagerNumber(partnerDto.getManagerNumber());
        partnerResponse.setNote(partnerDto.getNote());
        partnerResponse.setPhone(partnerDto.getPhone());
        partnerResponse.setVatNumber(partnerDto.getVatNumber());
        partnerResponse.setTeacherList(teacherPartnerAdapter.adptTeacherPartnerToTeacherPartnerResponse(partnerDto.getTeacherList()));
        partnerResponse.setAddressList(addressPartnerAdapter.adptAddressPartnerToAddressPartnerResponse(partnerDto.getAddressList()));

        return partnerResponse;
    }

    public List<PartnerResponseV1> adptPartnerDtoToPartnerResponse(List<PartnerDto> partnerDtoList){

        if(partnerDtoList == null)
            return null;

        List<PartnerResponseV1> partnerResponseList = new LinkedList<>();

        for (PartnerDto att : partnerDtoList){

            partnerResponseList.add(this.adptPartnerDtoToPartnerResponse(att));
        }

        return partnerResponseList;
    }

    public PaginationResponseV1<PartnerResponseV1> adpPartnerPaginationToPartnerPaginationResposne(Pagination partnerPagination){

        PaginationResponseV1<PartnerResponseV1> learnerPaginationResponse = new PaginationResponseV1<>();

        learnerPaginationResponse.setItems(this.adptPartnerToPartnerResponse(partnerPagination.getItems()));
        learnerPaginationResponse.setStats(partnerPagination.getStats());

        return learnerPaginationResponse;
    }

    @Component
    public class TeacherPartnerAdapter{

        @Autowired
        private TeacherAdapter teacherAdapter;

        public TeacherPartnerResponseV1 adptTeacherPartnerToTeacherPartnerResponse(TeacherPartner teacherPartner){

            if(teacherPartner == null)
                return null;

            TeacherPartnerResponseV1 teacherPartnerResponse = new TeacherPartnerResponseV1();

            teacherPartnerResponse.setTeacher(teacherAdapter.adptTeacherDtoToTeacherResponse(teacherPartner.getTeacher()));
            teacherPartnerResponse.setStatus(teacherPartner.getStatus());

            return teacherPartnerResponse;
        }

        public List<TeacherPartnerResponseV1> adptTeacherPartnerToTeacherPartnerResponse(List<TeacherPartner> teacherPartnerList){

            if(teacherPartnerList == null)
                return null;

            List<TeacherPartnerResponseV1> teacherPartnerResponseList = new LinkedList<>();

            for (TeacherPartner att : teacherPartnerList){
                teacherPartnerResponseList.add(this.adptTeacherPartnerToTeacherPartnerResponse(att));
            }

            return teacherPartnerResponseList;
        }

        public TeacherPartner adptTeacherPartnerRequestToTeacherPartner(TeacherPartnerRequestV1 teacherPartnerRequest){

            if(teacherPartnerRequest == null)
                return null;

            TeacherPartner teacherPartner = new TeacherPartner();

            teacherPartner.setTeacher(teacherAdapter.adptTeacherRequestDtoToTeacherDto(teacherPartnerRequest.getTeacher()));
            teacherPartner.setStatus(teacherPartnerRequest.getStatus());

            return teacherPartner;
        }

        public List<TeacherPartner> adptTeacherPartnerRequestToTeacherPartner(List<TeacherPartnerRequestV1> teacherPartnerRequestList){

            if(teacherPartnerRequestList == null)
                return null;

            List<TeacherPartner> teacherPartnerList = new LinkedList<>();

            for (TeacherPartnerRequestV1 att : teacherPartnerRequestList){
                teacherPartnerList.add(this.adptTeacherPartnerRequestToTeacherPartner(att));
            }
            return teacherPartnerList;
        }
    }

    @Component
    public class AddressPartnerAdapter{

        @Autowired
        private AddressAdapter addressAdapter;

        public AddressPartnerResponseV1 adptAddressPartnerToAddressPartnerResponse(AddressPartner addressPartner){

            if(addressPartner == null)
                return null;

            AddressPartnerResponseV1 addressPartnerResponse =  new AddressPartnerResponseV1();

            addressPartnerResponse.setAddress(addressAdapter.adptAddressDtoToAddressResponse(addressPartner.getAddress()));
            addressPartnerResponse.setLegalAddress(addressPartner.getLegalAddress());

            return addressPartnerResponse;
        }

        public List<AddressPartnerResponseV1> adptAddressPartnerToAddressPartnerResponse(List<AddressPartner> addressPartnerList){

            if(addressPartnerList == null)
                return null;

            List<AddressPartnerResponseV1> addressPartnerResponseList = new LinkedList<>();
            for(AddressPartner att : addressPartnerList){

                addressPartnerResponseList.add(this.adptAddressPartnerToAddressPartnerResponse(att));
            }

            return addressPartnerResponseList;
        }

        public AddressPartner adptAddressPartnerRequestToAddressPartner(AddressPartnerRequestV1 addressPartnerRequest){

            if(addressPartnerRequest == null)
                return null;

            AddressPartner addressPartner = new AddressPartner();

            addressPartner.setAddress(addressAdapter.adptAddressRequestToAddressDto(addressPartnerRequest.getAddress()));
            addressPartner.setLegalAddress(addressPartnerRequest.getLegalAddress());

            return addressPartner;
        }

        public List<AddressPartner> adptAddressPartnerRequestToAddressPartner(List<AddressPartnerRequestV1> addressPartnerRequestList){

            if(addressPartnerRequestList == null)
                return null;

            List<AddressPartner> addressPartnerList = new LinkedList<>();
            for(AddressPartnerRequestV1 att : addressPartnerRequestList){

                addressPartnerList.add(this.adptAddressPartnerRequestToAddressPartner(att));
            }

            return addressPartnerList;
        }

    }

}
