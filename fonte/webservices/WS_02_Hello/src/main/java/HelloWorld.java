import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        port(6789);

        get("/hello", (request, response) -> "<h1>Nosso primeiro Web Service!</h1>");

        post("/hello", (request, response) ->
            "Web Server recebeu: " + request.body()
        );

        get("/private", (request, response) -> {
            response.status(401);
            return "Área privada!!!";
        });

        get("/users/:name", (request, response) -> "Usuário selecionado: " + request.params(":name"));

        get("/news/:section", (request, response) -> {
            response.type("text/xml");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><news>" + request.params("section") + "</news>";
        });

        get("/protected", (request, response) -> {
            halt(403, "I don't think so!!!");
            return null;
        });

        get("/redirect", (request, response) -> {
            response.redirect("/news/world");
            return null;
        });

        get("/", (request, response) -> "root");
        
    }
}