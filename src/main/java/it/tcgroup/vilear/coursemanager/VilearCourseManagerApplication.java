package it.tcgroup.vilear.coursemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableRetry
public class VilearCourseManagerApplication {

    public static void main(String[] args) throws Exception{

        SpringApplication.run(VilearCourseManagerApplication.class, args);
    }
}
