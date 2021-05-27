package bstrom.akimts.demo_JUNIT.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeDTO {

    private Long id;
    private String nom;
    private float salaire;
}
