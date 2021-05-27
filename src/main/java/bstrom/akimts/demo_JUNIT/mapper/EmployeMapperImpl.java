package bstrom.akimts.demo_JUNIT.mapper;

import bstrom.akimts.demo_JUNIT.entity.Employe;
import bstrom.akimts.demo_JUNIT.model.EmployeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeMapperImpl implements EmployeMapper {
    @Override
    public EmployeDTO toDTO(Employe entity) {

        if(entity == null)
            return null;

        return EmployeDTO.builder().id(entity.getId())
                .nom(entity.getNom())
                .salaire(entity.getSalaire())
                .build();
    }

    @Override
    public Employe toEntity(EmployeDTO dto) {

        if(dto == null)
            return null;

        return Employe.builder().id(dto.getId())
                .nom(dto.getNom())
                .salaire(dto.getSalaire())
                .build();
    }
}
