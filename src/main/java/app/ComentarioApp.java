package app;

import static spark.Spark.*;

import model.Comentario;
import service.ComentarioService;

import java.sql.Date;

public class ComentarioApp {
	  private static ComentarioService comentarioService = new ComentarioService();

	    public static void main(String[] args) {

	        post("/Comentario", (request, response) -> comentarioService.add(request, response));

	        get("/Comentario/:id", (request, response) -> comentarioService.get(request, response));

	        get("/Comentario/update/:id", (request, response) -> comentarioService.update(request, response));

	        get("/Comentario/delete/:id", (request, response) -> comentarioService.remove(request, response));

	        get("/Comentario", (request, response) -> comentarioService.getAll(request, response));

			Date d = new Date(365726354);
			Comentario c = new Comentario("Oi", d,1,1);
			comentarioService.comentarioDAO.conectar();
			comentarioService.comentarioDAO.inserirComentario(c);

	    }
}
