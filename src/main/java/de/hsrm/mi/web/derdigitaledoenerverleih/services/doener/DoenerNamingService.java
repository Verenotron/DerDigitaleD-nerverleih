package de.hsrm.mi.web.derdigitaledoenerverleih.services.doener;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class DoenerNamingService implements DoenerNamingServiceImpl{

    private final WebClient webClient = WebClient.create("https://pokeapi.co/api/v2/pokemon/");

    @Override
    public String getName() {
        int id = new Random().nextInt(1000) + 1;

        try {
            String name = webClient.get()
                .uri(String.valueOf(id))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> json.get("name").asText())
                .block();

            return name;
        } catch (Exception e) {
            return "Döner Unbekannt";
        }
    }
    
}
