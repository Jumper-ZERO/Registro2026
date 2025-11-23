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

import pe.com.registro2026.entity.RegistroCompraEntity;
import pe.com.registro2026.service.RegistroCompraService;

@RestController
@RequestMapping("registrocompra")
public class RegistroCompraRestController {
	
	//Inyeccion de dependencias
	@Autowired
	private RegistroCompraService servicio;
	
	//listado -> GET
	@GetMapping
	public List<RegistroCompraEntity> findAll() {
	    List<RegistroCompraEntity> lista = servicio.findAll();
	    System.out.println(">>> Se encontraron " + lista.size() + " registro de compras");
	    return lista;
	}

	@GetMapping("/{id}")
	public  RegistroCompraEntity finById(@PathVariable Long id){
		return servicio.findById(id);
	}
	
	//registrar -> POST
	@PostMapping("/{id}")
	public RegistroCompraEntity add(@RequestBody RegistroCompraEntity obj) {
		return servicio.add(obj);
	}
	
	//actualizar -> PUT
	@PutMapping("/{id}")
	public RegistroCompraEntity update(@PathVariable Long id, 
			@RequestBody RegistroCompraEntity obj) {
		return servicio.update(id,  obj);
	}
	
	//eliminar -> DELETE
	@DeleteMapping("/{id}")
	public RegistroCompraEntity delete(@PathVariable Long id) {
		return servicio.delete(id);
	}
	
	//habilidar -> PUT
	@PutMapping("/enable/{id}")
	public RegistroCompraEntity enable(@PathVariable Long id) {
		return servicio.enable(id);
	}
}
