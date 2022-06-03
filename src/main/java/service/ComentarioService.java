package service;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;


import dao.ComentarioDAO;
import model.Comentario;
import spark.Request;
import spark.Response;


public class ComentarioService {

	public ComentarioDAO comentarioDAO;
	public SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");


	public ComentarioService() {
		comentarioDAO = new ComentarioDAO();
	}

	public Object add(Request request, Response response) {
		String texto = request.queryParams("texto");
		Date data = Date.valueOf(request.queryParams("data"));
		int aplicativo_id = Integer.parseInt(request.queryParams("aplicativo_id"));
		int user_id = Integer.parseInt(request.queryParams("user_id"));

		Comentario comentario = new Comentario(texto, data, aplicativo_id, user_id);

		comentarioDAO.inserirComentario(comentario);

		response.status(201); // 201 Created
		return comentario;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Comentario comentario = (Comentario) comentarioDAO.lerComentario(id);
		
		if (comentario != null) {
    	    response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");

            return comentario.toJSON();
        } else {
            response.status(404); // 404 Not found
            return "Comentario " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Comentario comentario = (Comentario) comentarioDAO.lerComentario(id);

        if (comentario != null) {
        	comentario.setTexto(request.queryParams("texto"));
        	comentario.setData(Date.valueOf(request.queryParams("data")));
			//Talvez não precise atualizar os ids já que o id do usuário ou do aplicativo não irá mudar

        	comentarioDAO.atualizarComentario(comentario);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Comentario não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Comentario comentario = (Comentario) comentarioDAO.lerComentario(id);

        if (comentario != null) {

            comentarioDAO.excluirComentario(comentario.getId());

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Comentario não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		int i = 0;
		StringBuffer returnValue = new StringBuffer("[");
		for (Comentario comentario : comentarioDAO.getComentarios()) {
			returnValue.append(comentario.toJSON());
			i++;
			if(!(i == comentarioDAO.getComentarios().length)) {
				returnValue.append(",\n");
			}
		}
		returnValue.append("]");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}