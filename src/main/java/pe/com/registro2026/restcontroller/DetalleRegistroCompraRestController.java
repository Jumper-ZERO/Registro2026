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

import pe.com.registro2026.entity.DetalleRegistroCompraEntity;
import pe.com.registro2026.service.DetalleRegistroCompraService;

@RestController
@RequestMapping("/detalleregistro")
public class DetalleRegistroCompraRestController {
	
	//Inyeccion de dependencias
		@Autowired
		private DetalleRegistroCompraService servicio;
		
		//listado -> GET
		@GetMapping
		public List<DetalleRegistroCompraEntity> findAll() {
		    List<DetalleRegistroCompraEntity> lista = servicio.findAll();
		    System.out.println(">>> Se encontraron " + lista.size() + " detalles de registro de compra");
		    return lista;
		}

		
		@GetMapping("/{id}")
		public  DetalleRegistroCompraEntity finById(@PathVariable Long id){
			return servicio.findById(id);
		}
		
		//registrar -> POST
		@PostMapping("/{id}")
		public DetalleRegistroCompraEntity add(@RequestBody DetalleRegistroCompraEntity obj) {
			return servicio.add(obj);
		}
		
		//actualizar -> PUT
		@PutMapping("/{id}")
		public DetalleRegistroCompraEntity update(@PathVariable Long id, 
				@RequestBody DetalleRegistroCompraEntity obj) {
			return servicio.update(id,  obj);
		}
		
		//eliminar -> DELETE
		@DeleteMapping("/{id}")
		public DetalleRegistroCompraEntity delete(@PathVariable Long id) {
			return servicio.delete(id);
		}
		
		//habilidar -> PUT
		@PutMapping("/enable/{id}")
		public DetalleRegistroCompraEntity enable(@PathVariable Long id) {
			return servicio.enable(id);
		}
}
