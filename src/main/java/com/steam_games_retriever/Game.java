package com.steam_games_retriever;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game implements Comparable<Game> {
    private int appid;
    private String name;
    private int playtime_2weeks;
    private int playtime_forever;

    @JsonProperty("appid")
    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("playtime_2weeks")
    public int getPlaytime2Weeks() {
        return playtime_2weeks;
    }

    public void setPlaytime2Weeks(int playtime_2weeks) {
        this.playtime_2weeks = playtime_2weeks;
    }

    @JsonProperty("playtime_forever")
    public int getPlaytimeForever() {
        return playtime_forever;
    }

    public void setPlaytimeForever(int playtime_forever) {
        this.playtime_forever = playtime_forever;
    }

    @Override
    public String toString() {
        return "Game{" +
                "appid=" + appid +
                ", name='" + name + '\'' +
                ", playtime2Weeks=" + playtime_2weeks +
                ", playtimeForever=" + playtime_forever +
                '}';
    }

    @Override
    public int compareTo(Game game) {
        return getPlaytimeForever() - game.getPlaytimeForever();
    }
}

