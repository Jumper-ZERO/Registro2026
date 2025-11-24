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
import pe.com.registro2026.entity.SexoEntity;
import pe.com.registro2026.service.SexoService;

@Controller
public class SexoController {

	// Inyeccion de dependencias
	@Autowired
	private SexoService servicio;

	// creamos una ruta para mostrar Sexo

	@GetMapping("/sexo/mostrar")
	public String MostrarSexo(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search) {

		Page<SexoEntity> sexoPage = servicio.query(search, page, size, RecordState.ACTIVE);

		modelo.addAttribute("listasexo", sexoPage.getContent());
		modelo.addAttribute("currentPage", sexoPage.getNumber());
		modelo.addAttribute("totalPages", sexoPage.getTotalPages());
		modelo.addAttribute("size", sexoPage.getSize());
		modelo.addAttribute("textoBuscado", search);
		return "sexo/mostrar_sexo";
	}

	@GetMapping("/sexo/registro")
	public String MostrarRegistroSexo(Model modelo) {
		return "sexo/registrar_sexo";
	}

	@GetMapping("/sexo/actualiza/{id}")
	public String MostrarActualizarSexo(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listasexo", servicio.findById(id));
		return "sexo/actualizar_sexo";
	}

	@GetMapping("/sexo/habilita")
	public String MostrarHabilitarSexo(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search) {

		Page<SexoEntity> sexoPage = servicio.query(search, page, size, RecordState.ALL);

		modelo.addAttribute("listasexo", sexoPage.getContent());
		modelo.addAttribute("currentPage", sexoPage.getNumber());
		modelo.addAttribute("totalPages", sexoPage.getTotalPages());
		modelo.addAttribute("size", sexoPage.getSize());
		modelo.addAttribute("textoBuscado", search);
		return "sexo/habilitar_sexo";
	}

	// Acciones -> GET
	@GetMapping("/sexo/habilitar/{id}")
	public String HabilitarSexo(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/sexo/habilita";
	}

	// Acciones -> GET
	@GetMapping("/sexo/eliminar/{id}")
	public String EliminarSexo(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/sexo/mostrar";
	}

	@GetMapping("/sexo/deshabilitar/{id}")
	public String DeshabilitarSexo(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/sexo/habilita";
	}

	// Modelo -> transporta la informacion
	@ModelAttribute("sexo")
	public SexoEntity ModeloSexo() {
		return new SexoEntity();
	}

	// ACCIONES -> POST
	@PostMapping("/sexo/registrar")
	public String RegistrarSexo(@ModelAttribute("sexo") SexoEntity obj) {
		servicio.add(obj);
		return "redirect:/sexo/mostrar";
	}

	// ACCIONES -> POST
	@PostMapping("/sexo/actualizar/{id}")
	public String ActualizarSexo(@ModelAttribute("sexo") SexoEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/sexo/mostrar";
	}
}
