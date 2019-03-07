package it.tcgroup.vilear.coursemanager.service.impl;

import it.tcgroup.vilear.coursemanager.adapter.CourseAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseAdapter courseAdapter;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public IdResponseV1 insertCourse(CourseRequestV1 courseInsertRequest) {

        CourseEntity course = courseAdapter.adptCourseRequestToCourse(courseInsertRequest);
        courseRepository.save(course);

        return courseAdapter.adptCourseIdToCourseIdResponse(course);
    }

    @Override
    public CourseResponseV1 getCourse(UUID courseId){

        Optional<CourseEntity> courseOpt = courseRepository.findById(courseId);

        if(!courseOpt.isPresent()){
            throw new NotFoundException("Course with id " + courseId+ " not found");
        }

        return ;
    }


}
