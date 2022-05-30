package circula.sam.control.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Busqueda {

    private String placa;
    private Date fecha;
    private String mensaje;
}
