Feature: Post functionality

  Scenario Outline: As a customer I want to create a new post.
    Given I can create a new post
    And I sending post to be created with isbn :<isbn>, title <author> and content description <description> with image <year> and year <image>
    Then I should be able to see my newly created post

    Examples:
      | isbn     | author              | description         |year   |image|
      | 333AABB  | Dimcho Debelyanov   | Amazing Author      |1919   |imageLink|
      | 444AABB  | Geo Milev           | Magnificent Author  |1920   |ImageLink|