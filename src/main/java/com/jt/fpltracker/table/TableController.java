package com.jt.fpltracker.table;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table")
@Slf4j
public class TableController {

    @GetMapping("/current")
    private ResponseEntity<JsonNode> getCurrentTable() {
        return null;
    }
}
