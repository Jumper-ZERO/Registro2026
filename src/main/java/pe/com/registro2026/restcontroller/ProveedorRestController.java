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

import pe.com.registro2026.entity.ProveedorEntity;
import pe.com.registro2026.service.ProveedorService;

@RestController
@RequestMapping("/proveedor")
public class ProveedorRestController {
	
	//Inyeccion de dependencias
	@Autowired
	private ProveedorService servicio;
	
	//listado -> GET
	@GetMapping
	public List<ProveedorEntity> findAll() {
	    List<ProveedorEntity> lista = servicio.findAll();
	    System.out.println(">>> Se encontraron " + lista.size() + " proveedores");
	    return lista;
	}

    // Buscar proveedor por ID
    @GetMapping("/{id}")
    public ProveedorEntity findById(@PathVariable("id") Long id) {
        return servicio.findById(id);
    }
	
	//registrar -> POST
	@PostMapping()
	public ProveedorEntity add(@RequestBody ProveedorEntity obj) {
		return servicio.add(obj);
	}
	
	 // Actualizar proveedor existente
    @PutMapping("/{id}")
    public ProveedorEntity update(@PathVariable("id") Long id,
                                  @RequestBody ProveedorEntity obj) {
        return servicio.update(id, obj);
    }

    // Eliminar (deshabilitar lógicamente)
    @DeleteMapping("/{id}")
    public ProveedorEntity delete(@PathVariable("id") Long id) {
        return servicio.delete(id);
    }

    // Habilitar nuevamente un proveedor
    @PutMapping("/enable/{id}")
    public ProveedorEntity enable(@PathVariable("id") Long id) {
        return servicio.enable(id);
    }
}
