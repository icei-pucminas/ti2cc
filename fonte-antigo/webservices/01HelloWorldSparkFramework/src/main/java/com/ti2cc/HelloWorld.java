package com.ti2cc;

import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] arg){
        get("/hello", (request, response) -> "Hello World!");
    }
}