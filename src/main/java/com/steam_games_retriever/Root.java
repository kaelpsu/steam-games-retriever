package com.steam_games_retriever;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
    private Response response;

    @JsonProperty("response")
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Root{" +
                "response=" + response +
                '}';
    }
}

