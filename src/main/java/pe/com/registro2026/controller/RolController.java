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

import pe.com.registro2026.entity.RolEntity;
import pe.com.registro2026.service.RolService;

@Controller
public class RolController {

	// Inyeccion de dependencias
	@Autowired
	private RolService servicio;

	@GetMapping("/rol/mostrar")
	public String MostrarRol(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search) {
		Page<RolEntity> rolPage = servicio.findAllCustom(search, page, size);

		modelo.addAttribute("listarol", rolPage.getContent());
		modelo.addAttribute("currentPage", rolPage.getNumber());
		modelo.addAttribute("totalPages", rolPage.getTotalPages());
		modelo.addAttribute("size", rolPage.getSize());
		modelo.addAttribute("textoBuscado", search);
		return "rol/mostrar_rol";
	}

	@GetMapping("/rol/registro")
	public String MostrarRegistroRol(Model modelo) {
		return "rol/registrar_rol";
	}

	@GetMapping("/rol/actualiza/{id}")
	public String MostrarRegistroRol(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listarol", servicio.findById(id));
		return "rol/actualizar_rol";
	}

	@GetMapping("/rol/habilita")
	public String MostrarHabilitarRol(Model modelo) {
		modelo.addAttribute("listarol", servicio.findAll());
		return "rol/habilitar_rol";
	}

	// Acciones -> GET
	@GetMapping("/rol/habilitar/{id}")
	public String HabilitarRol(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/rol/habilita";
	}

	@GetMapping("/rol/deshabilitar/{id}")
	public String DeshabilitarRol(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/rol/habilita";
	}

	// Acciones -> GET
	@GetMapping("/rol/eliminar/{id}")
	public String EliminarRol(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/rol/mostrar";
	}

	// Modelo -> transporta la informacion
	@ModelAttribute("rol")
	public RolEntity ModeloRol() {
		return new RolEntity();
	}

	// ACCIONES -> POST
	@PostMapping("/rol/registrar")
	public String RegistrarRol(@ModelAttribute("rol") RolEntity obj) {
		servicio.add(obj);
		return "redirect:/rol/mostrar";
	}

	// ACCIONES -> POST
	@PostMapping("/rol/actualizar/{id}")
	public String ActualizarRol(@ModelAttribute("rol") RolEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/rol/mostrar";
	}
}
