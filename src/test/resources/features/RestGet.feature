Feature: Post functionality

  Scenario Outline: As a customer I want to create a new post.
    Given I can create a new post
    And I sending post to be created with isbn :<isbn>, title <author> and content description <description> with image <image> and year <year>
    Then I should be able to see my newly created post

    Examples:
      | isbn     | author         | description      |image                  |year|
      | 111AAA   | Ivan Vazov     | A perfect book   |"POD IGOTO IMAGE LINK" |1000|
      | 222AAA   | Hristo Botev   | A perfect book   |"POD IGOTO IMAGE LINK" |1000|