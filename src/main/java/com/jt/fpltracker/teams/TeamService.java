package com.jt.fpltracker.teams;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jt.fpltracker.connection.FplDataPullService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class TeamService {

    private final FplDataPullService fplDataPullService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public TeamService(FplDataPullService fplDataPullService) {
        this.fplDataPullService = fplDataPullService;
    }

    public ObjectNode getTeamInfo(final String teamShortName) throws JsonProcessingException {
        AtomicInteger teamTotalPoints = new AtomicInteger(0);

        JsonNode teamsBootstrapInfo = fplDataPullService.getBootstrapStatsTree().get("teams");

//        teamsBootstrapInfo.fields().forEachRemaining(entry -> teamJsonKeys.add(Integer.valueOf(entry.getKey())));

        // get team id from bootstrap data using shortname - e.g. "BRE"
        int teamId = 0;
        for (int i = 1; i <= 20; i++) {
            if (teamsBootstrapInfo.get(i).get("short_name").asText().equals(teamShortName.toUpperCase())) {
                teamId = teamsBootstrapInfo.get(i).get("id").asInt();
                log.info("Found team {}: {}: {}", teamId, teamShortName, teamsBootstrapInfo.get(i).get("name").asText());
                break;
            }
        }

        // loop through fixtures to see if team is involved
        JsonNode fixtures = fplDataPullService.getFixturesList();
        int finalTeamId = teamId;
        fixtures.elements().forEachRemaining(fixture -> {
            if (fixture.get("started").asBoolean()) {
                int homeScore = fixture.get("team_h_score").asInt();
                int awayScore = fixture.get("team_a_score").asInt();

                // Is our team playing?
                if (fixture.get("team_h").asInt() == finalTeamId) {
                    if (homeScore > awayScore) {
                        // 3 points for win
                        teamTotalPoints.getAndAdd(3);
                    } else if (homeScore == awayScore) {
                        // 1 for draw
                        teamTotalPoints.getAndAdd(1);
                    }
                } else if (fixture.get("team_a").asInt() == finalTeamId) {
                    if (awayScore > homeScore) {
                        // 3 points for win
                        teamTotalPoints.getAndAdd(3);
                    } else if (homeScore == awayScore) {
                        // 1 for draw
                        teamTotalPoints.getAndAdd(1);
                    }
                }
            }
        });

        ObjectNode teamInfo = objectMapper.createObjectNode();
        teamInfo.put("teamId", teamId);
        teamInfo.put("teamShortName", teamShortName);
        teamInfo.put("teamTotalPoints", teamTotalPoints.get());
        return teamInfo;
    }
}
