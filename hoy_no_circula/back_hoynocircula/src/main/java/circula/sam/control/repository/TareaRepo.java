package circula.sam.control.repository;

import circula.sam.control.models.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TareaRepo extends MongoRepository<Tarea, String> {

}
