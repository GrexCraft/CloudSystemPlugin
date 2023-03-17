package net.grexcraft.cloud_plugin.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.grexcraft.cloud_plugin.model.ModifyServerRequest;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;


public class CloudWebClient {

    private static final String apiUrl = "http://cloud-service:8080";

    public static void modifyServer(ModifyServerRequest serverRequest) {
        Bukkit.getLogger().info("Sending request to cloud service");

        var values = new HashMap<String, String>() {{
            put("serverName", serverRequest.getServerName());
            put ("state", serverRequest.getState().toString());
        }};

        var objectMapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapper
                    .writeValueAsString(values);
            Bukkit.getLogger().info("object mapper wrote values");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("requestBody: " + requestBody);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl + "/api/v1/server/modify"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .setHeader("Content-Type", "application/json")
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
//    private static final WebClient client = WebClient.create(apiUrl);
//
//
//    public static void modifyServer(ModifyServerRequest request) {
//        Bukkit.getLogger().info("Sending request to cloud service");
//        client.post()
//                .uri("/api/v1/server/modify")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(Mono.just(request), ModifyServerRequest.class)
//                .retrieve()
//                .bodyToMono(String.class).block();
//    }
}
