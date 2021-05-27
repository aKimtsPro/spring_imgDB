package bstrom.akimts.demo_JUNIT.service;

import bstrom.akimts.demo_JUNIT.entity.Employe;
import bstrom.akimts.demo_JUNIT.exception.NotFoundException;
import bstrom.akimts.demo_JUNIT.mapper.EmployeMapper;
import bstrom.akimts.demo_JUNIT.model.EmployeDTO;
import bstrom.akimts.demo_JUNIT.repository.EmployeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmployeServiceImplTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        public EmployeService service(){
            return new EmployeServiceImpl(mapper, repository);
        }

        @MockBean
        public EmployeMapper mapper;

        @MockBean
        public EmployeRepository repository;
    }

    @Autowired
    private EmployeService service;

    @Autowired
    private EmployeRepository repository;

    @Autowired
    private EmployeMapper mapper;


    private EmployeDTO dto;
    private EmployeDTO dtoEntre;

    private Employe entity;
    private Employe entityEntre;


    // Il existe aussi AfterEach
    // et BeforeAll et AfterAll(static)
    @BeforeEach
    void setUp(){

        dto = EmployeDTO.builder()
                .id(1L)
                .nom("luc")
                .salaire(1000)
                .build();
        dtoEntre = EmployeDTO.builder()
                .nom("lucie")
                .salaire(1000)
                .build();
        entity = Employe.builder()
                .id(1L)
                .nom("luc")
                .salaire(1000)
                .build();
        entityEntre = Employe.builder()
                .nom("lucie")
                .salaire(1000)
                .build();

        // MOCKITO

        // sans l'import static: Mockito.doReturn(...). ...
        doNothing().when(repository).deleteAll();// ne fera rien
        doReturn(Optional.of(entity))
                .when(repository).findById(1L);
//        when( repository.findById(1L) ).thenReturn(
//                Optional.of(entity)
//        );
        doThrow( IllegalArgumentException.class ).when(repository).deleteById(null);

        // L'utilisation du when demande à ce que la méthode exécutée ne renvoie pas void
        when( repository.findById(2L) ).thenReturn(Optional.empty());
        when( repository.save(entityEntre) ).thenReturn(entity);
        when( repository.findById(null) ).thenThrow(IllegalArgumentException.class);

        when( mapper.toDTO(entity) ).thenReturn(dto);
        when( mapper.toEntity(dtoEntre) ).thenReturn(entityEntre);

    }

    @Test
    void getOne_whenFound() {
        assertEquals(dto,service.getOne(1L));
    }

    @Test
    void getOne_whenNull(){
        assertThrows(IllegalArgumentException.class, () -> service.getOne(null));
    }

    @Test
    void getOne_whenNotFound() {
        assertThrows(NotFoundException.class, () -> service.getOne(2L));
    }

    @Test
    void insert() {
        assertEquals(dto,service.insert(dtoEntre));
    }

    @Test
    void delete_whenFound() {
        assertEquals(dto, service.delete(1L));
    }

    @Test
    void delete_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> service.delete(null));
    }

    @Test
    void delete_whenNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(2L));
    }
}