package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.AlertAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.controller.payload.response.AlertCourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.AlertCourseEntity;
import it.tcgroup.vilear.coursemanager.entity.AlertSettingsEntity;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.enumerated.AlertPriorityEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.PlacementCourse;
import it.tcgroup.vilear.coursemanager.repository.AlertCourseEMRepository;
import it.tcgroup.vilear.coursemanager.repository.AlertCourseRepository;
import it.tcgroup.vilear.coursemanager.repository.AlertSettingsRepository;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.AlertService;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertCourseRepository alertCourseRepository;

    @Autowired
    private AlertSettingsRepository alertSettingsRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AlertAdapter alertAdapter;

    @Autowired
    private AlertCourseEMRepository alertCourseEMRepository;

    @Autowired
    private DateUtil dateUtil;

    @Override
    public void postAlertCourse(UUID idCourse){

        Optional<CourseEntity> optCourse = courseRepository.findById(idCourse);

        if(!optCourse.isPresent())
            throw new NotFoundException("Course not found!");

        CourseEntity course = optCourse.get();

        List<AlertSettingsEntity> alertSettingsList = alertSettingsRepository.findAll();

        List<AlertCourseEntity> alertCourseListToBeSaved = new LinkedList<>();

        for (AlertSettingsEntity att : alertSettingsList){

            AlertCourseEntity alertCourse = new AlertCourseEntity();
            alertCourse.setIdAlert(att.getId());
            alertCourse.setIdCourse(course.getId());
            alertCourse.setCourseName(course.getCourseTitle());
            alertCourse.setCourseCode(course.getCourseCode());
            alertCourse.setAlertDescription(att.getAlertDescription());
            alertCourse.setActive(false);
            alertCourse.setStatus(false);
            alertCourse.setPriority(AlertPriorityEnum.BASSA);

            switch (att.getType()){

                case "INIZIO_CORSO":
                case "INVIO_INAIL":
                case "INSERIMENTO_CODICE_FISCALE":

                    if(course.getCourseStartDate() != null)
                        alertCourse.setDateStartAlert(course.getCourseStartDate());

                    break;

                case "DATA_CONSEGNA_PROGETTO":
                    //In attesa della definizione della fatturazione
                    break;

                case "POSSIBILITA_RENDICONTAZIONE":

                    //controllo la data invio progetto (invio progetto è uguale a consegna progetto?)
                    if(course.getSendedProjectDate() != null) {
                        alertCourse.setDateStartAlert(course.getSendedProjectDate());
                    }
                    break;

                case "RENDICONTAZIONE":
                case "SCADENZA_PLACEMENT":
                case "INVIO_PLACEMENT":

                    if(course.getCourseEndDate() != null){
                        alertCourse.setDateStartAlert(course.getCourseEndDate());
                    }
                    break;

                case "RENDICONTAZIONE_ELETTRONICA":
                    //In attesa del comportamento da assumere in questo caso
                    break;

                case "RENDICONTAZIONE_CARTACEA":

                    if(course.getSendedEletronicReportingDate() != null){
                        alertCourse.setDateStartAlert(course.getSendedEletronicReportingDate());
                    }
                    break;

                case "INVIO_PLACEMENT_ELETTRONICO":
                    //In attea del comportamento da assumere
                    break;

                case "INVIO_PLACEMENT_CARTACEO":
                    //Da capire a quale data fare riferimento
                    break;

                default:
                    break;
            }

            if(alertCourse.getDateStartAlert() != null){
                alertCourse.setDateEndAlert(dateUtil.addDays(alertCourse.getDateStartAlert(), att.getPeriodicy()));
                alertCourseListToBeSaved.add(alertCourse);
            }

            if(!alertCourseListToBeSaved.isEmpty()){
                alertCourseRepository.saveAll(alertCourseListToBeSaved);
            }
        }
    }

    @Override
    public void patchAlertCourseStatus(UUID idAlertCourse){

        Optional<AlertCourseEntity> optAlertCourse = alertCourseRepository.findById(idAlertCourse);

        if(!optAlertCourse.isPresent())
            throw new NotFoundException("Alert not found!");

        AlertCourseEntity alertCourse = optAlertCourse.get();
        alertCourse.setStatus(true);
        alertCourse.setPriority(AlertPriorityEnum.NESSUNA);

        alertCourseRepository.save(alertCourse);
    }

    //@Override
    //@Scheduled(cron = "0 43 10 * * *")
    @Scheduled(cron = "0 0 0 * * *")
    public void cronActiveAlert(){

        List<AlertCourseEntity> courseEntityList = alertCourseRepository.getAllAlertToBeActive();
        List<AlertCourseEntity> courseEntityListToBeUpdate = new LinkedList<>();

        LocalDate toDay = dateUtil.getNowLocalDate();

        for(AlertCourseEntity att : courseEntityList){

            LocalDate alertStartDate = dateUtil.convertUTCDateToLocalDate(att.getDateStartAlert());

            if(alertStartDate.compareTo(toDay) == 0){
                att.setActive(true);
                courseEntityListToBeUpdate.add(att);
            }
        }

        if(!courseEntityListToBeUpdate.isEmpty()){
            alertCourseRepository.saveAll(courseEntityListToBeUpdate);
        }
    }

    //@Override
    //@Scheduled(cron = "0 43 10 * * *")
    @Scheduled(cron = "0 1 0 * * *")
    public void cronPriorityAlert(){

        List<AlertCourseEntity> courseEntityList = alertCourseRepository.getAllAlertToBeChangedPriority();
        List<AlertCourseEntity> courseEntityListToBeUpdate = new LinkedList<>();

        LocalDate toDay = dateUtil.getNowLocalDate();

        for(AlertCourseEntity att : courseEntityList){

            LocalDate alertEndDate = dateUtil.convertUTCDateToLocalDate(att.getDateEndAlert());

            if(alertEndDate.compareTo(toDay) == 0){
                att.setPriority(AlertPriorityEnum.ALTA);
                courseEntityListToBeUpdate.add(att);
            }
        }

        if(!courseEntityListToBeUpdate.isEmpty()){
            alertCourseRepository.saveAll(courseEntityListToBeUpdate);
        }

    }

    @Override
    public PaginationResponseV1<AlertCourseResponseV1> getAlertsPagination(int page, int pageSize, String courseName, String courseCode, String startDate, Boolean status, Boolean active){

        Pagination<AlertCourseEntity> alertsCoursePagination = new Pagination<>();

        List<AlertCourseEntity> alertCourseList = alertCourseEMRepository.getAlertCourseForPagination(courseName, courseCode, startDate, status, active);

        alertsCoursePagination.setStats(new PaginationResponseV1.InfoPagination(alertCourseList.size(), page, pageSize));

        int start = alertsCoursePagination.getStats().getStartPage();
        int count = 0;
        List<AlertCourseEntity> alertCourseForPagination = new LinkedList<>();

        while (count < alertsCoursePagination.getStats().getPageSize() && ((start - 1) + count) < alertCourseList.size()) {
            alertCourseForPagination.add((alertCourseList.get((start - 1) + count)));
            count++;
        }

        alertsCoursePagination.setItems(alertCourseForPagination);

        return alertAdapter.adpAlertCoursePaginationToAlertCoursePaginationResposne(alertsCoursePagination);






    }

}
