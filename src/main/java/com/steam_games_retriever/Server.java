package com.steam_games_retriever;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Serve the HTML page on the root URL
        server.createContext("/", new HtmlPageController());

        // Handle POST requests to /games
        server.createContext("/games", new RequestGamesController());

        server.setExecutor(null); // Default executor
        System.out.println("Server running on http://localhost:8080");
        server.start();
    }
}

// Controller to serve the HTML page
class HtmlPageController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Received request: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());

        // Read the HTML file and send it as a response
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        byte[] htmlBytes = Files.readAllBytes(Paths.get("./src/main/webapp/index.html"));
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, htmlBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(htmlBytes);
        }
    }
}

class RequestGamesController implements HttpHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            String apiKey = exchange.getRequestHeaders().getFirst("API-Key");
            String accountId = exchange.getRequestHeaders().getFirst("Account-ID");
            if (apiKey == null || apiKey.isEmpty()) {
                sendResponse(exchange, "Missing API Key", 400);
                return;
            }

            // Simulate retrieving game data
            List<Game> games = SteamAPIRequest.getGames(apiKey, accountId);
            String jsonResponse = objectMapper.writeValueAsString(games);

            exchange.getResponseHeaders().add("Content-Type", "application/json");
            sendResponse(exchange, jsonResponse, 200);
        } else {
            sendResponse(exchange, "Method Not Allowed", 405);
        }
    }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
