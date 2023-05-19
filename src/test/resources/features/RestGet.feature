Feature: Book CRUD Operations
  As a user of the book app
  I want to be able to perform CRUD operations on books
  So that I can manage book data effectively

  Scenario: Create a new book
    Given the book data:
      |isbn  | description    | author      |image       |year
      |1312AA| Test Book      | Test Author |random image|1919
    When I send a POST request to create the book
    Then the book should be created successfully