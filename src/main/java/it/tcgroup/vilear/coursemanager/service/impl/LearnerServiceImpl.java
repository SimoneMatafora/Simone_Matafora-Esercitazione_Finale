package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.AttachmentAdapter;
import it.tcgroup.vilear.coursemanager.adapter.LearnerAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.LearnerRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.request.UploadRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.LearnerResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.UploadResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.RecipientManagmentCourse;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.repository.LearnerEMRepository;
import it.tcgroup.vilear.coursemanager.repository.LearnerRepository;
import it.tcgroup.vilear.coursemanager.service.AddressService;
import it.tcgroup.vilear.coursemanager.service.FilemanagerService;
import it.tcgroup.vilear.coursemanager.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
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
    private FilemanagerService filemanagerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AttachmentAdapter attachmentAdapter;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public IdResponseV1 insertLearner(LearnerRequestV1 learnerInsertRequest) {

        UUID idLearner = UUID.randomUUID();
        learnerInsertRequest.setId(idLearner.toString());

        LearnerEntity learner = learnerAdapter.adptLearnerRequestToLearner(learnerInsertRequest);

        learnerRepository.save(learner);

        return learnerAdapter.adptLearnerIdToLearnerIdResponse(learner);
    }

    @Modifying
    @Override
    public LearnerResponseV1 updateLearner(LearnerRequestV1 learnerUpdateRequest, UUID idLearner) {

        Optional<LearnerEntity> optLearner = learnerRepository.findById(idLearner);
        if(!optLearner.isPresent()){
            throw new NotFoundException("Learner with id " + idLearner + " not found");
        }
        LearnerEntity learner = optLearner.get();

        LearnerEntity learnerUpdate = learnerAdapter.adptLearnerRequestToLearner(learnerUpdateRequest);

        learner.setFiscalCode(learnerUpdate.getFiscalCode());
        learner.setSurname(learnerUpdate.getSurname());
        //learner.setCurriculumVitae(learnerUpdate.getCurriculumVitae());
        learner.setDateOfBirth(learnerUpdate.getDateOfBirth());
        learner.setEmail(learnerUpdate.getEmail());
        learner.setBirthPlace(learnerUpdate.getBirthPlace());
        learner.setCourseOfStudy(learnerUpdate.getCourseOfStudy());
        learner.setDegreeOfStudies(learnerUpdate.getDegreeOfStudies());
        learner.setName(learnerUpdate.getName());
        learner.setNote(learnerUpdate.getNote());
        learner.setPhone(learnerUpdate.getPhone());
        learner.setAddress(learnerUpdate.getAddress());

        learnerRepository.save(learner);

        return  learnerAdapter.adptLearnerToLearnerResponse(learner);
    }

    @Override
    public LearnerResponseV1 getLearner(UUID idLearner) {

        Optional<LearnerEntity> learner = learnerRepository.findById(idLearner);
        if(!learner.isPresent()){
            throw new NotFoundException("Learner with id " + idLearner+ " not found");
        }

        return learnerAdapter.adptLearnerToLearnerResponse(learner.get());
    }

    @Modifying
    @Override
    public LearnerResponseV1 patchLearner(LearnerRequestV1 learnerUpdateRequest, UUID idLearner) {

        Optional<LearnerEntity> optLearner = learnerRepository.findById(idLearner);
        if(!optLearner.isPresent()){
            throw new NotFoundException("Learner with id " + idLearner+ " not found");
        }
        LearnerEntity learner = optLearner.get();

        LearnerEntity learnerPatch = learnerAdapter.adptLearnerRequestToLearner(learnerUpdateRequest);


        if( learnerPatch.getFiscalCode() != null)
            learner.setFiscalCode(learnerPatch.getFiscalCode());

        if( learnerPatch.getSurname() != null)
            learner.setSurname(learnerPatch.getSurname());

        /*if( learnerPatch.getCurriculumVitae() != null)
            learner.setCurriculumVitae(learnerPatch.getCurriculumVitae());*/

        if( learnerPatch.getDateOfBirth() != null)
            learner.setDateOfBirth(learnerPatch.getDateOfBirth());

        if( learnerPatch.getEmail() != null)
            learner.setEmail(learnerPatch.getEmail());

        if( learnerPatch.getBirthPlace() != null)
            learner.setBirthPlace(learnerPatch.getBirthPlace());

        if( learnerPatch.getName() != null)
            learner.setName(learnerPatch.getName());

        if( learnerPatch.getNote() != null)
            learner.setNote(learnerPatch.getNote());

        if( learnerPatch.getPhone() != null)
            learner.setPhone(learnerPatch.getPhone());

        if( learnerPatch.getDegreeOfStudies() != null)
            learner.setDegreeOfStudies(learnerPatch.getDegreeOfStudies());

        if( learnerPatch.getCourseOfStudy() != null)
            learner.setCourseOfStudy(learnerPatch.getCourseOfStudy());

        if( learnerPatch.getAddress() != null)
            learner.setAddress(addressService.patchAddress(learner.getAddress(), learnerPatch.getAddress()));

        learnerRepository.save(learner);

        return learnerAdapter.adptLearnerToLearnerResponse(learner);

    }

    @Override
    public PaginationResponseV1<LearnerResponseV1> getLearnersPagination(int page, int pageSize, String username, String name, String surname, String phone, String fiscalCode, String dateOfBirth,
                                                                         String birthPlace, String email, String degreeOfStudies, String courseOfStudy, String city, String region, String province){

        Pagination<LearnerEntity> learnersPagination = new Pagination<>();

        List<LearnerEntity> learnersList = learnerEMRepository.getLearnersForPagination(username, name, surname, phone, fiscalCode, dateOfBirth,
                birthPlace, email, degreeOfStudies, courseOfStudy, city, region, province);

        learnersPagination.setStats(new PaginationResponseV1.InfoPagination(learnersList.size(), page, pageSize));

        int start = learnersPagination.getStats().getStartPage();
        int count = 0;
        List<LearnerEntity> learnerForPagination = new LinkedList<>();

        while (count < learnersPagination.getStats().getPageSize() && ((start - 1) + count) < learnersList.size()) {
            learnerForPagination.add((learnersList.get((start - 1) + count)));
            count++;
        }

        learnersPagination.setItems(learnerForPagination);

        return learnerAdapter.adpLearnerPaginationToLearnerPaginationResposne(learnersPagination);
    }
    @Override
    public void deleteLearner(UUID idLearner){


        Optional<LearnerEntity> optLearner = learnerRepository.findById(idLearner);

        if(optLearner.isPresent())
            learnerRepository.delete(optLearner.get());
        else
            throw new NotFoundException("Learner with uuid: " + idLearner + " isn't present ");
    }

    @Override
    public Attachment addLearnerCurriculum(UploadRequestV1 curriculim, UUID idLearner) throws IOException {

        Optional<LearnerEntity> optLearner = learnerRepository.findById(idLearner);
        if(!optLearner.isPresent()){
            throw new NotFoundException("Learner with id " + idLearner+ " not found");
        }

        LearnerEntity learner = optLearner.get();

        curriculim.setResourceId(idLearner.toString());
        //curriculim.setResourceType("curriculum");

        UploadResponseV1 response = filemanagerService.uploadFile(curriculim);



        if(learner.getAttachments() ==null)
            learner.setAttachments(new ArrayList<>());
        learner.getAttachments().add(attachmentAdapter.adptUploadResponseToAttachment(response));

        learnerRepository.save(learner);

        return attachmentAdapter.adptUploadResponseToAttachment(response);

    }

    @Override
    public void deleteLearnerCurriculum(UUID idLearner, UUID idAttachment) {

        Optional<LearnerEntity> optLearner = learnerRepository.findById(idLearner);
        if (!optLearner.isPresent()) {
            throw new NotFoundException("Learner with id " + idLearner + " not found");
        }

        LearnerEntity learner = optLearner.get();
        if (learner.getAttachments() != null) {
            Attachment candidate = null;
            Iterator<Attachment> iAttachment = learner.getAttachments().iterator();
            while (candidate == null && iAttachment.hasNext()) {
                Attachment now = iAttachment.next();
                if (now.getFileManagerId().equalsIgnoreCase(idAttachment.toString()))
                    candidate = now;
            }
            if (candidate != null) {
                learner.getAttachments().remove(candidate);
                learnerRepository.save(learner);
            } else throw new NotFoundException("Attachment with id " + idAttachment + " not found");

        }
    }

    @Override
    public LearnerResponseV1 updateIdLearner(String email, String userId) {

        if(email == null || email.equalsIgnoreCase(""))
            throw new BadParametersException("email paramiter is empty!");

        learnerRepository.updateLearnerIdByEmail(email, UUID.fromString(userId));

        return null;
    }

    @Override
    public boolean candidateLearnerToCourse(UUID idLearner, UUID idCourse) {

        Optional<LearnerEntity> optLearner = learnerRepository.findById(idLearner);
        if(!optLearner.isPresent())
            throw new NotFoundException("Learner with id " + idLearner+ " not found");

        LearnerEntity learner = optLearner.get();
        System.out.println("sono qui");
        if(learner.getName() != null && learner.getSurname() != null && learner.getDateOfBirth() != null &&
        learner.getBirthPlace() != null && learner.getFiscalCode() != null && learner.getEmail() != null &&
        learner.getPhone() != null && learner.getDegreeOfStudies() != null && learner.getAddress() != null &&
        learner.getAttachments() != null) {
            System.out.println("sono qua");
            Optional<CourseEntity> courseOpt = courseRepository.findById(idCourse);
            if (!courseOpt.isPresent())
                throw new NotFoundException("Course with id " + idCourse + " not found");
            CourseEntity course = courseOpt.get();
            if(course.getRecipientManagment() == null)
                course.setRecipientManagment(new ArrayList<>());
            RecipientManagmentCourse recipientManagmentCourse = new RecipientManagmentCourse();
            recipientManagmentCourse.setLearner(learnerAdapter.adptLearnerToLearnerDto(learner));
            course.getRecipientManagment().add(recipientManagmentCourse);
            courseRepository.save(course);
            return true;
        }
        return false;
    }
}
