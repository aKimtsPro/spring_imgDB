package bstrom.akimts.demo_JUNIT.repository;

import bstrom.akimts.demo_JUNIT.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Long> {

    Optional<Employe> findByNom(String nom);
}
