package bstrom.akimts.demo_JUNIT.repository;

import bstrom.akimts.demo_JUNIT.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT f.id FROM File f")
    List<Long> getIds();

}
