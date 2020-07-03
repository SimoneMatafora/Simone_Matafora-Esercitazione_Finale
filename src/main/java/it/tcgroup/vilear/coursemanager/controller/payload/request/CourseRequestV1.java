package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class CourseRequestV1 implements Serializable {

    @JsonProperty("titlw")
    private String title;

    @JsonProperty("maximum_number-of_learner")
    private int maximumNumberOfLearner;

    public CourseRequestV1(String title, int maximumNumberOfLearner) {
        this.title = title;
        this.maximumNumberOfLearner = maximumNumberOfLearner;
    }

    public CourseRequestV1( ) {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaximumNumberOfLearner() {
        return maximumNumberOfLearner;
    }

    public void setMaximumNumberOfLearner(int maximumNumberOfLearner) {
        this.maximumNumberOfLearner = maximumNumberOfLearner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRequestV1 that = (CourseRequestV1) o;
        return maximumNumberOfLearner == that.maximumNumberOfLearner &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, maximumNumberOfLearner);
    }

    @Override
    public String toString() {
        return "CourseRequestV1{" +
                "title='" + title + '\'' +
                ", maximumNumberOfLearner=" + maximumNumberOfLearner +
                '}';
    }
}
