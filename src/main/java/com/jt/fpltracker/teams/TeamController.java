package com.jt.fpltracker.teams;

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
@RequestMapping("/teams")
@Slf4j
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{team}")
    public ResponseEntity<JsonNode> getTeamInfo(@PathVariable("team") String teamShortName)
            throws JsonProcessingException {
        JsonNode basicTeamInfo = teamService.getTeamInfo(teamShortName);
        return new ResponseEntity<>(basicTeamInfo, HttpStatus.OK);
    }
}
