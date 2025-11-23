package pe.com.registro2026.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.registro2026.entity.EmpleadoEntity;
import pe.com.registro2026.service.EmpleadoService;

@RestController
@RequestMapping("/empleado")
public class EmpleadoRestController {

	@Autowired
	private EmpleadoService servicio;
		
	@GetMapping
	public List<EmpleadoEntity> findAll(){
		return servicio.findAll();
	}
	
    // Buscar por ID
    @GetMapping("/{id}")
    public EmpleadoEntity findById(@PathVariable("id") Long id) {
        return servicio.findById(id);
    }
	
	@PostMapping()
	public EmpleadoEntity add(@RequestBody EmpleadoEntity obj) {
		return servicio.add(obj);
	}
	
	 //  Actualizar empleado existente
    @PutMapping("/{id}")
    public EmpleadoEntity update(@PathVariable("id") Long id,
                                 @RequestBody EmpleadoEntity obj) {
        return servicio.update(id, obj);
    }

    //  Eliminar (deshabilitar lógicamente)
    @DeleteMapping("/{id}")
    public EmpleadoEntity delete(@PathVariable("id") Long id) {
        return servicio.delete(id);
    }

    // Habilitar nuevamente un empleado
    @PutMapping("/enable/{id}")
    public EmpleadoEntity enable(@PathVariable("id") Long id) {
        return servicio.enable(id);
    }
}
