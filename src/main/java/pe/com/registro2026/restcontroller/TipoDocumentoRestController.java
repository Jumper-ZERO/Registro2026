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

import pe.com.registro2026.entity.TipoDocumentoEntity;
import pe.com.registro2026.service.TipoDocumentoService;

@RestController
@RequestMapping("/tipodocumento")
public class TipoDocumentoRestController {
	
	//Inyeccion de dependencias
	@Autowired
	private TipoDocumentoService servicio;
	
		
	@GetMapping
	public List<TipoDocumentoEntity> findAll(){
		return servicio.findAll();
	}
	
    // Buscar tipo de documento por ID
    @GetMapping("/{id}")
    public TipoDocumentoEntity findById(@PathVariable("id") Long id) {
        return servicio.findById(id);
    }
	
	@PostMapping
	public TipoDocumentoEntity add(@RequestBody TipoDocumentoEntity obj) {
		return servicio.add(obj);
	}
	

    // Actualizar tipo de documento existente
    @PutMapping("/{id}")
    public TipoDocumentoEntity update(@PathVariable("id") Long id,
                                      @RequestBody TipoDocumentoEntity obj) {
        return servicio.update(id, obj);
    }

    // Eliminar (deshabilitar lógicamente)
    @DeleteMapping("/{id}")
    public TipoDocumentoEntity delete(@PathVariable("id") Long id) {
        return servicio.delete(id);
    }

    // Habilitar nuevamente un tipo de documento
    @PutMapping("/enable/{id}")
    public TipoDocumentoEntity enable(@PathVariable("id") Long id) {
        return servicio.enable(id);
    }
}
