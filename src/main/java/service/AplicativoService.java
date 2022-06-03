package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.AplicativoDAO;
import model.Aplicativo;
import spark.Request;
import spark.Response;


public class AplicativoService {

	public AplicativoDAO aplicativoDAO;

	public AplicativoService() {
		aplicativoDAO = new AplicativoDAO();
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		String descricao = request.queryParams("descrição");
		String fonte = request.queryParams("fonte");
        String tutorial = request.queryParams("tutorial");
        String imagem = request.queryParams("imagem");
        float nota = Float.parseFloat(request.queryParams("nota"));
        String dispositivo = request.queryParams("dispositivo");
        boolean pago = Boolean.parseBoolean(request.queryParams("pago"));
        String dicas = request.queryParams("dicas");
		String categoria = request.queryParams("categoria");
        

		Aplicativo aplicativo = new Aplicativo(nome, descricao, fonte, tutorial, imagem, nota, dispositivo, pago, dicas, categoria);

		aplicativoDAO.inserirAplicativo(aplicativo);

		response.status(201); // 201 Created
		return aplicativo;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Aplicativo aplicativo = (Aplicativo) aplicativoDAO.lerAplicativo(id);
		
		if (aplicativo != null) {
    	    response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");

            return aplicativo.toJSON();
        } else {
            response.status(404); // 404 Not found
            return "Aplicativo " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Aplicativo aplicativo = (Aplicativo) aplicativoDAO.lerAplicativo(id);

        if (aplicativo != null) {
        	aplicativo.setNome(request.queryParams("Nome"));
        	aplicativo.setDescricao(request.queryParams("Descrição"));
			aplicativo.setFonte(request.queryParams("Fonte"));
            aplicativo.setTutorial(request.queryParams("Tutorial"));
            aplicativo.setImagem(request.queryParams("Imagem"));
            aplicativo.setNota(Float.parseFloat(request.queryParams("Nota")));
            aplicativo.setDispositivo(request.queryParams("Dispositivo"));
            aplicativo.setPago(Boolean.parseBoolean(request.queryParams("Pago")));
            aplicativo.setDicas(request.queryParams("Dicas"));
			aplicativo.setCategoria(request.queryParams("Categoria"));


        	aplicativoDAO.atualizarAplicativo(aplicativo);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Aplicativo não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Aplicativo aplicativo = (Aplicativo) aplicativoDAO.lerAplicativo(id);

        if (aplicativo != null) {

            aplicativoDAO.excluirAplicativo(aplicativo.getId());

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Aplicativo não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		int i = 0;
		StringBuffer returnValue = new StringBuffer("[");
		for (Aplicativo aplicativo : aplicativoDAO.getAplicativo()) {
			returnValue.append(aplicativo.toJSON());
			i++;
			if(!(i == aplicativoDAO.getAplicativo().length)) {
				returnValue.append(",\n");
			}
		}
		returnValue.append("]");
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}