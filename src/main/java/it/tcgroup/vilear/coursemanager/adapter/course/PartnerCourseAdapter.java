package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.PartnerAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.PartnerCourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.PartnerCourseResponseV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PartnerCourse;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PartnerCourse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PartnerCourseAdapter {

    @Autowired
    private PartnerAdapter partnerAdapter;

    @Autowired
    private  SubSupplierAdapter subSupplierAdapter;

    public PartnerCourse adptPartnerCourseRequestToPartnerCourse(PartnerCourseRequestV1 partnerCourseRequest){

        if(partnerCourseRequest == null)
            return null;

        PartnerCourse partnerCourse = new PartnerCourse();

        partnerCourse.setAmountFirstPaymen(partnerCourseRequest.getAmountFirstPaymen());
        partnerCourse.setAmountSecondPaymen(partnerCourseRequest.getAmountSecondPaymen());
        partnerCourse.setAmountThirdPaymen(partnerCourseRequest.getAmountThirdPaymen());
        partnerCourse.setFirstPaymentDate(partnerCourseRequest.getFirstPaymentDate());
        partnerCourse.setSecondPaymentDate(partnerCourseRequest.getSecondPaymentDate());
        partnerCourse.setThirdPaymentDate(partnerCourseRequest.getThirdPaymentDate());
        partnerCourse.setServicesCosts(partnerCourseRequest.getServicesCosts());
        partnerCourse.setSubSupplierList(subSupplierAdapter.adptSubSupplierRequestToSubSupplier(partnerCourseRequest.getSubSupplierList()));
        partnerCourse.setSupplier(partnerAdapter.adptPartnerRequestToPartnerDto(partnerCourseRequest.getSupplier()));
        partnerCourse.setSupplierService(partnerCourseRequest.getSupplierService());

        return  partnerCourse;
    }

    public List<PartnerCourse> adptPartnerCourseRequestToPartnerCourse(List<PartnerCourseRequestV1> partnerCourseRequestList){

        if(partnerCourseRequestList == null)
            return  null;

        List<PartnerCourse> partnerCourseList = new LinkedList<>();

        for (PartnerCourseRequestV1 att : partnerCourseRequestList){
            partnerCourseList.add(this.adptPartnerCourseRequestToPartnerCourse(att));
        }

        return partnerCourseList;
    }

    public PartnerCourseResponseV1  adptPartnerCourseToPartnerCourseResponse(PartnerCourse partnerCourse){

        if(partnerCourse == null)
            return null;

        PartnerCourseResponseV1 partnerCourseResponse = new PartnerCourseResponseV1();

        partnerCourseResponse.setAmountFirstPaymen(partnerCourse.getAmountFirstPaymen());
        partnerCourseResponse.setAmountSecondPaymen(partnerCourse.getAmountSecondPaymen());
        partnerCourseResponse.setAmountThirdPaymen(partnerCourse.getAmountThirdPaymen());
        partnerCourseResponse.setFirstPaymentDate(partnerCourse.getFirstPaymentDate());
        partnerCourseResponse.setSecondPaymentDate(partnerCourse.getSecondPaymentDate());
        partnerCourseResponse.setThirdPaymentDate(partnerCourse.getThirdPaymentDate());
        partnerCourseResponse.setServicesCosts(partnerCourse.getServicesCosts());
        partnerCourseResponse.setSubSupplierList(subSupplierAdapter.adptSubSupplierToSubSupplierResponse(partnerCourse.getSubSupplierList()));
        partnerCourseResponse.setSupplier(partnerAdapter.adptPartnerDtoToPartnerResponse(partnerCourse.getSupplier()));
        partnerCourseResponse.setSupplierService(partnerCourse.getSupplierService());

        return  partnerCourseResponse;

    }

    public List<PartnerCourseResponseV1> adptPartnerCourseToPartnerCourseResponse(List<PartnerCourse> partnerCourseList){

        if(partnerCourseList == null)
            return  null;

        List<PartnerCourseResponseV1> partnerCourseResponseList = new LinkedList<>();

        for (PartnerCourse att : partnerCourseList){
            partnerCourseResponseList.add(this.adptPartnerCourseToPartnerCourseResponse(att));
        }

        return partnerCourseResponseList;
    }

    @Component
    public class SubSupplierAdapter{

        @Autowired
        private PartnerAdapter partnerAdapter;

        public SubSupplier adptSubSupplierRequestToSubSupplier(SubSupplierRequestV1 subSupplierRequest){

            if(subSupplierRequest == null)
                return null;

            SubSupplier subSupplier = new SubSupplier();

            subSupplier.setSubSupplier(partnerAdapter.adptPartnerRequestToPartnerDto(subSupplierRequest.getSubSupplier()));
            subSupplier.setSubSupplierService(subSupplierRequest.getSubSupplierService());

            return subSupplier;
        }

        public List<SubSupplier> adptSubSupplierRequestToSubSupplier(List<SubSupplierRequestV1> subSupplierRequestList){

            if(subSupplierRequestList == null)
                return null;

            List<SubSupplier> subSupplierList = new LinkedList<>();

            for (SubSupplierRequestV1 att : subSupplierRequestList){
                subSupplierList.add(this.adptSubSupplierRequestToSubSupplier(att));
            }

            return subSupplierList;
        }

        public SubSupplierResponseV1 adptSubSupplierToSubSupplierResponse(SubSupplier subSupplier){

            if(subSupplier == null)
                return null;

            SubSupplierResponseV1 subSupplierResponse = new SubSupplierResponseV1();

            subSupplierResponse.setSubSupplier(partnerAdapter.adptPartnerDtoToPartnerResponse(subSupplier.getSubSupplier()));
            subSupplierResponse.setSubSupplierService(subSupplier.getSubSupplierService());

            return subSupplierResponse;
        }

        public List<SubSupplierResponseV1> adptSubSupplierToSubSupplierResponse(List<SubSupplier> subSupplierRequestList){

            if(subSupplierRequestList == null)
                return null;

            List<SubSupplierResponseV1> subSupplierResponseList = new LinkedList<>();

            for (SubSupplier att : subSupplierRequestList){
                subSupplierResponseList.add(this.adptSubSupplierToSubSupplierResponse(att));
            }

            return subSupplierResponseList;
        }
    }



}
