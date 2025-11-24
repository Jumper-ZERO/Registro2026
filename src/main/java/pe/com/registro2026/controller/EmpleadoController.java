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
import pe.com.registro2026.entity.EmpleadoEntity;
import pe.com.registro2026.service.DistritoService;
import pe.com.registro2026.service.EmpleadoService;
import pe.com.registro2026.service.RolService;
import pe.com.registro2026.service.SexoService;
import pe.com.registro2026.service.TipoDocumentoService;

@Controller
public class EmpleadoController {

	//Inyeccion de dependencias
	@Autowired
	private EmpleadoService servicio;
	
	@Autowired
	private DistritoService servdis;
	
	@Autowired
	private RolService servrol;
	
	@Autowired
	private SexoService servsexo;
	
	@Autowired
	private TipoDocumentoService servtipd;
	
	@GetMapping("/empleado/mostrar")
	public String MostrarEmpleado(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "") String search
	) {
		Page<EmpleadoEntity> empleadoPage = servicio.query(search, page, size, RecordState.ACTIVE);

		modelo.addAttribute("listaempleado", empleadoPage.getContent());
		modelo.addAttribute("currentPage", empleadoPage.getNumber());
		modelo.addAttribute("totalPages", empleadoPage.getTotalPages());
		modelo.addAttribute("size", empleadoPage.getSize());
	    modelo.addAttribute("textoBuscado", search);
		return "empleado/mostrar_empleado";
	}
	
	@GetMapping("/empleado/actualiza/{id}")
	public String MostrarActualizarEmpleado(Model modelo, @PathVariable("id") Long id) {
		modelo.addAttribute("listaempleado", servicio.findById(id));
		modelo.addAttribute("listadistritos", servdis.findAll());
		modelo.addAttribute("listarol", servrol.findAll());
		modelo.addAttribute("listasexo", servsexo.findAll());
		modelo.addAttribute("listatipodocumento", servtipd.findAll());
		return "empleado/actualizar_empleado";
	}
	
	@GetMapping("/empleado/habilita")
	public String MostrarHabilitarEmpleado(
			Model modelo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "") String search
	) {
		Page<EmpleadoEntity> empleadoPage = servicio.query(search, page, size, RecordState.ALL);

		modelo.addAttribute("listaempleado", empleadoPage.getContent());
		modelo.addAttribute("currentPage", empleadoPage.getNumber());
		modelo.addAttribute("totalPages", empleadoPage.getTotalPages());
		modelo.addAttribute("size", empleadoPage.getSize());
	    modelo.addAttribute("textoBuscado", search);
		
		return "empleado/habilitar_empleado";
	}
	
	//Acciones -> GET 
	@GetMapping("/empleado/eliminar/{id}")
	public String EliminarEmpleado(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/empleado/mostrar";
	}
	
	//Acciones -> GET 
	@GetMapping("/empleado/habilitar/{id}")
	public String HabilitarEmpleado(@PathVariable("id") Long id) {
		servicio.enable(id);
		return "redirect:/empleado/habilita";
	}
	
	@GetMapping("/empleado/deshabilitar/{id}")
	public String DeshabilitarEmpleado(@PathVariable("id") Long id) {
	    servicio.delete(id);
	    return "redirect:/empleado/habilita";
	}
	
	@GetMapping("/empleado/registro")
	public String MostrarRegistroEmpleado(Model modelo) {
		modelo.addAttribute("listadistritos", servdis.findAll());
		modelo.addAttribute("listarol", servrol.findAll());
		modelo.addAttribute("listasexo", servsexo.findAll());
		modelo.addAttribute("listatipodocumento", servtipd.findAll());
		return "empleado/registrar_empleado";
	}
	
	//Modelo -> transporta la informacion
	@ModelAttribute("empleado")
	public EmpleadoEntity ModeloEmpleado() {
		return new EmpleadoEntity();
	}
	//ACCIONES -> POST
	@PostMapping("/empleado/registrar")
	public String RegistrarEmpleado(@ModelAttribute("empleado") EmpleadoEntity obj) {
		servicio.add(obj);
		return "redirect:/empleado/mostrar";
	}
	
	//ACCIONES -> POST
	@PostMapping("/empleado/actualizar/{id}")
	public String ActualizarEmpleado(@ModelAttribute("empleado") EmpleadoEntity obj,
			@PathVariable("id") Long id) {
		servicio.update(id, obj);
		return "redirect:/empleado/mostrar";
	}
}
