package circula.sam.control.repository;

import circula.sam.control.models.Auto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AutoRepo extends MongoRepository<Auto, String> {
}
