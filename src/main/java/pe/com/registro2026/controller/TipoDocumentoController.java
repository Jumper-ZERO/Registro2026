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
import pe.com.registro2026.entity.TipoDocumentoEntity;
import pe.com.registro2026.service.TipoDocumentoService;

@Controller
public class TipoDocumentoController {

	// Inyeccion de dependencias
	@Autowired
	private TipoDocumentoService servicio;

	@GetMapping("/tipodocumento/mostrar")
	public String MostrarTipoDocumento(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search) {
		Page<TipoDocumentoEntity> tipoDocumentoPage = servicio.query(search, page, size, RecordState.ACTIVE);

		modelo.addAttribute("listatipodocumento", tipoDocumentoPage.getContent());
		modelo.addAttribute("currentPage", tipoDocumentoPage.getNumber());
		modelo.addAttribute("totalPages", tipoDocumentoPage.getTotalPages());
		modelo.addAttribute("size", tipoDocumentoPage.getSize());
		modelo.addAttribute("textoBuscado", search);
		return "tipodocumento/mostrar_tipodocumento";
	}

	@GetMapping("/tipodocumento/registro")
	public String MostrarRegistroTipoDocumento(Model modelo) {
		return "tipodocumento/registrar_tipodocumento";
	}

	@GetMapping("/tipodocumento/actualiza/{id}")
	public String MostrarActualizarTipoDocumento(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listatipodocumento", servicio.findById(id));
		return "tipodocumento/actualizar_tipodocumento";
	}

	@GetMapping("/tipodocumento/habilita")
	public String MostrarHabilitarListaDocumento(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String search) {
		Page<TipoDocumentoEntity> tipoDocumentoPage = servicio.query(search, page, size, RecordState.ALL);

		modelo.addAttribute("listatipodocumento", tipoDocumentoPage.getContent());
		modelo.addAttribute("currentPage", tipoDocumentoPage.getNumber());
		modelo.addAttribute("totalPages", tipoDocumentoPage.getTotalPages());
		modelo.addAttribute("size", tipoDocumentoPage.getSize());
		modelo.addAttribute("textoBuscado", search);
		return "tipodocumento/habilitar_tipodocumento";
	}

	// Acciones -> GET
	@GetMapping("/tipodocumento/habilitar/{id}")
	public String HabilitarTipoDocumento(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/tipodocumento/habilita";
	}

	@GetMapping("/tipodocumento/deshabilitar/{id}")
	public String DeshabilitarTipoDocumento(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/tipodocumento/habilita";
	}

	// Acciones -> GET
	@GetMapping("/tipodocumento/eliminar/{id}")
	public String EliminarTipoDocumento(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/tipodocumento/mostrar";
	}

	// Modelo -> transporta la informacion
	@ModelAttribute("tipodocumento")
	public TipoDocumentoEntity ModeloTipoDocumento() {
		return new TipoDocumentoEntity();
	}

	// ACCIONES -> POST
	@PostMapping("/tipodocumento/registrar")
	public String RegistrarTipoDocumento(@ModelAttribute("tipodocumento") TipoDocumentoEntity obj) {
		servicio.add(obj);
		return "redirect:/tipodocumento/mostrar";
	}

	// ACCIONES -> POST
	@PostMapping("/tipodocumento/actualizar/{id}")
	public String ActualizarTipoDocumento(@ModelAttribute("tipodocumento") TipoDocumentoEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/tipodocumento/mostrar";
	}
}
