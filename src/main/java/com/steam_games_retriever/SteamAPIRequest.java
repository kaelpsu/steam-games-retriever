package com.steam_games_retriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SteamAPIRequest {

    public static List<Game> getGames(String apiKey, String accountId) {
        String host = "api.steampowered.com";
        int port = 80;
        ObjectMapper objectMapper = new ObjectMapper(); // For JSON deserialization

        try (   Socket socket = new Socket(InetAddress.getByName(host), port); 
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Sending an HTTP GET request manually
            out.println("GET /IPlayerService/GetOwnedGames/v0001/?key=" + apiKey + "&steamid=" + accountId + "&format=json&include_appinfo=true&include_played_free_games=true HTTP/1.1");
            out.println("Host: " + host);
            out.println("Connection: close"); // Indicate that we want to close the connection after the response
            out.println(); // Blank line to indicate end of the request headers

            // Reading the response
            StringBuilder jsonResponse = new StringBuilder();
            String inputLine;
            boolean bodyStarted = false;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.isEmpty()) {
                    // Header and body are separated by a blank line
                    bodyStarted = true;
                }
                if (bodyStarted) {
                    jsonResponse.append(inputLine);
                }
            }

            // Deserialize JSON response into Java objects
            try {
                String jsonstring = jsonResponse.toString();
                String response = jsonstring.substring(8, jsonstring.length() - 8);

                System.out.println("JSON Response: " + response);
                
                Root root = objectMapper.readValue(response, Root.class);
                List<Game> games = root.getResponse().getGames();
                Collections.sort(games, Collections.reverseOrder()); // Sort games by playtime

                // games.forEach(System.out::println); // Print each game on terminal

                return games;
            } catch (IOException e) {
                System.err.println("Error processing JSON: " + e.getMessage());
                return null;
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            return null;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            return null;
        }
    }
}
