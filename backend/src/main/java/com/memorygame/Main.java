package com.memorygame;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create(cfg -> cfg.bundledPlugins
                        .enableCors(cors -> cors
                                .addRule(it -> it.allowHost("http://localhost:5173", "http://localhost:63342"))))

                .get("/api/cards", ctx -> {
                    List<Card> cards = generateCards();
                    ctx.json(cards);
                })
                //websockets
                .ws("/ws/connect", new WsHandler())
                .start(8080);

    }


    private static List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            cards.add(new Card(i, "card" + i + "-1"));
            cards.add(new Card(i, "card" + i + "-2"));
        }

        Collections.shuffle(cards);

        for (int i = 0; i < cards.size(); i++) {
            int col = i / 4;
            int row = i % 4;
            cards.get(i).setPosition(row, col);
        }

        return cards;
    }

    record HelloWorld(String greeting, String name) {
    }
}