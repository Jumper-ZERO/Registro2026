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
import pe.com.registro2026.entity.CategoriaEntity;
import pe.com.registro2026.service.CategoriaService;

@Controller
public class CategoriaController {

	// Inyeccion de dependencias
	@Autowired
	private CategoriaService servicio;

	// RUTAS -> GET
	// creamos una ruta para mostrar categoria

	@GetMapping("/categoria/mostrar")
	public String MostrarCategoria(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search) {

		Page<CategoriaEntity> categoriaPage = servicio.query(search, page, size, RecordState.ACTIVE);

		modelo.addAttribute("listacategoria", categoriaPage.getContent());
		modelo.addAttribute("currentPage", categoriaPage.getNumber());
		modelo.addAttribute("totalPages", categoriaPage.getTotalPages());
		modelo.addAttribute("size", categoriaPage.getSize());
		modelo.addAttribute("textoBuscado", search);
		return "categoria/mostrar_categoria";
	}

	@GetMapping("/categoria/registro")
	public String MostrarRegistroCategoria(Model modelo) {
		return "categoria/registrar_categoria";
	}

	@GetMapping("/categoria/actualiza/{id}")
	public String MostrarActualizarCategoria(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listacategoria", servicio.findById(id));
		return "categoria/actualizar_categoria";
	}

	@GetMapping("/categoria/habilita")
	public String MostrarHabilitarCategoria(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search) {

		Page<CategoriaEntity> categoriaPage = servicio.query(search, page, size, RecordState.ALL);

		modelo.addAttribute("listacategoria", categoriaPage.getContent());
		modelo.addAttribute("currentPage", categoriaPage.getNumber());
		modelo.addAttribute("totalPages", categoriaPage.getTotalPages());
		modelo.addAttribute("size", categoriaPage.getSize());
		modelo.addAttribute("textoBuscado", search);
		return "categoria/habilitar_categoria";
	}

	// Acciones -> GET
	@GetMapping("/categoria/eliminar/{id}")
	public String EliminarCategoria(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/categoria/mostrar";
	}

	// Acciones -> GET
	@GetMapping("/categoria/habilitar/{id}")
	public String HabilitarCategoria(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/categoria/habilita";
	}

	@GetMapping("/categoria/deshabilitar/{id}")
	public String DeshabilitarCategoria(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/categoria/habilita";
	}

	// Modelo -> transporta la informacion
	@ModelAttribute("categoria")
	public CategoriaEntity ModeloCategoria() {
		return new CategoriaEntity();
	}

	// ACCIONES -> POST
	@PostMapping("/categoria/registrar")
	public String RegistrarCategoria(@ModelAttribute("categoria") CategoriaEntity obj) {
		servicio.add(obj);
		return "redirect:/categoria/mostrar";
	}

	// ACCIONES -> POST
	@PostMapping("/categoria/actualizar/{id}")
	public String ActualizarCategoria(@ModelAttribute("categoria") CategoriaEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/categoria/mostrar";
	}
}
