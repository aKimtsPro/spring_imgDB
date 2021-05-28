package bstrom.akimts.demo_JUNIT.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {

    private long id;
    private String name;
    private String type;
    private byte[] bytes;

}
