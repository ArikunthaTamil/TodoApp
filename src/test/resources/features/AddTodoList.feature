@AddTodoList @TodoApp
Feature: To add one or more items Todo List

  Background:
    Given user on TodoApp main page

  @AddSingle
  Scenario Outline: Add a new todo list and verify footer text

    When I "add" todo list "<todo_list>"
    Then I should see todo list "<todo_list>"
    And I should see todo items left
    And I should see footer option "<footerOptions>"
    Examples:
    |todo_list|footerOptions|
    |My first todo list item|All;Active;Completed|

  @AddMultiple
  Scenario Outline: Add more than one todo list and verify footer text

    When I "add" todo list "<todo_list>"
    And I should see todo list "<todo_list>"
    And I should see todo items left
    And I should see footer option "<footerOptions>"
    Examples:
      |todo_list|footerOptions|
      |Book air ticket;Make hotel reservation;Exchange currency;Book pickup cab;Buy attraction tickets|All;Active;Completed|




