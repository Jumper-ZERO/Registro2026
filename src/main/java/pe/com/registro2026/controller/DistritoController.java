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

import pe.com.registro2026.entity.DistritoEntity;
import pe.com.registro2026.service.DistritoService;

@Controller
public class DistritoController {

	// Inyeccion de dependencias
	@Autowired
	private DistritoService servicio;

	// RUTAS -> GET
	// creamos una ruta para mostrar distrito

	@GetMapping("/distrito/mostrar")
	public String MostrarDistrito(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search
		) {

		Page<DistritoEntity> distritoPage = servicio.findAllCustom(search, page, size);

		modelo.addAttribute("listadistrito", distritoPage.getContent());
		modelo.addAttribute("currentPage", distritoPage.getNumber());
		modelo.addAttribute("totalPages", distritoPage.getTotalPages());
		modelo.addAttribute("size", distritoPage.getSize());
	    modelo.addAttribute("textoBuscado", search);
		return "distrito/mostrar_distrito";
	}

	@GetMapping("/distrito/registro")
	public String MostrarRegistroDistrito(Model modelo) {
		return "distrito/registrar_distrito";
	}

	@GetMapping("/distrito/actualiza/{id}")
	public String MostrarActualizarDistrito(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listadistrito", servicio.findById(id));
		return "distrito/actualizar_distrito";
	}

	@GetMapping("/distrito/habilita")
	public String MostrarHabilitarDistrito(Model modelo) {
		modelo.addAttribute("listadistrito", servicio.findAll());
		return "distrito/habilitar_distrito";
	}

	// Acciones -> GET
	@GetMapping("/distrito/eliminar/{id}")
	public String EliminarDistrito(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/distrito/mostrar";
	}

	// Acciones -> GET
	@GetMapping("/distrito/habilitar/{id}")
	public String HabilitarDistrito(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/distrito/habilita";
	}

	@GetMapping("/distrito/deshabilitar/{id}")
	public String DeshabilitarDistrito(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/distrito/habilita";
	}

	// Modelo -> transporta la informacion
	@ModelAttribute("distrito")
	public DistritoEntity ModeloDistrito() {
		return new DistritoEntity();
	}

	// ACCIONES -> POST
	@PostMapping("/distrito/registrar")
	public String RegistrarDistrito(@ModelAttribute("distrito") DistritoEntity obj) {
		servicio.add(obj);
		return "redirect:/distrito/mostrar";
	}

	// ACCIONES -> POST
	@PostMapping("/distrito/actualizar/{id}")
	public String ActualizarDistrito(@ModelAttribute("distrito") DistritoEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/distrito/mostrar";
	}
}
