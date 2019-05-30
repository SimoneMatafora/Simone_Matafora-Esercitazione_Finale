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
    
    @Autowired
    private  SupplyServiceAdapter supplyServiceAdapter;

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
        partnerCourse.setSupplyServices(supplyServiceAdapter.adptSupplierServiceRequestToSupplierService(partnerCourseRequest.getSupplyServices()));
        partnerCourse.setSubSupplierList(subSupplierAdapter.adptSubSupplierRequestToSubSupplier(partnerCourseRequest.getSubSupplierList()));
        partnerCourse.setSupplier(partnerAdapter.adptPartnerRequestToPartnerDto(partnerCourseRequest.getSupplier()));

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
        partnerCourseResponse.setSupplyServices(supplyServiceAdapter.adptSupplierServiceToSupplierServiceResponse(partnerCourse.getSupplyServices()));
        partnerCourseResponse.setSubSupplierList(subSupplierAdapter.adptSubSupplierToSubSupplierResponse(partnerCourse.getSubSupplierList()));
        partnerCourseResponse.setSupplier(partnerAdapter.adptPartnerDtoToPartnerResponse(partnerCourse.getSupplier()));

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
    public class SupplyServiceAdapter{

        public SupplierService adptSupplierServiceRequestToSupplierService(SupplierServiceRequestV1 supplierServiceRequest){

            if(supplierServiceRequest == null)
                return null;

            SupplierService supplierService = new SupplierService();

            supplierService.setSupplierService(supplierServiceRequest.getSupplierService());
            supplierService.setServiceCost(supplierServiceRequest.getServiceCost());

            return supplierService;
        }

        public List<SupplierService> adptSupplierServiceRequestToSupplierService(List<SupplierServiceRequestV1> supplierServiceRequestList){

            if(supplierServiceRequestList == null)
                return null;

            List<SupplierService> supplierServiceList = new LinkedList<>();

            for (SupplierServiceRequestV1 att : supplierServiceRequestList){
                supplierServiceList.add(this.adptSupplierServiceRequestToSupplierService(att));
            }

            return supplierServiceList;
        }

        public SupplierServiceResponseV1 adptSupplierServiceToSupplierServiceResponse(SupplierService supplierService){

            if(supplierService == null)
                return null;

            SupplierServiceResponseV1 supplierServiceResponse = new SupplierServiceResponseV1();

            supplierServiceResponse.setServiceCost(supplierService.getServiceCost());
            supplierServiceResponse.setSupplierService(supplierService.getSupplierService());

            return supplierServiceResponse;
        }

        public List<SupplierServiceResponseV1> adptSupplierServiceToSupplierServiceResponse(List<SupplierService> supplierServiceRequestList){

            if(supplierServiceRequestList == null)
                return null;

            List<SupplierServiceResponseV1> supplierServiceResponseList = new LinkedList<>();

            for (SupplierService att : supplierServiceRequestList){
                supplierServiceResponseList.add(this.adptSupplierServiceToSupplierServiceResponse(att));
            }

            return supplierServiceResponseList;
        }
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
