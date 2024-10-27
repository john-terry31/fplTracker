package com.jt.fpltracker.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.jt.fpltracker.util.URLs.*;

@Service
@Slf4j
public class FplDataPullService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode getPlayerStats(final int id) throws JsonProcessingException {
        String playerUrl = BASE_URL + PLAYER_ALL_GWS.formatted(id);
        ResponseEntity<String> playerResponse = restTemplate.getForEntity(playerUrl, String.class);

        log.info(objectMapper.readTree(playerResponse.getBody()).toPrettyString());
        return objectMapper.readTree(playerResponse.getBody());
    }

    public JsonNode getBootstrapStatsTree() throws JsonProcessingException {
        String bootstrapURL = BASE_URL + BOOTSTRAP;
        ResponseEntity<String> bootstrapResponse = restTemplate.getForEntity(bootstrapURL, String.class);

        return objectMapper.readTree(bootstrapResponse.getBody());
    }

    public JsonNode getFixturesList() throws JsonProcessingException {
        String fixturesUrl = BASE_URL + FIXTURES;
        ResponseEntity<String> fixturesResponse = restTemplate.getForEntity(fixturesUrl, String.class);

        return objectMapper.readTree(fixturesResponse.getBody());
    }
}
