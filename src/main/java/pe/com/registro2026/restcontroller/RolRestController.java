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

import pe.com.registro2026.entity.RolEntity;
import pe.com.registro2026.service.RolService;

@RestController
@RequestMapping("rol")
public class RolRestController {


	@Autowired
	private RolService servicio;
		
	@GetMapping
	public List<RolEntity> findAll(){
		return servicio.findAll();
	}
	
    // Buscar rol por ID
    @GetMapping("/{id}")
    public RolEntity findById(@PathVariable("id") Long id) {
        return servicio.findById(id);
    }
	
	@PostMapping()
	public RolEntity add(@RequestBody RolEntity obj) {
		return servicio.add(obj);
	}
	
	 // Actualizar rol existente
    @PutMapping("/{id}")
    public RolEntity update(@PathVariable("id") Long id, 
                            @RequestBody RolEntity obj) {
        return servicio.update(id, obj);
    }

    // Eliminar (deshabilitar lógicamente)
    @DeleteMapping("/{id}")
    public RolEntity delete(@PathVariable("id") Long id) {
        return servicio.delete(id);
    }

    // Habilitar nuevamente un rol
    @PutMapping("/enable/{id}")
    public RolEntity enable(@PathVariable("id") Long id) {
        return servicio.enable(id);
    }
}
