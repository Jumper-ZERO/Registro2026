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

import pe.com.registro2026.entity.ProductoEntity;
import pe.com.registro2026.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoRestController {
	
	//Inyeccion de dependencias
	@Autowired
	private ProductoService servicio;
	
	//listado -> GET
	@GetMapping
	public List<ProductoEntity> findAll() {
	    List<ProductoEntity> lista = servicio.findAll();
	    System.out.println(">>> Se encontraron " + lista.size() + " productos");
	    return lista;
	}

    // Buscar por ID
    @GetMapping("/{id}")
    public ProductoEntity findById(@PathVariable("id") Long id) {
        return servicio.findById(id);
    }
	
	//registrar -> POST
	@PostMapping()
	public ProductoEntity add(@RequestBody ProductoEntity obj) {
		return servicio.add(obj);
	}
	
    // Actualizar producto existente
    @PutMapping("/{id}")
    public ProductoEntity update(@PathVariable("id") Long id, @RequestBody ProductoEntity obj) {
        return servicio.update(id, obj);
    }

    // Eliminar producto (borrado lógico)
    @DeleteMapping("/{id}")
    public ProductoEntity delete(@PathVariable("id") Long id) {
        return servicio.delete(id);
    }

    // Habilitar producto (volver a estado activo)
    @PutMapping("/enable/{id}")
    public ProductoEntity enable(@PathVariable("id") Long id) {
        return servicio.enable(id);
    }
}
