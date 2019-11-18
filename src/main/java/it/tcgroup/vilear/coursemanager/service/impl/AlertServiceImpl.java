package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.AlertAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.entity.AlertEntity;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.repository.AlertRepository;
import it.tcgroup.vilear.coursemanager.service.AddressService;
import it.tcgroup.vilear.coursemanager.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private AlertAdapter alertAdapter;

    @Override
    public AlertEntity patchAlertStatus(UUID alertId){

        Optional<AlertEntity> optAlert = alertRepository.findById(alertId);
        AlertEntity alert = null;

        if(optAlert.isPresent()){

            alert = optAlert.get();
            alert.setStatus(true);

            alertRepository.save(alert);
            return alert;

        }else {
            throw new NotFoundException("Alert with id: " + alertId +" not found. ");
        }
    }

    public void insertAlert(CourseEntity course){

        //Inserisco gli alert con start_date = course_start_date
        if(course.getCourseStartDate() != null ){



        }


    }

    private void insertAlertCourseStartDateType(Date startDate, UUID idCourse){

        //Aggiungi un metodo nel repository che recupera tutti gli alert con quel course_code e con il
        //tipo di alert = DATA_INIZIO_CORSO
        List<AlertEntity> alertEntityList = alertRepository.findAll();





    }

}
