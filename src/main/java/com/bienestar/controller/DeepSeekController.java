package com.bienestar.controller;

import com.bienestar.model.DeepSeekRequest;
import com.bienestar.model.DeepSeekResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DeepSeekController {

    private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";

    @Value("${groq.api.key}")
    private String apiKey;

    @PostMapping("/llama")
    public ResponseEntity<DeepSeekResponse> consultarGroqLlama(@RequestBody DeepSeekRequest request) {
        return procesarSolicitud(request, "llama-3.1-8b-instant");
    }

    @PostMapping("/groq")
    public ResponseEntity<DeepSeekResponse> consultarGroq(@RequestBody DeepSeekRequest request) {
        return procesarSolicitud(request, "llama-3.1-8b-instant");
    }

    private ResponseEntity<DeepSeekResponse> procesarSolicitud(DeepSeekRequest request, String modelo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", modelo);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of(
                "role",
                "system",
                "content",
                "Eres un terapeuta emocional que responde con empatía, calma y comprensión."
        ));
        messages.add(Map.of(
                "role",
                "user",
                "content",
                request.getMessage()
        ));

        body.put("messages", messages);
        body.put("temperature", 0.7);

        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        RestTemplate rest = new RestTemplate();

        try {
            ResponseEntity<String> response = rest.postForEntity(GROQ_API_URL, entity, String.class);

            String rawJson = response.getBody();
            String content = "Respuesta no encontrada";

            if (rawJson != null && rawJson.contains("\"content\"")) {
                int start = rawJson.indexOf("\"content\":\"") + 11;
                int end = rawJson.indexOf("\"", start);
                if (end > start) {
                    content = rawJson.substring(start, end)
                            .replace("\\n", "\n")
                            .replace("\\\"", "\"");
                }
            }

            return ResponseEntity.ok(new DeepSeekResponse(content));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(new DeepSeekResponse("Error al llamar a Groq: " + e.getMessage()));
        }
    }

    // 🔥 Endpoint para listar los modelos que tu cuenta puede usar
    @GetMapping("/modelos")
    public ResponseEntity<String> listarModelos() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);

            HttpEntity<Void> entity = new HttpEntity<>(headers);
            RestTemplate rest = new RestTemplate();

            ResponseEntity<String> response = rest.exchange(
                    "https://api.groq.com/openai/v1/models",
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("ERROR: " + e.getMessage());
        }
    }
}
