Feature: Post functionality

  Scenario Outline: As a customer I want to create a new post.
    Given I can create a new post
    And I sending post to be created with isbn :<isbn>, title <author> and content description <description> with image <image> and year <year>
    Then I should be able to see my newly created post

    Examples:
      | isbn    | author          | description     |image|year|
      | 12345   | New post title | New post content |aaaa |1212|
      | new_ID  | Post title     | This is content  |aaa2 |bruh|