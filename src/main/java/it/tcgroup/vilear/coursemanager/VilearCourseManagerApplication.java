package it.tcgroup.vilear.coursemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VilearCourseManagerApplication {

    public static void main(String[] args) throws Exception{

        SpringApplication.run(VilearCourseManagerApplication.class, args);
    }
}
