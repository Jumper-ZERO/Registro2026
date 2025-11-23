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

import pe.com.registro2026.entity.RegistroCompraEntity;
import pe.com.registro2026.service.EmpleadoService;
import pe.com.registro2026.service.ProveedorService;
import pe.com.registro2026.service.RegistroCompraService;

@Controller
public class RegistroCompraController {

	//Inyeccion de dependencias
	@Autowired
	private RegistroCompraService servicio;
	
	@Autowired
	private ProveedorService servprov;
	
	@Autowired
	private EmpleadoService servemp;
	
	@GetMapping("/registrocompra/mostrar")
	public String MostrarRegistroCompra(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search
	) {
		Page<RegistroCompraEntity> registrocompraPage = servicio.findAllCustom(search, page, size);

		modelo.addAttribute("listaregistrocompra", registrocompraPage.getContent());
		modelo.addAttribute("currentPage", registrocompraPage.getNumber());
		modelo.addAttribute("totalPages", registrocompraPage.getTotalPages());
		modelo.addAttribute("size", registrocompraPage.getSize());
	    modelo.addAttribute("textoBuscado", search);
		return "registrocompra/mostrar_registrocompra";
	}
	
	@GetMapping("/registrocompra/actualiza/{id}")
	public String MostrarActualizarRegistroCompra(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listaregistrocompra", servicio.findById(id));
		modelo.addAttribute("listaproveedor", servprov.findAll());
		modelo.addAttribute("listaempleado", servemp.findAll());
		return "registrocompra/actualizar_registrocompra";
	}
	
	@GetMapping("/registrocompra/habilita")
	public String MostrarHabilitarRegistroCompra(Model modelo) {
		modelo.addAttribute("listaregistrocompra", servicio.findAll());
		
		return "registrocompra/habilitar_registrocompra";
	}
	
	//Acciones -> GET 
	@GetMapping("/registrocompra/eliminar/{id}")
	public String EliminarRegistroCompra(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/registrocompra/mostrar";
	}
	
	//Acciones -> GET 
	@GetMapping("/registrocompra/habilitar/{id}")
	public String HabilitarRegistroCompra(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/registrocompra/habilita";
	}
	
	@GetMapping("/registrocompra/deshabilitar/{id}")
	public String DeshabilitarRegistroCompra(@PathVariable("id") Long id) {
	    servicio.delete(id);
	    return "redirect:/registrocompra/habilita";
	}
	
	@GetMapping("/registrocompra/registro")
	public String MostrarRegistroRegistroCompra(Model modelo) {
		modelo.addAttribute("listaproveedor", servprov.findAll());
		modelo.addAttribute("listaempleado", servemp.findAll());
		return "registrocompra/registrar_registrocompra";
	}
	
	//Modelo -> transporta la informacion
	@ModelAttribute("registrocompra")
	public RegistroCompraEntity ModeloRegistroCompra() {
		return new RegistroCompraEntity();
	}
	//ACCIONES -> POST
	@PostMapping("/registrocompra/registrar")
	public String RegistrarRegistroCompra(@ModelAttribute("registrocompra") RegistroCompraEntity obj) {
		servicio.add(obj);
		return "redirect:/registrocompra/mostrar";
	}
	
	//ACCIONES -> POST
	@PostMapping("/registrocompra/actualizar/{id}")
	public String ActualizarRegistroCompra(@ModelAttribute("registrocompra") RegistroCompraEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/registrocompra/mostrar";
	}
}
