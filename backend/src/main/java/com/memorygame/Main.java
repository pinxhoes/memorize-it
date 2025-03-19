package org.example;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create(cfg -> cfg.bundledPlugins
                        .enableCors(cors -> cors
                                .addRule(it -> it.allowHost("http://localhost:5173"))))
                .get("/api/hello", ctx -> ctx.json(new HelloWorld("Hello", "You")))
                .start(8080);
    }

    record HelloWorld(String greeting, String name) {
    }
}