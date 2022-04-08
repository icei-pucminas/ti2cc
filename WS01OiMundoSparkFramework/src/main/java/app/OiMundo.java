package app;

import static spark.Spark.*;

public class OiMundo {
    public static void main(String[] arg){
        get("/oi", (request, response) -> "Oi Mundo!");
    }
}