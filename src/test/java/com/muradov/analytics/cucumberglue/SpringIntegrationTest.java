package com.muradov.analytics.cucumberglue;

import com.muradov.analytics.model.LibraryApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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

    private int postId = 0;

    @Given("I can create a new post")
    public void i_can_create_a_new_post() {
        String url = postUrl + ":" + port+"/library" + "/getAll";
        List<LibraryApp> allPost = restTemplate.getForObject(url, List.class, HttpMethod.GET);
        log.info(allPost);
        assertTrue(!allPost.isEmpty());
    }

    @Given("^I sending post to be created with id (.*) and title (.*) and content (.*) and content (.*) year: (.*) and image (.*)$")

    public void i_sending_post( Integer book_id,String isbn, String author,String description,String year,String image) {
        String url = postUrl + ":" + port+"/library" + "/add";
        LibraryApp newPost = new LibraryApp();
           newPost.setId(book_id);
        newPost.setIsbn(isbn);
        newPost.setAuthor(author);
        newPost.setDescription(description);
        newPost.setYear(year);
        newPost.setImage(image);

        LibraryApp post = restTemplate.postForObject(url, newPost, LibraryApp.class,HttpMethod.POST);

        postId = post.getId();
        log.info(post);
        assertNotNull(post);
    }

    @Then("I should be able to see my newly created post")
    public void i_should_see_my_newly_created_post() {
        String url = postUrl + ":" + port + "/library/" + postId;
        LibraryApp myPost = restTemplate.getForObject(url, LibraryApp.class,HttpMethod.GET);
        log.info(myPost);
        assertNotNull(myPost);
    }
}
