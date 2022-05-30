package circula.sam.control.repository;

import circula.sam.control.models.Restriccion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestriccionesRepo extends MongoRepository<Restriccion, String> {
}
