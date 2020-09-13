package app;
import static spark.Spark.*;

import service.BemDeConsumoService;


public class Aplicacao {
	
	private static BemDeConsumoService bemDeConsumoService = new BemDeConsumoService();
	
    public static void main(String[] args) {
        port(6789);

        staticFiles.location("/public");
        
        post("/bensdeconsumo", (request, response) -> bemDeConsumoService.add(request, response));

        get("/bensdeconsumo/:id", (request, response) -> bemDeConsumoService.get(request, response));

        put("/bensdeconsumo/:id", (request, response) -> bemDeConsumoService.update(request, response));

        delete("/bensdeconsumo/:id", (request, response) -> bemDeConsumoService.remove(request, response));

        get("/bensdeconsumo", (request, response) -> bemDeConsumoService.getAll(request, response));
        
        
    }
}