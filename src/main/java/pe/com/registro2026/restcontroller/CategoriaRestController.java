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

import pe.com.registro2026.entity.CategoriaEntity;
import pe.com.registro2026.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaRestController {
	
	//Inyeccion de dependencias
	@Autowired
	private CategoriaService servicio;
	
	//listado -> GET
	@GetMapping
	public List<CategoriaEntity> findAll() {
	    List<CategoriaEntity> lista = servicio.findAll();
	    System.out.println(">>> Se encontraron " + lista.size() + " categorias");
	    return lista;
	}

	@GetMapping("/{id}")
	public CategoriaEntity finById(@PathVariable("id") Long id) {
	    return servicio.findById(id);
	}
	
	//registrar -> POST
	@PostMapping
	public CategoriaEntity add(@RequestBody CategoriaEntity obj) {
		return servicio.add(obj);
	}
	
	@PutMapping("/{id}")
	public CategoriaEntity update(@PathVariable("id") Long id, @RequestBody CategoriaEntity obj) {
	    return servicio.update(id, obj);
	}

	@DeleteMapping("/{id}")
	public CategoriaEntity delete(@PathVariable("id") Long id) {
	    return servicio.delete(id);
	}

	@PutMapping("/enable/{id}")
	public CategoriaEntity enable(@PathVariable("id") Long id) {
	    return servicio.enable(id);
	}
}
