package app;

import model.Aplicativo;
import model.Comentario;
import model.Usuario;
import service.AplicativoService;
import service.ComentarioService;
import service.UsuarioService;
import spark.Filter;

import java.sql.Date;

import static spark.Spark.*;

public class App {
    private static AplicativoService aplicativoService = new AplicativoService();
    private static ComentarioService comentarioService = new ComentarioService();
    private static UsuarioService usuarioService = new UsuarioService();

    public static void main(String[] args) {
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });
        post("/Aplicativo", (request, response) -> aplicativoService.add(request, response));

        get("/Aplicativo/:id", (request, response) -> aplicativoService.get(request, response));

        get("/Aplicativo/update/:id", (request, response) -> aplicativoService.update(request, response));

        get("/Aplicativo/delete/:id", (request, response) -> aplicativoService.remove(request, response));

        get("/Aplicativo", (request, response) -> aplicativoService.getAll(request, response));

        post("/Comentario", (request, response) -> comentarioService.add(request, response));

        get("/Comentario/:id", (request, response) -> comentarioService.get(request, response));

        get("/Comentario/update/:id", (request, response) -> comentarioService.update(request, response));

        get("/Comentario/delete/:id", (request, response) -> comentarioService.remove(request, response));

        get("/Comentario", (request, response) -> comentarioService.getAll(request, response));

        post("/Usuario", (request, response) -> usuarioService.add(request, response));

        get("/Usuario/:id", (request, response) -> usuarioService.get(request, response));

        get("/Usuario/update/:id", (request, response) -> usuarioService.update(request, response));

        get("/Usuario/delete/:id", (request, response) -> usuarioService.remove(request, response));

        get("/Usuario", (request, response) -> usuarioService.getAll(request, response));

        aplicativoService.aplicativoDAO.conectar();
        comentarioService.comentarioDAO.conectar();
        usuarioService.usuarioDAO.conectar();

        Usuario a = new Usuario(3,"Ka","cal","cal");
        usuarioService.usuarioDAO.atualizarUsuario(a);

    }
}
