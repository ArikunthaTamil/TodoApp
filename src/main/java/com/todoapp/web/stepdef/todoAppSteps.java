package com.todoapp.web.stepdef;

import com.todoapp.web.pages.todoAppMainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class todoAppSteps {

    WebDriver driver = testHooks.driver;
    private final todoAppMainPage todoPage = new todoAppMainPage(driver);

    @Given("^user on TodoApp main page$")
    public void userLaunchesTodoApplication() {
        String url = "https://todomvc.com/examples/vue/";
        driver.get(url);
        assert driver.getTitle().contains("TodoMVC") : "Title is not matching";
    }

    @When("I {string} todo list {string}")
    public void TodoList(String action, String todolist) throws InterruptedException {
        List<String> todoLists = Arrays.asList(todolist.split(";"));
        if (action.equalsIgnoreCase("add")){
            todoPage.addTodoLists(todoLists);
        }else if (action.equalsIgnoreCase("delete")){
            todoPage.deleteTodoLists(todoLists);
        }else{
            todoPage.markCompleteTodoListItems(todoLists);
        }

    }

    @And("I should see todo items left")
    public void todoItemsLeft() {
        String expText = "";
        int size = todoPage.getTodoItemsListSize();
        if (size == 1) {
            expText = size + " item left";
        }else{
            expText = size + " items left";
        }
        assert Objects.equals(expText, todoPage.getFooterItemsCountText()) : "Added todo item left text is not matching";
    }

    @And("I should see footer option {string}")
    public void footerOptions(String options) {
        List<String> expfooterList = Arrays.asList(options.split(";"));
        List<String> actfooterList = todoPage.getFooterLinksText();
        assert expfooterList.containsAll(actfooterList) : "Footer Links options are not matching";
    }

    @Then("I should see todo list {string}")
    public void verifyAddedTodoListItems(String todolist) {
        List<String> todoLists = Arrays.asList(todolist.split(";"));
        List<String> addedList = todoPage.listOfAddedTodoItemText();
        assert todoLists.equals(addedList) : "Todo List is not matching";
    }

    @Then("I should see {string} todo list item {string}")
    public void isStrikedOut(String todolist, String state) {
        List<String> todoLists = Arrays.asList(todolist.split(";"));
        if (state.equalsIgnoreCase("striked")) {
            for (String listitem : todoLists) {
                assert todoPage.isTodoItemStrikedOut(listitem) : listitem + " - Todo item is not striked out";
            }
        }
    }


    @And("I should see {string} button")
    public void isButtonDisplayed(String button) {
        if(button.equalsIgnoreCase("clear completed")) {
            assert todoPage.isClearCompletedDisplay() : "Clear Completed button not display";
        }
    }

    @When("I click on {string} button")
    public void clickOnButton(String button) throws InterruptedException {
        if(button.equalsIgnoreCase("clear completed")) {
            todoPage.clickOnClearCompleted();
        }else if(button.equalsIgnoreCase("mark all completed")){
            todoPage.markCompletedAll();
        }
    }

    @When("I click on {string} link")
    public void clickOnFooterLink(String linkText) {
        todoPage.clickOnFooterLink(linkText);
    }

    @And("I {string} see todo list item {string}")
    public void isTodoItemExists(String condition, String todoListItem) {
        boolean flag = todoPage.isTodoItemDisplayed(todoListItem);
        if(condition.equalsIgnoreCase("should")) {
            assert flag : "Todo list is displayed";
        } else {
            assert !flag : "Todo list is not displayed";
        }
    }

    @When("I delete the item {string}")
    public void deleteTodoList(String todoItem) throws InterruptedException {
        todoPage.deleteByTodoText(todoItem);
    }

    @When("I update {string} item as {string}")
    public void renameTodoList(String currentItem, String updateItem) throws InterruptedException {
        todoPage.renameTodoList(currentItem, updateItem);
    }
}
