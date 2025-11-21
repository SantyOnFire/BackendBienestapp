package com.bienestar.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AlertService {

    @Value("${teams.webhook.url}")
    private String webhookUrl;

    public void enviarAlerta(String mensaje) {
        try {
            RestTemplate rest = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = Map.of("text", mensaje);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            rest.postForEntity(webhookUrl, request, String.class);

            System.out.println("🔔 Alerta enviada correctamente a Teams");

        } catch (Exception e) {
            System.out.println("❌ Error enviando alerta a Teams: " + e.getMessage());
        }
    }
}
