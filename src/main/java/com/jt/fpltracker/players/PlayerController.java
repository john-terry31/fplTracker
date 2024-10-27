package com.jt.fpltracker.players;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<JsonNode> getPlayerAllInfo(@PathVariable final int id) throws JsonProcessingException {
        log.info("Get player from id {}", id);
        JsonNode playerInfo = playerService.getPlayerAllInfo(id);
        return new ResponseEntity<>(playerInfo, HttpStatus.OK);
    }

    @GetMapping("/{id}/basic")
    private ResponseEntity<JsonNode> getBasicPlayerInfo(@PathVariable final int id) throws JsonProcessingException {
        JsonNode basicPlayerInfo = playerService.getBasicPlayerInfo(id);
        return new ResponseEntity<>(basicPlayerInfo, HttpStatus.OK);
    }

    @GetMapping("/{id}/fixtures")
    private ResponseEntity<JsonNode> getPlayerFixturesInfo(@PathVariable final int id) throws JsonProcessingException {
        JsonNode basicPlayerInfo = playerService.getPlayerFixturesInfo(id);
        return new ResponseEntity<>(basicPlayerInfo, HttpStatus.OK);
    }
}
