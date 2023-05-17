Feature: Verify get all request in the Api


  Scenario Outline: As a customer I want to create a new post.
    Given I can create a new post
    And I sending post to be created with id <book_id> and title <isbn> and content <author> and content <description> year: <year> and image <image>
    Then I should be able to see my newly created post

    Examples:
       |book_id| isbn          | author      |description|year|image |
      |28| 1        |Ivan Vazov   | Masterpiece|1918| imagine this |