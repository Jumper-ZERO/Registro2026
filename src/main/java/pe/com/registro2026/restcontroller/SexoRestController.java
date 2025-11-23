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

import pe.com.registro2026.entity.SexoEntity;
import pe.com.registro2026.service.SexoService;

@RestController
@RequestMapping("/sexo")
public class SexoRestController {

	@Autowired
	private SexoService servicio;
		
	@GetMapping
	public List<SexoEntity> findAll(){
		return servicio.findAll();
	}
	
    // Buscar sexo por ID
	@GetMapping("/id/{id}")
    public SexoEntity findById(@PathVariable("id") Long id) {
        return servicio.findById(id);
    }
	
	@PostMapping()
	public SexoEntity add(@RequestBody SexoEntity obj) {
		return servicio.add(obj);
	}

    // Actualizar sexo existente
    @PutMapping("/{id}")
    public SexoEntity update(@PathVariable("id") Long id,
                             @RequestBody SexoEntity obj) {
        return servicio.update(id, obj);
    }

    // Eliminar (deshabilitar lógicamente)
    @DeleteMapping("/{id}")
    public SexoEntity delete(@PathVariable("id") Long id) {
        return servicio.delete(id);
    }

    // Habilitar nuevamente un sexo
    @PutMapping("/enable/{id}")
    public SexoEntity enable(@PathVariable("id") Long id) {
        return servicio.enable(id);
    }
}
