package com.muradov.analytics.cucumberglue;

import com.muradov.analytics.model.LibraryApp;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class SpringIntegrationTest {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";
    private Integer postId = null;

    @Given("I can create a new post")
    public void i_can_create_a_new_post() {
        String url = postUrl + ":" + port+"/library" + "/getAll";
        List<LibraryApp> allPost = restTemplate.getForObject(url, List.class);
        log.info(allPost);
        assertTrue(!allPost.isEmpty());
    }

    @Given("^I sending post to be created with isbn :(.*), title (.*) and content description (.*) with image (.*) and year (.*)$")

    public void i_sending_post( String isbn, String author,String description,String year,String image) {

        LibraryApp newPost = new LibraryApp();
        String url = postUrl + ":" + port+"/library" + "/add";

        newPost.setIsbn(isbn);
        newPost.setAuthor(author);
        newPost.setDescription(description);
        newPost.setYear(year);
        newPost.setImage(image);

        LibraryApp book = restTemplate.postForObject(url, newPost,LibraryApp.class);

        postId = book.getId();
        log.info(book);
        assertNotNull(book);
    }

    @Then("I should be able to see my newly created post")
    public void i_should_see_my_newly_created_post() {
        String url = postUrl + ":" + port + "/library/"+postId;
        LibraryApp myPost = restTemplate.getForObject(url, LibraryApp.class);
        log.info(myPost);
        assertNotNull(myPost);
    }
}
