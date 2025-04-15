package com.example.medical.client;

import com.example.medical.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    public UserDTO getUser(String authHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> request = new HttpEntity<>(headers);
    
        try {
            ResponseEntity<UserDTO> response = restTemplate.exchange(
                "http://auth-service:8081/auth/me",
                HttpMethod.GET,
                request,
                UserDTO.class
            );
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            return null;
        }
    }
    

}
