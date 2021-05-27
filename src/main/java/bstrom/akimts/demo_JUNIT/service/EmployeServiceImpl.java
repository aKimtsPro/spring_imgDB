package bstrom.akimts.demo_JUNIT.service;

import bstrom.akimts.demo_JUNIT.entity.Employe;
import bstrom.akimts.demo_JUNIT.exception.NotFoundException;
import bstrom.akimts.demo_JUNIT.mapper.EmployeMapper;
import bstrom.akimts.demo_JUNIT.model.EmployeDTO;
import bstrom.akimts.demo_JUNIT.repository.EmployeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeServiceImpl implements EmployeService {

    private final EmployeMapper mapper;
    private final EmployeRepository repository;

    public EmployeServiceImpl(EmployeMapper mapper, EmployeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public EmployeDTO getOne(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public EmployeDTO insert(EmployeDTO toInsert) {
        return mapper.toDTO(repository.save( mapper.toEntity(toInsert) ) );
    }

    @Override
    public EmployeDTO delete(Long id) {
        Employe found = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.deleteById(id);
        return mapper.toDTO(found);
    }
}
