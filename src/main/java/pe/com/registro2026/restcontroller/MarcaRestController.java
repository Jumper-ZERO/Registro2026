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

import pe.com.registro2026.entity.MarcaEntity;
import pe.com.registro2026.service.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaRestController {
	//Inyeccion de dependencias
		@Autowired
		private MarcaService servicio;
		
		//listado -> GET
		@GetMapping
		public List<MarcaEntity> findAll() {
		    List<MarcaEntity> lista = servicio.findAll();
		    System.out.println(">>> Se encontraron " + lista.size() + " marcas");
		    return lista;
		}

		
		@GetMapping("/{id}")
	    public MarcaEntity findById(@PathVariable("id") Long id) {
	        return servicio.findById(id);
	    }
		
		//registrar -> POST
		@PostMapping()
		public MarcaEntity add(@RequestBody MarcaEntity obj) {
			return servicio.add(obj);
		}
		
		@PutMapping("/{id}")
	    public MarcaEntity update(@PathVariable("id") Long id, @RequestBody MarcaEntity obj) {
	        return servicio.update(id, obj);
	    }

	    // Eliminar marca (borrado lógico)
	    @DeleteMapping("/{id}")
	    public MarcaEntity delete(@PathVariable("id") Long id) {
	        return servicio.delete(id);
	    }

	    // Habilitar marca
	    @PutMapping("/enable/{id}")
	    public MarcaEntity enable(@PathVariable("id") Long id) {
	        return servicio.enable(id);
	    }
}
