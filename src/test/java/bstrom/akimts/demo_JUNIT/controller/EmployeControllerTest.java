package bstrom.akimts.demo_JUNIT.controller;

import bstrom.akimts.demo_JUNIT.exception.NotFoundException;
import bstrom.akimts.demo_JUNIT.model.EmployeDTO;
import bstrom.akimts.demo_JUNIT.service.EmployeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;



@WebMvcTest(EmployeController.class)
@ExtendWith(SpringExtension.class)
class EmployeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeService employeService;

    private EmployeDTO entree;
    private EmployeDTO sortie;

    @BeforeEach
    void setUp() {

        entree = EmployeDTO.builder()
                .nom("dominique")
                .salaire(2000)
                .build();

        sortie = EmployeDTO.builder()
                .id(1L)
                .nom("dominique")
                .salaire(2000)
                .build();

        doReturn( sortie ).when(employeService).getOne(1L);
        doThrow(NotFoundException.class).when(employeService).getOne(2L);
        doThrow(NotFoundException.class).when(employeService).getOne(null);

        doReturn( sortie ).when(employeService).insert(entree);
        doThrow(IllegalArgumentException.class).when(employeService).insert(null);

        doReturn( sortie ).when(employeService).delete(1L);
        doThrow(NotFoundException.class).when(employeService).delete(2L);
        doThrow(IllegalArgumentException.class).when(employeService).delete(null);
    }

    @Test
    void getOne_whenFound() {

        // Une dépendance à un utilitaire JSON aurait pu être trouvé
        String expectedJSON = "{" +
                "\"id\":" + sortie.getId() + ", " +
                "\"nom\":" + sortie.getNom() + ", " +
                "\"salaire\":" + sortie.getSalaire() +
                "}";

        assertDoesNotThrow(() -> {
            mvc.perform( get("/employe/1") )
                    .andDo( print() )
                    .andExpect( status().is(HttpStatus.FOUND.value()) )
                    .andExpect( content().json( expectedJSON ) );
//                    .andExpect(jsonPath("$.id", is(1) ))
//                    .andExpect(jsonPath("$.nom", is(sortie.getNom()) ))
//                    .andExpect(jsonPath("$.salaire", is(sortie.getSalaire()) ));
        });
    }

    @Test
    void getOne_whenNotFound(){

        assertDoesNotThrow(() -> {
            mvc.perform( get("/employe/"+2))
                    .andDo(print())
                    .andExpect(status().is(400));
        });

    }

}