package com.laba.solvd.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.laba.solvd.domain.enums.FlightType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlightTypeDeserializer extends JsonDeserializer<List<FlightType>> {
    @Override
    public List<FlightType> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        List<FlightType> flightTypes = new ArrayList<>();
        JsonNode arrayNode = jsonParser.getCodec().readTree(jsonParser);
        Iterator<JsonNode> itr = arrayNode.elements();
        while (itr.hasNext()) {
            String flightType = itr.next().get("typeName").asText();
            flightTypes.add(FlightType.valueOf(flightType.toUpperCase()));
        }
        return flightTypes;
    }
}
