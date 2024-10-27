package com.jt.fpltracker.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class JsonUtils {

    public static JsonNode getSubsetOfJsonFields(final JsonNode originalNode, final List<String> fields) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode subsetNode = mapper.createObjectNode();

        for (final String field : fields) {
            if (originalNode.get(field) != null) {
                JsonNode newNode = originalNode.get(field);

                subsetNode.set(field, newNode);
            }
        }
        return subsetNode;
    }
}
