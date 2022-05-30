package circula.sam.control.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.Date;

@Data
@Document
public class Restriccion {

    @Id
    private String id;

    private int[] lunes;
    private int[] martes;
    private int[] miercoles;
    private int[] jueves;
    private int[] viernes;
    private int[] sabado;
    private int[] domingo;

    private String hora_inicia;
    private String hora_fin;
}
