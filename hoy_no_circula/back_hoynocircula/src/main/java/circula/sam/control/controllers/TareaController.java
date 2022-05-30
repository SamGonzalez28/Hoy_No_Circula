package circula.sam.control.controllers;

import circula.sam.control.models.Tarea;
import circula.sam.control.repository.TareaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaRepo tareaRepo;

    @GetMapping("")
    List<Tarea> index() {
        return tareaRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Tarea create(@RequestBody Tarea tarea){
        return tareaRepo.save(tarea);
    }

    @PutMapping("{id}")
    Tarea update(@PathVariable String id, @RequestBody Tarea tarea){

        Tarea tareaFromDB = tareaRepo.findById(id).orElseThrow(RuntimeException::new);

        tareaFromDB.setNombre(tarea.getNombre());
        tareaFromDB.setCompletado(tarea.isCompletado());

        return tareaRepo.save(tareaFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable String id){

        Tarea tarea = tareaRepo.findById(id).orElseThrow(RuntimeException::new);
        tareaRepo.delete(tarea);
    }
}
