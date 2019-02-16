package it.tcgroup.vilear.dummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VilearDummyApplication {

    public static void main(String[] args) throws Exception{

        SpringApplication.run(VilearDummyApplication.class, args);
    }
}
