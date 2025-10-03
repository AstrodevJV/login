package com.example.basicloggin.service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

@Service
public class GoogleService {
    @Async
    public Map<String, Object> getUserDetails(String accessToken) {
        String url = "https://people.googleapis.com/v1/people/me?personFields=birthdays,genders";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, request, Map.class);

        return response.getBody();
    }
    public String getGender(String accessToken) {
        Map<String, Object> response = getUserDetails(accessToken);

        List<Map<String, Object>> genders = (List<Map<String, Object>>) response.get("genders");
        if (genders != null && !genders.isEmpty()) {

            return (String) genders.get(0).get("value"); // ej: "male", "female"
        }
        return null;
    }

    public Integer getAge(String accessToken) {
        Map<String, Object> response = getUserDetails(accessToken);

        List<Map<String, Object>> birthdays = (List<Map<String, Object>>) response.get("birthdays");
        if (birthdays != null && !birthdays.isEmpty()) {
            Map<String, Object> dateMap = (Map<String, Object>) birthdays.get(0).get("date");

            Integer year = (Integer) dateMap.get("year");
            Integer month = (Integer) dateMap.get("month");
            Integer day = (Integer) dateMap.get("day");

            if (year != null && month != null && day != null) {
                LocalDate birthDate = LocalDate.of(year, month, day);
                LocalDate today = LocalDate.now();
                return Period.between(birthDate, today).getYears(); // edad en años
            }
        }


        return null;
    }
}
