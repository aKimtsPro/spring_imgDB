package bstrom.akimts.demo_JUNIT.mapper;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class EmployeMapperImplTestConfiguration {

    @Bean
    public EmployeMapper mapper(){
        return new EmployeMapperImpl();
    }

}
