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

import pe.com.registro2026.entity.DistritoEntity;
import pe.com.registro2026.service.DistritoService;

@RestController
@RequestMapping("/distrito")
public class DistritoRestController {

	// Inyeccion de dependencias
	@Autowired
	private DistritoService servicio;

	// listado -> GET
	@GetMapping
	public List<DistritoEntity> findAll() {
		List<DistritoEntity> lista = servicio.findAll();
		System.out.println(">>> Se encontraron " + lista.size() + " distritos");
		return lista;
	}

	// Buscar distrito por ID
	@GetMapping("/{id}")
	public DistritoEntity findById(@PathVariable("id") Long id) {
		return servicio.findById(id);
	}

	// registrar -> POST
	@PostMapping()
	public DistritoEntity add(@RequestBody DistritoEntity obj) {
		return servicio.add(obj);
	}

	// Actualizar distrito existente
	@PutMapping("/{id}")
	public DistritoEntity update(@PathVariable("id") Long id, @RequestBody DistritoEntity obj) {
		return servicio.update(id, obj);
	}

	// Eliminar distrito (borrado lógico)
	@DeleteMapping("/{id}")
	public DistritoEntity delete(@PathVariable("id") Long id) {
		return servicio.delete(id);
	}

	// Habilitar distrito (volver a estado activo)
	@PutMapping("/enable/{id}")
	public DistritoEntity enable(@PathVariable("id") Long id) {
		return servicio.enable(id);
	}
}
