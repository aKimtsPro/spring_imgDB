package bstrom.akimts.demo_JUNIT.repository;

import bstrom.akimts.demo_JUNIT.entity.Employe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private EmployeRepository repository;

    @Test
    void findByName_whenFound(){

        String nom = "luc";

        Employe inserted = Employe.builder()
                .nom(nom)
                .salaire(1000)
                .build();

        inserted = manager.persist(inserted);

        Employe found = repository.findByNom(nom).orElseThrow();

        assertEquals(inserted, found);

    }

    @Test
    void foundByName_whenNotFound(){
        // A remarque que malgré le fait que le test puisse être fait après le
        // précédent, luc n'est plus dans la DB, on repart donc à zéro pour chaque test
        assertTrue(repository.findByNom("luc").isEmpty());
    }

}
