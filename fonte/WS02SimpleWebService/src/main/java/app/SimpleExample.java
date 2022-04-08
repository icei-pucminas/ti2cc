package app;

import static spark.Spark.*;

public class SimpleExample {

    public static void main(String[] args) {

        port(5678);

        get("/oi", (request, response) -> "Oi Mundo!");

        post("/oi", (request, response) ->  "Oi Mundo: " + request.body());

        get("/privado", (request, response) -> {
            response.status(401);
            return "Área privada!!!";
        });

        get("/usuarios/:nome", (request, response) -> "Usuário selecionado: " + request.params(":nome"));

        get("/novidades/:section", (request, response) -> {
            response.type("text/xml");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><novidades>" + request.params("section") + "</novidades>";
        });

        get("/protegido", (request, response) -> {
            halt(403, "Entrada não permitida!!!");
            return null;
        });

        get("/redirecionar", (request, response) -> {
            response.redirect("/novidades/mundo");
            return null;
        });

        get("/", (request, response) -> "root");
    }
}        