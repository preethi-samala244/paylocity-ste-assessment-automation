package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class EmployeesPage extends BasePage {

    private final By ADD_BUTTON = By.id("add");
    private final By FIRST_NAME = By.id("firstName");
    private final By LAST_NAME = By.id("lastName");
    private final By DEPENDANTS = By.id("dependants");

    private final By ADD_EMPLOYEE_BTN = By.id("addEmployee");
    private final By UPDATE_EMPLOYEE_BTN = By.id("updateEmployee");
    private final By DELETE_EMPLOYEE_BTN = By.id("deleteEmployee");

    private final By TABLE_ROWS = By.cssSelector("#employeesTable tbody tr");
    private final By EDIT_ICON = By.cssSelector("#employeesTable tbody tr:first-child .fa-edit");
    private final By DELETE_ICON = By.cssSelector("#employeesTable tbody tr:first-child .fa-times");

    public EmployeesPage(WebDriver driver) {
        super(driver);
    }

    public void clickAdd() {
        click(ADD_BUTTON);
    }

    public void fillForm(String first, String last, String dependants) {
        type(FIRST_NAME, first);
        type(LAST_NAME, last);
        type(DEPENDANTS, dependants);
    }

    public void submitAdd() {
        click(ADD_EMPLOYEE_BTN);
    }

    public void submitUpdate() {
        click(UPDATE_EMPLOYEE_BTN);
    }

    public void confirmDelete() {
        click(DELETE_EMPLOYEE_BTN);
    }

    public int getRowCount() {
        List<?> rows = driver.findElements(TABLE_ROWS);
        return rows.size();
    }

    public void clickFirstEdit() {
        click(EDIT_ICON);
    }

    public void clickFirstDelete() {
        click(DELETE_ICON);
    }
}