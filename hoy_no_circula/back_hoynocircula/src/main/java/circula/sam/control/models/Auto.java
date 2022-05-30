package circula.sam.control.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Auto {
    @Id
    private String id;

    private String placa;
    private String color;
    private String chasis;
    private boolean publico;
    private boolean discapacidad;

}


