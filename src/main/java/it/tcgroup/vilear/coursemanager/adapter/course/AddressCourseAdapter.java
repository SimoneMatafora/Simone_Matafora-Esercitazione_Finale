package it.tcgroup.vilear.coursemanager.adapter.course;

import it.tcgroup.vilear.coursemanager.adapter.AddressAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1.*;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.course.AddressCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class AddressCourseAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    public AddressCourse adptAddressCourseRequestToAddressCourse(AddressCourseRequestV1 addressCourseRequest){

        if(addressCourseRequest == null)
            return null;

        AddressCourse addressCourse = new AddressCourse();

        addressCourse.setAddress(addressAdapter.adptAddressRequestToAddressDto(addressCourseRequest.getAddress()));
        addressCourse.setMain(addressCourseRequest.getMain());

        return addressCourse;
    }

    public List<AddressCourse> adptAddressCourseRequestToAddressCourse(List<AddressCourseRequestV1> addressCourseRequestList){

        if(addressCourseRequestList == null)
            return  null;

        List<AddressCourse> addressCourseList = new LinkedList<>();

        for(AddressCourseRequestV1 att : addressCourseRequestList){
            addressCourseList.add(this.adptAddressCourseRequestToAddressCourse(att));
        }

        return addressCourseList;
    }

    public AddressCourseResponseV1 adptAddressCourseToAddressCourseResponse(AddressCourse addressCourse){

        if(addressCourse == null)
            return null;

        AddressCourseResponseV1 addressCourseResponse = new AddressCourseResponseV1();

        addressCourseResponse.setAddress(addressCourse.getAddress());
        addressCourseResponse.setMain(addressCourse.getMain());

        return addressCourseResponse;
    }

    public List<AddressCourseResponseV1> adptAddressCourseToAddressCourseResponse(List<AddressCourse> addressCourseList){

        if(addressCourseList == null)
            return  null;

        List<AddressCourseResponseV1> addressCourseResponseList = new LinkedList<>();

        for(AddressCourse att : addressCourseList){
            addressCourseResponseList.add(this.adptAddressCourseToAddressCourseResponse(att));
        }

        return addressCourseResponseList;
    }
}
