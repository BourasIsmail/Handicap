package ma.entraide.handicap;

import ma.entraide.handicap.Entity.Province;
import ma.entraide.handicap.Entity.UserInfo;
import ma.entraide.handicap.Service.ProvinceService;
import ma.entraide.handicap.Service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HandicapApplication {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ProvinceService provinceService;

    public static Logger logger = LoggerFactory.getLogger(HandicapApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HandicapApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args ->{
            logger.info("Running Spring Security Application ...");



            logger.info("end");
        };
    }
}
