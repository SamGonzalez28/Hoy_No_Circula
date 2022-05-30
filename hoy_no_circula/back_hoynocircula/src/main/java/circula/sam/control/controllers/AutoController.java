package circula.sam.control.controllers;
import circula.sam.control.models.Auto;
import circula.sam.control.models.Restriccion;
import circula.sam.control.repository.RestriccionesRepo;
import circula.sam.control.controllers.RestriccionesController;
import circula.sam.control.models.Busqueda;
import circula.sam.control.repository.AutoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/auto")
public class AutoController {

    @Autowired
    private AutoRepo auRepo;
    @Autowired
    private RestriccionesRepo reRepo;

    @GetMapping("")
    List<Auto> index() {
        return auRepo.findAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Auto create(@RequestBody Auto auto){
        return auRepo.save(auto);
    }

    @PutMapping("{id}")
    Auto update(@PathVariable String id, @RequestBody Auto auto){

        Auto auFromDB = auRepo.findById(id).orElseThrow(RuntimeException::new);

        auFromDB.setPlaca(auto.getPlaca());
        auFromDB.setChasis(auto.getChasis());
        auFromDB.setColor(auto.getColor());
        auFromDB.setDiscapacidad(auto.isDiscapacidad());
        auFromDB.setPublico(auto.isPublico());

        return auRepo.save(auFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable String id){

        Auto auto = auRepo.findById(id).orElseThrow(RuntimeException::new);
        auRepo.delete(auto);
    }


    @PostMapping("/buscar")
    Busqueda busqueda(@RequestBody Busqueda busque){
        if(compararFecha(busque)){
            List<Auto> autos = index();
            Auto encontrado=null;


            for (int i=0; i<autos.size();i++) {
                if (autos.get(i).getPlaca().equals(busque.getPlaca())) {
                    encontrado = autos.get(i);
                    break;
                }
            }
            if(encontrado!= null){

                String ultimo = busque.getPlaca().substring(busque.getPlaca().length()-1);
                System.out.println(ultimo);
                busque.setMensaje(ultimo);

                if(verificarDias(busque, Integer.parseInt(ultimo))){
                    busque.setMensaje("Puede Circular");
                }else {
                    busque.setMensaje("No Puede Circular");
                }


                return  busque;
            }else{
                busque.setMensaje("Auto No encontrado");
                return  busque;
            }
        }else {
            busque.setMensaje("No se aceptan fechas pasadas");
            return busque;
        }

    }

    boolean compararFecha(Busqueda busqueda){
        Date date = new Date(System.currentTimeMillis());

        if (date.before(busqueda.getFecha())){
            return true;
        }else{
            return false;
        }
    }

    boolean verificarDias(Busqueda busque, int ultimo){
        boolean flagVeriDias = false;
        String dia="";
        List<Restriccion> res = reRepo.findAll();
        System.out.println(res.get(0));
        int day = busque.getFecha().getDay();
        switch (day){
            case 0: dia = "domingo";
                if (res.get(0).getDomingo() != null){
                    flagVeriDias = verificarCirculacion(res.get(0).getDomingo(), ultimo);
                }else{
                    return true;
                }
                break;
            case 1: dia = "lunes";
                flagVeriDias = verificarCirculacion(res.get(0).getLunes(), ultimo);
                break;
            case 2: dia = "martes";
                flagVeriDias = verificarCirculacion(res.get(0).getMartes(), ultimo);
                break;
            case 3: dia = "miercoles";
                flagVeriDias = verificarCirculacion(res.get(0).getMiercoles(), ultimo);
                break;
            case 4: dia = "jueves";
                flagVeriDias = verificarCirculacion(res.get(0).getJueves(), ultimo);
                break;
            case 5: dia = "viernes";
                flagVeriDias = verificarCirculacion(res.get(0).getViernes(), ultimo);
                break;
            case 6: dia = "sabado";
                if (res.get(0).getSabado() != null){
                    flagVeriDias = verificarCirculacion(res.get(0).getSabado(), ultimo);
                }else{
                    return true;
                }
                break;
        }

        return flagVeriDias;
    }

    boolean verificarCirculacion(int[] rechazados, int digPlaca){
        for (int i =0; i< rechazados.length; i++){
            if (digPlaca == rechazados[i]){
                return false;
            }
        }
        return  true;
    }
}
