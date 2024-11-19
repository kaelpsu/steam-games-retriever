package com.steam_games_retriever;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    private int gameCount;
    private List<Game> games;

    @JsonProperty("game_count")
    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    @JsonProperty("games")
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Response{" +
                "gameCount=" + gameCount +
                ", games=" + games +
                '}';
    }
}

