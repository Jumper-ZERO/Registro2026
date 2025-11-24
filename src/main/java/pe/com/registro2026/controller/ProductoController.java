package pe.com.registro2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.registro2026.RecordState;
import pe.com.registro2026.entity.ProductoEntity;
import pe.com.registro2026.service.CategoriaService;
import pe.com.registro2026.service.MarcaService;
import pe.com.registro2026.service.ProductoService;

@Controller
public class ProductoController {

	//Inyeccion de dependencias
	@Autowired
	private ProductoService servicio;
	
	@Autowired
	private MarcaService servmar;
	
	@Autowired
	private CategoriaService servcat;
	
	@GetMapping("/producto/mostrar")
	public String MostrarProducto(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search
	) {
		Page<ProductoEntity> productoPage = servicio.query(search, page, size, RecordState.ACTIVE);

		modelo.addAttribute("listaproducto", productoPage.getContent());
		modelo.addAttribute("currentPage", productoPage.getNumber());
		modelo.addAttribute("totalPages", productoPage.getTotalPages());
		modelo.addAttribute("size", productoPage.getSize());
	    modelo.addAttribute("textoBuscado", search);
		return "producto/mostrar_producto";
	}
	
	@GetMapping("/producto/actualiza/{id}")
	public String MostrarActualizarProducto(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listaproducto", servicio.findById(id));
		modelo.addAttribute("listamarca", servmar.findAll());
		modelo.addAttribute("listacategoria", servcat.findAll());
		return "producto/actualizar_producto";
	}
	
	@GetMapping("/producto/habilita")
	public String MostrarHabilitarProducto(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search
	) {
		Page<ProductoEntity> productoPage = servicio.query(search, page, size, RecordState.ALL);

		modelo.addAttribute("listaproducto", productoPage.getContent());
		modelo.addAttribute("currentPage", productoPage.getNumber());
		modelo.addAttribute("totalPages", productoPage.getTotalPages());
		modelo.addAttribute("size", productoPage.getSize());
	    modelo.addAttribute("textoBuscado", search);
		
		return "producto/habilitar_producto";
	}
	
	//Acciones -> GET 
	@GetMapping("/producto/eliminar/{id}")
	public String EliminarProducto(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/producto/mostrar";
	}
	
	//Acciones -> GET 
	@GetMapping("/producto/habilitar/{id}")
	public String HabilitarProducto(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/producto/habilita";
	}
	
	@GetMapping("/producto/deshabilitar/{id}")
	public String DeshabilitarProducto(@PathVariable("id") Long id) {
	    servicio.delete(id);
	    return "redirect:/producto/habilita";
	}
	
	@GetMapping("/producto/registro")
	public String MostrarRegistroProducto(Model modelo) {
		modelo.addAttribute("listamarca", servmar.findAll());
		modelo.addAttribute("listacategoria", servcat.findAll());
		return "producto/registrar_producto";
	}
	
	//Modelo -> transporta la informacion
	@ModelAttribute("producto")
	public ProductoEntity ModeloProducto() {
		return new ProductoEntity();
	}
	//ACCIONES -> POST
	@PostMapping("/producto/registrar")
	public String RegistrarProducto(@ModelAttribute("producto") ProductoEntity obj) {
		servicio.add(obj);
		return "redirect:/producto/mostrar";
	}
	
	//ACCIONES -> POST
	@PostMapping("/producto/actualizar/{id}")
	public String ActualizarProducto(@ModelAttribute("producto") ProductoEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/producto/mostrar";
	}
}
