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

import pe.com.registro2026.entity.MarcaEntity;
import pe.com.registro2026.service.MarcaService;

@Controller
public class MarcaController {

	// Inyeccion de dependencias
	@Autowired
	private MarcaService servicio;

	// RUTAS -> GET
	// creamos una ruta para mostrar marca

	@GetMapping("/marca/mostrar")
	public String MostrarMarca(
			Model modelo,

			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search) {
		Page<MarcaEntity> marcaPage = servicio.findAllCustom(search, page, size);

		modelo.addAttribute("listamarca", marcaPage.getContent());
		modelo.addAttribute("currentPage", marcaPage.getNumber());
		modelo.addAttribute("totalPages", marcaPage.getTotalPages());
		modelo.addAttribute("size", marcaPage.getSize());
		modelo.addAttribute("textoBuscado", search);
		return "marca/mostrar_marca";
	}

	@GetMapping("/marca/registro")
	public String MostrarRegistroMarca(Model modelo) {
		return "marca/registrar_marca";
	}

	@GetMapping("/marca/actualiza/{id}")
	public String MostrarActualizarMarca(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listamarca", servicio.findById(id));
		return "marca/actualizar_marca";
	}

	@GetMapping("/marca/habilita")
	public String MostrarHabilitarMarca(Model modelo) {
		modelo.addAttribute("listamarca", servicio.findAll());
		return "marca/habilitar_marca";
	}

	// Acciones -> GET
	@GetMapping("/marca/eliminar/{id}")
	public String EliminarMarca(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/marca/mostrar";
	}

	// Acciones -> GET
	@GetMapping("/marca/habilitar/{id}")
	public String HabilitarMarca(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/marca/habilita";
	}

	@GetMapping("/marca/deshabilitar/{id}")
	public String DeshabilitarMarca(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/marca/habilita";
	}

	// Modelo -> transporta la informacion
	@ModelAttribute("marca")
	public MarcaEntity ModeloDistrito() {
		return new MarcaEntity();
	}

	// ACCIONES -> POST
	@PostMapping("/marca/registrar")
	public String RegistrarMarca(@ModelAttribute("marca") MarcaEntity obj) {
		servicio.add(obj);
		return "redirect:/marca/mostrar";
	}

	// ACCIONES -> POST
	@PostMapping("/marca/actualizar/{id}")
	public String ActualizarMarca(@ModelAttribute("marca") MarcaEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/marca/mostrar";
	}
}
