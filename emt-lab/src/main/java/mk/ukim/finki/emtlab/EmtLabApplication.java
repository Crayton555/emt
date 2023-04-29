package mk.ukim.finki.emtlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableScheduling
@SpringBootApplication
public class EmtLabApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmtLabApplication.class, args);
    }
}
