package com.bienestar.controller;

import com.bienestar.model.DeepSeekRequest;
import com.bienestar.model.DeepSeekResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DeepSeekController {

    private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String API_KEY = "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";

    @PostMapping("/llama")
    public ResponseEntity<DeepSeekResponse> consultarGroqLlama(@RequestBody DeepSeekRequest request) {
        return procesarSolicitud(request, "llama3-8b-8192");
    }

    @PostMapping("/groq")
    public ResponseEntity<DeepSeekResponse> consultarGroq(@RequestBody DeepSeekRequest request) {
        return procesarSolicitud(request, "mixtral-8x7b-32768"); // o usa llama si mixtral falla
    }

    private ResponseEntity<DeepSeekResponse> procesarSolicitud(DeepSeekRequest request, String modelo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        Map<String, Object> body = new HashMap<>();
        body.put("model", modelo);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "Eres un terapeuta emocional que responde con empatía y comprensión."));
        messages.add(Map.of("role", "user", "content", request.getMessage()));
        body.put("messages", messages);
        body.put("temperature", 0.7);

        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(GROQ_API_URL, entity, String.class);

            // ✅ Extraer solo el contenido del mensaje assistant
            String rawJson = response.getBody();
            String content = "Respuesta no encontrada";

            if (rawJson != null && rawJson.contains("\"content\"")) {
                int start = rawJson.indexOf("\"content\":\"") + 11;
                int end = rawJson.indexOf("\"", start);
                content = rawJson.substring(start, end)
                        .replace("\\n", "\n")
                        .replace("\\\"", "\"");
            }

            return ResponseEntity.ok(new DeepSeekResponse(content));

        } catch (Exception e) {
            e.printStackTrace(); // para depurar en consola
            return ResponseEntity.status(500).body(new DeepSeekResponse("Error al llamar a Groq: " + e.getMessage()));
        }
    }
}
