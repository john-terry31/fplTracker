package com.jt.fpltracker.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import static com.jt.fpltracker.util.URLs.BASE_URL;
import static com.jt.fpltracker.util.URLs.BOOTSTRAP;

@Controller
@Slf4j
public class FplDataPullController {

    final RestTemplate restTemplate = new RestTemplate();

    final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/")
    public String getHelloWorld() {
        log.info("Hello World");
        return null;
    }

    @GetMapping("/test-bootstrap")
    public ResponseEntity<String> testBootstrap() throws JsonProcessingException {
        String bootstrapURL = BASE_URL + BOOTSTRAP;
        ResponseEntity<String> bootstrapResponse = restTemplate.getForEntity(bootstrapURL, String.class);

        log.info(objectMapper.readTree(bootstrapResponse.getBody()).toPrettyString());
        return bootstrapResponse;
    }
}
