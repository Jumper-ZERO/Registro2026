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

import pe.com.registro2026.entity.DetalleRegistroCompraEntity;
import pe.com.registro2026.service.DetalleRegistroCompraService;
import pe.com.registro2026.service.ProductoService;
import pe.com.registro2026.service.RegistroCompraService;

@Controller
public class DetalleRegistroCompraController {

	//Inyeccion de dependencias
	@Autowired
	private DetalleRegistroCompraService servicio;
	
	@Autowired
	private RegistroCompraService servregcom;
	
	@Autowired
	private ProductoService servprod;
	
	@GetMapping("/detalleregistrocompra/mostrar")
	public String MostrarDetalleRegistroCompra(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search
	) {
		Page<DetalleRegistroCompraEntity> detalleregistrocompraPage = servicio.findAllCustom(search, page, size);

		modelo.addAttribute("listadetalleregistrocompra", detalleregistrocompraPage.getContent());
		modelo.addAttribute("currentPage", detalleregistrocompraPage.getNumber());
		modelo.addAttribute("totalPages", detalleregistrocompraPage.getTotalPages());
		modelo.addAttribute("size", detalleregistrocompraPage.getSize());
	    modelo.addAttribute("textoBuscado", search);
		return "detalleregistrocompra/mostrar_detalleregistrocompra";
	}
	
	@GetMapping("/detalleregistrocompra/actualiza/{id}")
	public String MostrarActualizarDetalleRegistroCompra(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listadetalleregistrocompra", servicio.findById(id));
		modelo.addAttribute("listaregistrocompra", servregcom.findAll());
		modelo.addAttribute("listaproducto", servprod.findAll());
		return "detalleregistrocompra/actualizar_detalleregistrocompra";
	}
	
	@GetMapping("/detalleregistrocompra/habilita")
	public String MostrarHabilitarDetalleRegistroCompra(Model modelo) {
		modelo.addAttribute("listadetalleregistrocompra",servicio.findAll());
		
		return "detalleregistrocompra/habilitar_detalleregistrocompra";
	}
	
	//Acciones -> GET 
	@GetMapping("/detalleregistrocompra/eliminar/{id}")
	public String EliminarDetalleRegistroCompra(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/detalleregistrocompra/mostrar";
	}
	
	//Acciones -> GET 
	@GetMapping("/detalleregistrocompra/habilitar/{id}")
	public String HabilitarDetalleRegistroCompra(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/detalleregistrocompra/habilita";
	}
	
	@GetMapping("/detalleregistrocompra/deshabilitar/{id}")
	public String DeshabilitarDetalleRegistroCompra(@PathVariable("id") Long id) {
	    servicio.delete(id);
	    return "redirect:/detalleregistrocompra/habilita";
	}
	
	@GetMapping("/detalleregistrocompra/registro")
	public String MostrarRegistroDetalleRegistroCompra(Model modelo) {
		modelo.addAttribute("listaregistrocompra", servregcom.findAll());
		modelo.addAttribute("listaproducto", servprod.findAll());
		return "detalleregistrocompra/registrar_detalleregistrocompra";
	}
	
	//Modelo -> transporta la informacion
	@ModelAttribute("detalleregistrocompra")
	public DetalleRegistroCompraEntity ModeloDetalleRegistroCompra() {
		return new DetalleRegistroCompraEntity();
	}
	//ACCIONES -> POST
	@PostMapping("/detalleregistrocompra/registrar")
	public String RegistrarDetalleRegistroCompra(@ModelAttribute("detalleregistrocompra") DetalleRegistroCompraEntity obj) {
		servicio.add(obj);
		return "redirect:/detalleregistrocompra/mostrar";
	}
	
	//ACCIONES -> POST
	@PostMapping("/detalleregistrocompra/actualizar/{id}")
	public String ActualizarDetalleRegistroCompra(@ModelAttribute("detalleregistrocompra") DetalleRegistroCompraEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/detalleregistrocompra/mostrar";
	}
}
