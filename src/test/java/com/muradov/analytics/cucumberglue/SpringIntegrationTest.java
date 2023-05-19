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
    private final String BASE_URL = "http://localhost:8080"+"/library";
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<LibraryApp> response;
    private List<LibraryApp> bookList;

    @Given("the book data:")
    public void setBookData(DataTable libraryAppData) {
        List<Map<String, String>> bookDataList = libraryAppData.asMaps(String.class, String.class);
        Map<String, String> bookData = bookDataList.get(0); // Assuming only one set of book data is provided

        LibraryApp book = new LibraryApp(bookData.get("isbn"), bookData.get("description"),bookData.get("author"),bookData.get("image"),bookData.get("year"));
        response = new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @When("I send a POST request to create the book")
    public void createBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LibraryApp> requestEntity = new HttpEntity<>(response.getBody(), headers);

        try {
            restTemplate.exchange(BASE_URL + "/add", HttpMethod.POST, requestEntity, LibraryApp.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getStatusCode());
        }
    }
    @Then("the book should be created successfully")
    public void verifyBookCreated() {
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

//    @LocalServerPort
//    private int port;
//    private RestTemplate restTemplate = new RestTemplate();
//
//    private String postUrl = "http://localhost";
//
//    private Integer postId = null;
//
//
//
//
//    @Given("I can create a new post")
//    public void i_can_create_a_new_post() {
//        String url = postUrl + ":" + port+"/library" + "/getAll";
//        List<LibraryApp> allPost = restTemplate.getForObject(url, List.class);
//        log.info(allPost);
//        assertTrue(!allPost.isEmpty());
//    }
//
//    @Given("^I sending post to be created with id (.*) , title (.*) and content (.*) and content (.*) year: (.*) and image (.*)$")
//
//    public void i_sending_post( Integer book_id,String isbn, String author,String description,String year,String image) {
////        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
////        headers.add("Content-Type", "application/json; charset=utf-8");
//        LibraryApp newPost = new LibraryApp();
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
////
////        HttpEntity<LibraryApp> entity = new HttpEntity<>(newPost, headers);
//        String url = postUrl + ":" + port+"/library" + "/add";
//
////        ResponseEntity<LibraryApp> response = restTemplate.exchange(url, HttpMethod.POST, entity, LibraryApp.class);
//
//
//
//
//        newPost.setId(book_id);
//        newPost.setIsbn(isbn);
//        newPost.setAuthor(author);
//        newPost.setDescription(description);
//        newPost.setYear(year);
//        newPost.setImage(image);
//
//
//        LibraryApp book = restTemplate.postForObject(url, newPost,LibraryApp.class);
//
////        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//        postId = book.getId();
//        log.info(book);
//        assertNotNull(book);
//    }
//
//    @Then("I should be able to see my newly created post")
//    public void i_should_see_my_newly_created_post() {
//        String url = postUrl + ":" + port + "/library/"+postId;
//        LibraryApp myPost = restTemplate.getForObject(url, LibraryApp.class);
//        log.info(myPost);
//        assertNotNull(myPost);
//    }
}
