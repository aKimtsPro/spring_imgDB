package bstrom.akimts.demo_JUNIT.service;

import bstrom.akimts.demo_JUNIT.model.EmployeDTO;

public interface EmployeService {

    EmployeDTO getOne(Long id);
    EmployeDTO insert(EmployeDTO toInsert);
    EmployeDTO delete(Long id);

}
