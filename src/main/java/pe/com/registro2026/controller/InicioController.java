package pe.com.registro2026.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller -> esto define a la clase como controlador, un controlador es una clase que recibe las peticiones
//(request) las procesa y devuelve una respuesta (response)

@Controller
public class InicioController {
	//@GetMapping -> sirve para trabajar con rutas y algunas operaciones 
	//@PostMapping -> sirve para trabajar las acciones que generalmente son ejecutadas por un boton
	
	//creamos una ruta para el index
	@GetMapping
	public String MostrarInicio() {
		return "index";
	}
	
	//creamos una ruta para el menu principal
	@GetMapping("/mainmenu")
	public String MostrarMenuPrincipal() {
		return "mainmenu";
	}
}
