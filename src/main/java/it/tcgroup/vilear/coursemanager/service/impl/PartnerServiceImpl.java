package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.PartnerAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.PartnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PartnerResponseV1;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import it.tcgroup.vilear.coursemanager.repository.PartnerEMRepository;
import it.tcgroup.vilear.coursemanager.repository.PartnerRepository;
import it.tcgroup.vilear.coursemanager.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class PartnerServiceImpl implements PartnerService {


    @Autowired
    private PartnerAdapter partnerAdapter;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PartnerEMRepository partnerEMRepository;

    @Override
    public IdResponseV1 insertPartner(PartnerRequestV1 partnerInsertRequest) {

        PartnerEntity partner = partnerAdapter.adptPartnerRequestToPartner(partnerInsertRequest);
        partnerRepository.save(partner);

        return partnerAdapter.adptPartnerIdToPartnerIdResponse(partner);
    }

    @Override
    public PartnerResponseV1 getPartner(UUID idPartner) {

        Optional<PartnerEntity> partner = partnerRepository.findById(idPartner);
        if(!partner.isPresent()){
            throw new NotFoundException("Partner with id " + idPartner + " not found");
        }

        return partnerAdapter.adptPartnerToPartnerResponse(partner.get());
    }

    @Override
    public PartnerResponseV1 updatePartner(PartnerRequestV1 partnerUpdateRequest, UUID idPartner) {

        Optional<PartnerEntity> optPartner = partnerRepository.findById(idPartner);
        if(!optPartner .isPresent()){
            throw new NotFoundException("Partner with id " + idPartner + " not found");
        }

        PartnerEntity partner = optPartner.get();

        PartnerEntity partnerUpdate = partnerAdapter.adptPartnerRequestToPartner(partnerUpdateRequest);

        partner.setAccreditedFt(partnerUpdate.getAccreditedFt());
        partner.setAccreditedFtCode(partnerUpdate.getAccreditedFtCode());
        partner.setBusinessName(partnerUpdate.getBusinessName());
        partner.setCompany(partnerUpdate.getCompany());
        partner.setCostElement(partnerUpdate.getCostElement());
        partner.setEmail(partnerUpdate.getEmail());
        partner.setFax(partnerUpdate.getFax());
        partner.setManagerName(partnerUpdate.getManagerName());
        partner.setManagerNumber(partnerUpdate.getManagerNumber());
        partner.setNote(partnerUpdate.getNote());
        partner.setPhone(partnerUpdate.getPhone());
        partner.setVatNumber(partnerUpdate.getVatNumber());
        partner.setTeacherList(partnerUpdate.getTeacherList());
        partner.setAddressList(partnerUpdate.getAddressList());

        partnerRepository.save(partner);

        return partnerAdapter.adptPartnerToPartnerResponse(partner);
    }

    @Override
    public PartnerResponseV1 patchPartner(PartnerRequestV1 partnerPatchRequest, UUID idPartner) {

        Optional<PartnerEntity> optPartner = partnerRepository.findById(idPartner);
        if(!optPartner .isPresent()){
            throw new NotFoundException("Partner with id " + idPartner + " not found");
        }

        PartnerEntity partner = optPartner.get();

        PartnerEntity partnerUpdate = partnerAdapter.adptPartnerRequestToPartner(partnerPatchRequest);

        if(partnerUpdate.getAccreditedFt() != null)
            partner.setAccreditedFt(partnerUpdate.getAccreditedFt());
        if(partnerUpdate.getAccreditedFtCode() != null)
            partner.setAccreditedFtCode(partnerUpdate.getAccreditedFtCode());
        if(partnerUpdate.getAddressList() != null)
            partner.setAddressList(partnerUpdate.getAddressList());
        if(partnerUpdate.getBusinessName() != null)
            partner.setBusinessName(partnerUpdate.getBusinessName());
        if(partnerUpdate.getCompany() != null)
            partner.setCompany(partnerUpdate.getCompany());
        if(partnerUpdate.getCostElement() != null)
            partner.setCostElement(partnerUpdate.getCostElement());
        if(partnerUpdate.getEmail() != null)
            partner.setEmail(partnerUpdate.getEmail());
        if(partnerUpdate.getFax() != null)
            partner.setFax(partnerUpdate.getFax());
        if(partnerUpdate.getManagerName() != null)
            partner.setManagerName(partnerUpdate.getManagerName());
        if(partnerUpdate.getManagerNumber() != null)
            partner.setManagerNumber(partnerUpdate.getManagerNumber());
        if(partnerUpdate.getNote() != null)
            partner.setNote(partnerUpdate.getNote());
        if(partnerUpdate.getPhone() != null)
            partner.setPhone(partnerUpdate.getPhone());
        if(partnerUpdate.getTeacherList() != null)
            partner.setTeacherList(partnerUpdate.getTeacherList());
        if(partnerUpdate.getVatNumber() != null)
            partner.setVatNumber(partnerUpdate.getVatNumber());

        partnerRepository.save(partner);

        return partnerAdapter.adptPartnerToPartnerResponse(partner);

    }

    @Override
    public PaginationResponseV1<PartnerResponseV1> getPartnersPagination(int page, int pageSize, String businessName, Boolean company, String managerName, String accreditedFt, String teacherName, String teacherSurname,
                                                                         String teacherProfessionalArea, String teacherPublicEmployee, String citta, String comune, String cap) {

        Pagination<PartnerEntity> partnerPagination = new Pagination<>();

        List<PartnerEntity> partnerList = partnerEMRepository.getPartnersForPagination(businessName, company, managerName, accreditedFt, teacherName, teacherSurname,
                teacherProfessionalArea, teacherPublicEmployee, citta, comune, cap);

        partnerPagination.setStats(new PaginationResponseV1.InfoPagination(partnerList.size(), page, pageSize));

        int start = partnerPagination.getStats().getStartPage();
        int count = 0;
        List<PartnerEntity> learnerForPagination = new LinkedList<>();

        while (count < partnerPagination.getStats().getPageSize() && ((start - 1) + count) < partnerList.size()) {
            learnerForPagination.add((partnerList.get((start - 1) + count)));
            count++;
        }

        partnerPagination.setItems(learnerForPagination);

        return partnerAdapter.adpPartnerPaginationToPartnerPaginationResposne(partnerPagination);
    }

    @Override
    public void deletePartner(UUID idPartner) {
        partnerRepository.deleteById(idPartner);
    }
}
