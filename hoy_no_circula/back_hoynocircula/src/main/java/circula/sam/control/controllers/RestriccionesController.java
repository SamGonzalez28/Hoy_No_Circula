package circula.sam.control.controllers;

import circula.sam.control.models.Restriccion;
import circula.sam.control.repository.RestriccionesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/circula")
public class RestriccionesController {

    @Autowired
    private RestriccionesRepo resRepo;

    @GetMapping("")
    List<Restriccion> index() {
        return resRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Restriccion create(@RequestBody Restriccion res){
        return resRepo.save(res);
    }

    @PutMapping("{id}")
    Restriccion update(@PathVariable String id, @RequestBody Restriccion res){

        Restriccion resFromDB = resRepo.findById(id).orElseThrow(RuntimeException::new);

        resFromDB.setLunes(res.getLunes());
        resFromDB.setMartes(res.getMartes());
        resFromDB.setMiercoles(res.getMiercoles());
        resFromDB.setJueves(res.getJueves());
        resFromDB.setViernes(res.getViernes());
        resFromDB.setSabado(res.getSabado());
        resFromDB.setDomingo(res.getDomingo());

        resFromDB.setHora_inicia(res.getHora_inicia());
        resFromDB.setHora_fin(res.getHora_fin());

        return resRepo.save(resFromDB);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable String id){

        Restriccion res = resRepo.findById(id).orElseThrow(RuntimeException::new);
        resRepo.delete(res);

    }
}
