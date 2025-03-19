package com.memorygame;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.websocket.WsConfig;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsMessageContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class WsHandler implements Consumer<WsConfig> {

    private static final Integer[] VALS = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};

    @Override
    public void accept(WsConfig wsConfig) {
        wsConfig.onConnect(this::handleConnect);
        wsConfig.onMessage(this::handleMessage);
    }

    private void handleMessage(WsMessageContext ws) throws JsonProcessingException {
        //parse message into DTO-ish object
        var wsMessage = ws.message();
        switch (wsMessage) {
            case "INIT_GAME" -> initGame(ws);
            default -> System.out.println("unknown operation");
        }
    }

    private void initGame(WsMessageContext ws) {
        var list = Arrays.asList(VALS);
        Collections.shuffle(list);
        System.out.println(list);
        ws.send(list);
    }

    record WsMessage(WsMessageType type) {}
    enum WsMessageType {INIT_GAME}

    private void handleConnect(WsConnectContext ws) {
        System.out.printf("Connection registered from %s%n", ws.host());
        ws.send("Client connected");
    }
}
