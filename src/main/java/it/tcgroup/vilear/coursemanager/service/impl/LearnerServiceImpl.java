package it.tcgroup.vilear.coursemanager.service.impl;


import it.tcgroup.vilear.coursemanager.adapter.AddressAdapter;
import it.tcgroup.vilear.coursemanager.adapter.LearnerAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.pagination.InfoPagination;
import it.tcgroup.vilear.coursemanager.pagination.Pagination;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.repository.LearnerEMRepository;
import it.tcgroup.vilear.coursemanager.repository.LearnerRepository;
import it.tcgroup.vilear.coursemanager.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.*;


@Transactional
@Service

public class LearnerServiceImpl implements LearnerService {
    @Autowired
    private LearnerAdapter learnerAdapter;

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private LearnerEMRepository learnerEMRepository;

    @Autowired
    private AddressAdapter addressAdapter;

    @Override
    public IdResponseV1 insertLearner(LearnerRequestV1 learnerInsertRequest){

        LearnerEntity learner = learnerAdapter.adptLearnerRequestToLearner(learnerInsertRequest);

        learnerRepository.save(learner);

        return learnerAdapter.adptLearnerIdToLearnerIdResponse(learner);

    }

    @Override
    public LearnerResponseV1 getLearner(UUID idLearner){

        Optional<LearnerEntity> learner = learnerRepository.findById(idLearner);
        if(!learner.isPresent()){
            throw new NotFoundException("Learner with id " + idLearner+ " not found");
        }

        return learnerAdapter.adptLearnerToLearnerResponse(learner.get());
    }

    @Override
    public List<LearnerResponseV1> getAllLearner() {

        return learnerAdapter.adptLearnerToLearnerResponse(learnerRepository.findAll());

    }

    @Override
    public void deleteLeraner(UUID idLearner) {
        Optional<LearnerEntity> learnerToDelete=learnerRepository.findById(idLearner);
        if(!learnerToDelete.isPresent()){
            throw new NotFoundException("Learner with id " + idLearner+ " not found");
        }
        learnerRepository.deleteById(idLearner);
    }

    @Override
    public LearnerResponseV1 updateLearner(LearnerRequestV1 learnerUpdateRequest, UUID idLearner) {

        Optional<LearnerEntity> optLearner = learnerRepository.findById(idLearner);
        if(!optLearner.isPresent()){
            throw new NotFoundException("Learner with id " + idLearner + " not found");
        }
        LearnerEntity learner = optLearner.get();

        LearnerEntity learnerUpdate = learnerAdapter.adptLearnerRequestToLearner(learnerUpdateRequest);

        learner.setName(learnerUpdate.getName());
        learner.setSurname(learnerUpdate.getSurname());
        learner.setFiscalCode(learnerUpdate.getFiscalCode());
        learner.setDateOfBirth(learnerUpdate.getDateOfBirth());
        learner.setBirthPlace(learnerUpdate.getBirthPlace());
        learner.setPhone(learnerUpdate.getPhone());
        learner.setEmail(learnerUpdate.getEmail());
        learner.setDegreeOfStudies(learnerUpdate.getDegreeOfStudies());
        learner.setCourseOfStudy(learnerUpdate.getCourseOfStudy());
        learner.setNote(learnerUpdate.getNote());
        learner.setResidentialAddress(learnerUpdate.getResidentialAddress());
        learner.setDomicileAddress(learnerUpdate.getDomicileAddress());
        learner.setDomicileEqualsResidential(learnerUpdate.getDomicileEqualsResidential());

        learnerRepository.save(learner);

        return learnerAdapter.adptLearnerToLearnerResponse(learner);

    }

    @Override
    public LearnerResponseV1 patchLearner(LearnerRequestV1 learnerUpdateRequest, UUID idLearner) {

        Optional<LearnerEntity> optLearner = learnerRepository.findById(idLearner);
        if(!optLearner.isPresent()){
            throw new NotFoundException("Learner with id " + idLearner + " not found");
        }
        LearnerEntity learner = optLearner.get();


        LearnerEntity learnerPatch = learnerAdapter.adptLearnerRequestToLearner(learnerUpdateRequest);

        Address address;
        Address addressPatch;

        if(learnerPatch.getName()!=null){
            learner.setName(learnerPatch.getName());
        }
        if(learnerPatch.getSurname()!=null){
            learner.setSurname(learnerPatch.getSurname());
        }
        if(learnerPatch.getFiscalCode()!=null){
            learner.setFiscalCode(learnerPatch.getFiscalCode());
        }
        if(learnerPatch.getDateOfBirth()!=null){
            learner.setDateOfBirth(learnerPatch.getDateOfBirth());
        }
        if(learnerPatch.getBirthPlace()!=null){
            learner.setBirthPlace(learnerPatch.getBirthPlace());
        }
        if(learnerPatch.getPhone()!=null){
            learner.setPhone(learnerPatch.getPhone());
        }
        if(learnerPatch.getEmail()!=null){
            learner.setEmail(learnerPatch.getEmail());
        }
        if(learnerPatch.getDegreeOfStudies()!=null){
            learner.setDegreeOfStudies(learnerPatch.getDegreeOfStudies());
        }
        if(learnerPatch.getCourseOfStudy()!=null){
            learner.setCourseOfStudy(learnerPatch.getCourseOfStudy());
        }
        if(learnerPatch.getNote()!=null){
            learner.setNote(learnerPatch.getNote());
        }

        /*JSON address manage*/

        if(learnerPatch.getResidentialAddress()!=null){

            address=learner.getResidentialAddress();
            addressPatch=learnerPatch.getResidentialAddress();

            if(addressPatch.getNation()!=null){
                address.setNation(addressPatch.getNation());
            }
            if(addressPatch.getRegion()!=null){
                address.setRegion(addressPatch.getRegion());
            }
            if(addressPatch.getProvince()!=null){
                address.setProvince(addressPatch.getProvince());
            }
            if(addressPatch.getCity()!=null){
                address.setCity(addressPatch.getCity());
            }
            if(addressPatch.getStreet()!=null){
                address.setStreet(addressPatch.getStreet());
            }
            if(addressPatch.getNumber()!=null){
                address.setNumber(addressPatch.getNumber());
            }
            if(addressPatch.getZipCode()!=null){
                address.setZipCode(addressPatch.getZipCode());
            }
        }

        if(learnerPatch.getDomicileAddress()!=null){

            address=learner.getDomicileAddress();
            addressPatch=learnerPatch.getDomicileAddress();

            if(addressPatch.getNation()!=null){
                address.setNation(addressPatch.getNation());
            }
            if(addressPatch.getRegion()!=null){
                address.setRegion(addressPatch.getRegion());
            }
            if(addressPatch.getProvince()!=null){
                address.setProvince(addressPatch.getProvince());
            }
            if(addressPatch.getCity()!=null){
                address.setCity(addressPatch.getCity());
            }
            if(addressPatch.getStreet()!=null){
                address.setStreet(addressPatch.getStreet());
            }
            if(addressPatch.getNumber()!=null){
                address.setNumber(addressPatch.getNumber());
            }
            if(addressPatch.getZipCode()!=null){
                address.setZipCode(addressPatch.getZipCode());
            }
        }

        /*FINE JSON MANAGING*/

        if(learnerPatch.getDomicileEqualsResidential()!=null){
            learner.setDomicileEqualsResidential(learnerPatch.getDomicileEqualsResidential());
        }

        learnerRepository.save(learner);

        return learnerAdapter.adptLearnerToLearnerResponse(learner);

    }


        @Override
        public PaginationResponseV1<LearnerResponseV1> getLearnersPagination(int page, int pageSize, String name, String surname,
                                                                             String fiscalCode, String dateOfBirth, String birthPlace, String email, String phone,
                                                                             String note, String degreeOfStudies, String courseOfStudy, String residentialNation,
                                                                             String residentialRegion, String residentialProvince, String residentialCity,
                                                                             String residentialStreet, String residentialNumber, String domicileNation,
                                                                             String domicileRegion, String domicileProvince, String domicileCity,
                                                                             String domicileStreet, String domicileNumber, Boolean domicileEqualsResidential) throws ParseException {

            Pagination<LearnerEntity> learnersPagination = new Pagination<>();

            List<LearnerEntity> learnerList = learnerEMRepository.getFilialiForPagination( page, pageSize, name, surname,
                     fiscalCode, dateOfBirth,birthPlace, email, phone,
                     note, degreeOfStudies, courseOfStudy, residentialNation,
                     residentialRegion, residentialProvince, residentialCity,
                     residentialStreet, residentialNumber, domicileNation,
                     domicileRegion, domicileProvince, domicileCity,
                     domicileStreet, domicileNumber, domicileEqualsResidential);

            learnersPagination.setStats(new InfoPagination(learnerList.size(), page, pageSize));

            int start = learnersPagination.getStats().getStartPage();
            int count = 0;
            List<LearnerEntity> filialiForPagination = new LinkedList<>();

            while (count < learnersPagination.getStats().getPageSize() && ((start - 1) + count) < learnerList.size()) {
                filialiForPagination.add((learnerList.get((start - 1) + count)));
                count++;
            }

            learnersPagination.setItems(filialiForPagination);

            return learnerAdapter.adpLearnerPaginationToLearnerPaginationResposne(learnersPagination);
        }


}

