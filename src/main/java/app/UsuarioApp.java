package app;

import static spark.Spark.*;

import model.Usuario;
import service.UsuarioService;

public class UsuarioApp {
	  private static UsuarioService usuarioService = new UsuarioService();

	    public static void main(String[] args) {

	        post("/Usuario", (request, response) -> usuarioService.add(request, response));

	        get("/Usuario/:id", (request, response) -> usuarioService.get(request, response));

	        get("/Usuario/update/:id", (request, response) -> usuarioService.update(request, response));

	        get("/Usuario/delete/:id", (request, response) -> usuarioService.remove(request, response));

	        get("/Usuario", (request, response) -> usuarioService.getAll(request, response));

	    }
}
