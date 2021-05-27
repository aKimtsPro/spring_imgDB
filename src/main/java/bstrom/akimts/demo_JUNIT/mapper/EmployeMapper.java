package bstrom.akimts.demo_JUNIT.mapper;

import bstrom.akimts.demo_JUNIT.entity.Employe;
import bstrom.akimts.demo_JUNIT.model.EmployeDTO;

public interface EmployeMapper {

    EmployeDTO toDTO(Employe entity);
    Employe toEntity(EmployeDTO dto);

}
