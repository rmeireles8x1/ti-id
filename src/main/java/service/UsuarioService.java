package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class UsuarioService {

	public UsuarioDAO usuarioDAO;

	public UsuarioService() {
		usuarioDAO = new UsuarioDAO();
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		String senha = request.queryParams("senha");
		String email = request.queryParams("email");

		Usuario usuario = new Usuario(nome, senha, email);

		usuarioDAO.inserirUsuario(usuario);

		response.status(201); // 201 Created
		return usuario;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Usuario usuario = (Usuario) usuarioDAO.lerUsuario(id);
		
		if (usuario != null) {
    	    response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");

            return usuario.toJSON();
        } else {
            response.status(404); // 404 Not found
            return "Usuario " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Usuario usuario = (Usuario) usuarioDAO.lerUsuario(id);

        if (usuario != null) {
        	usuario.setNome(request.queryParams("nome"));
        	usuario.setSenha(request.queryParams("senha"));
			usuario.setEmail(request.queryParams("email"));

        	usuarioDAO.atualizarUsuario(usuario);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Usuario usuario = (Usuario) usuarioDAO.lerUsuario(id);

        if (usuario != null) {

            usuarioDAO.excluirUsuario(usuario.getId());

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		int i = 0;
		StringBuffer returnValue = new StringBuffer("[");
		for (Usuario usuario : usuarioDAO.getUsuarios()) {
			returnValue.append(usuario.toJSON());
			i++;
			if(!(i == usuarioDAO.getUsuarios().length)) {
				returnValue.append(",\n");
			}
		}
		returnValue.append("]");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}