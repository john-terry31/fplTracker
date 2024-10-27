package com.jt.fpltracker.players;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jt.fpltracker.connection.FplDataPullService;
import com.jt.fpltracker.util.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class PlayerService {

    private final FplDataPullService fplDataPullService;

    public PlayerService(FplDataPullService fplDataPullService) {
        this.fplDataPullService = fplDataPullService;
    }

    public JsonNode getPlayerAllInfo(final int id) throws JsonProcessingException {
        final JsonNode jsonTree = fplDataPullService.getBootstrapStatsTree();

        return jsonTree.get("elements").get(id);
    }

    public JsonNode getBasicPlayerInfo(final int id) throws JsonProcessingException {
        final JsonNode player = fplDataPullService.getBootstrapStatsTree().get("elements").get(id);
        final List<String> basicFields = Arrays.asList("first_name","second_name");

        return JsonUtils.getSubsetOfJsonFields(player, basicFields);
    }

    public JsonNode getPlayerFixturesInfo(final int id) throws JsonProcessingException {
        final JsonNode player = fplDataPullService.getPlayerStats(id);
        final ObjectNode extraFields = (ObjectNode) getBasicPlayerInfo(id);
        final ObjectNode playerFixturesAndInfo = (ObjectNode) player;

        playerFixturesAndInfo.setAll(extraFields);
        return playerFixturesAndInfo;
    }
}
