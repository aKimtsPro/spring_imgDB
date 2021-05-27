package bstrom.akimts.demo_JUNIT.mapper;

import bstrom.akimts.demo_JUNIT.entity.Employe;
import bstrom.akimts.demo_JUNIT.model.EmployeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@Import(EmployeMapperImplTestConfiguration.class)
class EmployeMapperImplTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        public EmployeMapper mapper(){
            return new EmployeMapperImpl();
        }

    }

    @Autowired
    private EmployeMapper mapper;

    @Test
    void toDTO_whenNull() {
        assertNull( mapper.toDTO(null) );
    }

    @Test
    void toDTO_whenNotNull() {

        Employe toMap = Employe.builder()
                .id(1L)
                .nom("luc")
                .salaire(1000)
                .build();

        EmployeDTO expected = EmployeDTO.builder()
                .id(1L)
                .nom("luc")
                .salaire(1000)
                .build();

        assertEquals( expected,mapper.toDTO(toMap) );

    }

    @Test
    void toEntity_whenNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toEntity_whenNotNull() {

        EmployeDTO toMap = EmployeDTO.builder()
                .id(1L)
                .nom("luc")
                .salaire(1000)
                .build();

        Employe expected = Employe.builder()
                .id(1L)
                .nom("luc")
                .salaire(1000)
                .build();

        assertEquals( expected, mapper.toEntity(toMap) );
    }
}