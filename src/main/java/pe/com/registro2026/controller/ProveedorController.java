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

import pe.com.registro2026.entity.ProveedorEntity;
import pe.com.registro2026.service.DistritoService;
import pe.com.registro2026.service.ProveedorService;

@Controller
public class ProveedorController {

	//Inyeccion de dependencias
	@Autowired
	private ProveedorService servicio;
	
	@Autowired
	private DistritoService servdis;
	
	@GetMapping("/proveedor/mostrar")
	public String MostrarProveedor(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search
	) {
		Page<ProveedorEntity> proveedorPage = servicio.findAllCustom(search, page, size);

		modelo.addAttribute("listaproveedor", proveedorPage.getContent());
		modelo.addAttribute("currentPage", proveedorPage.getNumber());
		modelo.addAttribute("totalPages", proveedorPage.getTotalPages());
		modelo.addAttribute("size", proveedorPage.getSize());
	    modelo.addAttribute("textoBuscado", search);
		return "proveedor/mostrar_proveedor";
	}
	
	@GetMapping("/proveedor/actualiza/{id}")
	public String MostrarActualizarProveedor(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listaproveedor", servicio.findById(id));
		modelo.addAttribute("listadistrito", servdis.findAll());
		return "proveedor/actualizar_proveedor";
	}
	
	@GetMapping("/proveedor/habilita")
	public String MostrarHabilitarProveedor(Model modelo) {
		modelo.addAttribute("listaproveedor",servicio.findAll());
		
		return "proveedor/habilitar_proveedor";
	}
	
	//Acciones -> GET 
	@GetMapping("/proveedor/eliminar/{id}")
	public String EliminarProveedor(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/proveedor/mostrar";
	}
	
	//Acciones -> GET 
	@GetMapping("/proveedor/habilitar/{id}")
	public String HabilitarProveedor(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/proveedor/habilita";
	}
	
	@GetMapping("/proveedor/deshabilitar/{id}")
	public String DeshabilitarProveedor(@PathVariable("id") Long id) {
	    servicio.delete(id);
	    return "redirect:/proveedor/habilita";
	}
	
	@GetMapping("/proveedor/registro")
	public String MostrarRegistroProveedor(Model modelo) {
		modelo.addAttribute("listadistrito", servdis.findAll());
		return "proveedor/registrar_proveedor";
	}
	
	//Modelo -> transporta la informacion
	@ModelAttribute("proveedor")
	public ProveedorEntity ModeloProveedor() {
		return new ProveedorEntity();
	}
	//ACCIONES -> POST
	@PostMapping("/proveedor/registrar")
	public String RegistrarProveedor(@ModelAttribute("proveedor") ProveedorEntity obj) {
		servicio.add(obj);
		return "redirect:/proveedor/mostrar";
	}
	
	//ACCIONES -> POST
	@PostMapping("/proveedor/actualizar/{id}")
	public String ActualizarProveedor(@ModelAttribute("proveedor") ProveedorEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/proveedor/mostrar";
	}
}
