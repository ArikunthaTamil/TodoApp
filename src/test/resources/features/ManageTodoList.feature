@ManageTodoList @TodoApp
Feature: To manage added Todo List

  Background:
    Given user on TodoApp main page

  @markCompleted
  Scenario Outline: Mark a few todo list item as completed

    Given I "add" todo list "<todo_list>"
    When I "complete" todo list "<mark_completed>"
    Then I should see "<mark_completed>" todo list item "striked"
    And I should see "Clear completed" button
    Examples:
      |todo_list|mark_completed|
      |Book air ticket;Make hotel reservation;Exchange currency;Book pickup cab;Buy attraction tickets|Book air ticket;Make hotel reservation|

  @markAllCompleted
  Scenario Outline: Mark all todo list item as completed

    Given I "add" todo list "<todo_list>"
    When I click on "mark all completed" button
    Then I should see "<todo_list>" todo list item "striked"
    And I should see "Clear completed" button
    Examples:
      |todo_list|
      |Book air ticket;Make hotel reservation;Exchange currency;Book pickup cab;Buy attraction tickets|

  @clearCompleted
  Scenario Outline: Clear completed todo list item

    Given I "add" todo list "<todo_list>"
    And I "complete" todo list "<mark_completed>"
    And I click on "Clear completed" button
    And I should see todo items left
    Examples:
      |todo_list|mark_completed|
      |Book air ticket;Make hotel reservation;Exchange currency;Book pickup cab;Buy attraction tickets|Book air ticket;Make hotel reservation|

  @renameTodoList
  Scenario Outline: Rename one of the added todo list item

    Given I "add" todo list "<todo_list>"
    When I update "<tobe_renamed>" item as "<new_name>"
    Then I "should" see todo list item "<new_name>"
    And I "should not" see todo list item "<tobe_renamed>"
    Examples:
      |todo_list|tobe_renamed|new_name|
      |Book air ticket;Make hotel reservation;Exchange currency;Book pickup cab;Buy attraction tickets|Make hotel reservation|Apply visa application|

  @deleteTodoList
  Scenario Outline: Delete one of the added todo list item

    Given I "add" todo list "<todo_list>"
    When I delete the item "<tobe_deleted>"
    Then I "should not" see todo list item "<tobe_deleted>"
    Examples:
      |todo_list|tobe_deleted|
      |Book air ticket;Apply visa application;Make hotel reservation;Exchange currency;Book pickup cab;Buy attraction tickets|Make hotel reservation|

  @verifyFooterOptions
  Scenario Outline: Delete one of the added todo list item

    Given I "add" todo list "<todo_list>"
    When I "complete" todo list "<mark_completed>"
    Then I should see todo list "<todo_list>"
    When I click on "Active" link
    Then I should see todo list "<active_list>"
    When I click on "Completed" link
    Then I should see todo list "<mark_completed>"
    Examples:
      |todo_list|mark_completed|active_list|
      |Book air ticket;Apply visa application;Make hotel reservation;Exchange currency;Book pickup cab;Buy attraction tickets|Book air ticket;Make hotel reservation|Apply visa application;Exchange currency;Book pickup cab;Buy attraction tickets|