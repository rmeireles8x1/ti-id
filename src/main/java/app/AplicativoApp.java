package app;

import static spark.Spark.*;

import model.Aplicativo;
import service.AplicativoService;

public class AplicativoApp {
	  private static AplicativoService aplicativoService = new AplicativoService();

	    public static void main(String[] args) {

	        post("/Aplicativo", (request, response) -> aplicativoService.add(request, response));

	        get("/Aplicativo/:id", (request, response) -> aplicativoService.get(request, response));

	        get("/Aplicativo/update/:id", (request, response) -> aplicativoService.update(request, response));

	        get("/Aplicativo/delete/:id", (request, response) -> aplicativoService.remove(request, response));

	        get("/Aplicativo", (request, response) -> aplicativoService.getAll(request, response));


	    }
}