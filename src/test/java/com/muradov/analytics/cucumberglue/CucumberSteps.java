package com.muradov.analytics.cucumberglue;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

public class CucumberSteps {

    @LocalServerPort
    String port;

    ResponseEntity<String> lastResponse;
    private final String BASE_URL="http://localhost:"+port;





}
