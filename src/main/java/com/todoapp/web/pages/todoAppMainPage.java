package com.todoapp.web.pages;

import com.todoapp.web.wrapper.wrapperMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.ArrayList;
import java.util.List;

public class todoAppMainPage extends LoadableComponent<todoAppMainPage> {

    private WebDriver driver;

    public todoAppMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".new-todo")
    WebElement txtnewTodo;
    @FindBy(css = ".todo")
    List<WebElement> alltodoLists;
    @FindBy(css = "li[class='todo']")
    List<WebElement> activetodoLists;
    @FindBy(css = ".todo label")
    List<WebElement> lblTodoLists;
    @FindBy(css = ".todo-count")
    WebElement txtTodoCount;
    @FindBy(css = ".footer")
    WebElement txtFooter;
    @FindBy(css = ".footer a")
    List<WebElement> linkFooter;
    @FindBy(css = "a.selected")
    WebElement linkSelectedFooter;
    @FindBy(css = ".destroy")
    List<WebElement> btnDeleteLists;
    @FindBy(css = ".toggle")
    List<WebElement> cbToggle;
    @FindBy(css = "label[for='toggle-all']")
    WebElement cbToggleAll;
    @FindBy(css = ".clear-completed")
    WebElement btnClearCompleted;

    public void addTodoList(String list) {
        wrapperMethods.setElementText(txtnewTodo, list);
        txtnewTodo.sendKeys(Keys.ENTER);
    }

    public int getTodoItemsListSize() {
        return alltodoLists.size();
    }

    public List<String> listOfAddedTodoItemText() {
        List<WebElement> todoItemsElements = alltodoLists;
        List<String> todoItemsTexts = new ArrayList<>();

        for(WebElement todoItem: todoItemsElements) {
            todoItemsTexts.add(todoItem.getText());
        }
        return todoItemsTexts;
    }

    public String getFooterItemsCountText() {
        return wrapperMethods.getElementText(txtTodoCount);
    }

    public List<String> getFooterLinksText() {
        List<String> footerLinksText = new ArrayList<>();
        for(WebElement element: linkFooter) {
            footerLinksText.add(element.getText());
        }

        return footerLinksText;
    }

    public void addTodoLists(List<String> todoItemsList) {
        for(String todoItem: todoItemsList) {
            addTodoList(todoItem);
        }
    }

    public void clickOnFooterLink(String linkText) {
        for(WebElement element: linkFooter) {
            if(element.getText().equalsIgnoreCase(linkText)) {
                element.click();
                break;
            }
        }
    }

    public void deleteByTodoText(String todoListText) {
        int itemIndex = getTodoListTextPosition(todoListText);
       alltodoLists.get(itemIndex).click();
        btnDeleteLists.get(itemIndex).click();
    }

    public void deleteTodoLists(List<String> todoItemsList) throws InterruptedException {
        for(String todoItem: todoItemsList) {
            deleteByTodoText(todoItem);
        }
    }

    public void completeByTodoListText(String todoListText) throws InterruptedException {
        int itemIndex = getTodoListTextPosition(todoListText);
        cbToggle.get(itemIndex).click();
    }

    public void markCompletedAll() throws InterruptedException {
        cbToggleAll.click();
    }

    public boolean isTodoItemDisplayed(String todoListText) {
        return getTodoListTextPosition(todoListText) >= 0;
    }

    private int getTodoListTextPosition(String todoListText) {
        int position = -1;
        for(int i = 0; i< alltodoLists.size(); i++) {
            if(alltodoLists.get(i).getText().equalsIgnoreCase(todoListText)) {
                position = i;
                break;
            }
        }
        return position;
    }

    public void renameTodoList(String originalList, String renamedList) throws InterruptedException {
        int currentItemIndex = getTodoListTextPosition(originalList);
        wrapperMethods.doubleClickOnElement(alltodoLists.get(currentItemIndex));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerText='" + renamedList + "'", lblTodoLists.get(currentItemIndex));
        txtnewTodo.sendKeys(Keys.ENTER);
    }

    public void markCompleteTodoListItems(List<String> todoLists) throws InterruptedException {
        for(String todoItem: todoLists) {
            completeByTodoListText(todoItem);
        }
    }

    public boolean isTodoItemStrikedOut(String todoListItem) {
        int position = getTodoListTextPosition(todoListItem);
        return lblTodoLists.get(position).getCssValue("text-decoration").contains("line-through");
    }

    public boolean isClearCompletedDisplay() {
        return btnClearCompleted.isDisplayed();
    }

    public void clickOnClearCompleted() {
        btnClearCompleted.click();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }
}
