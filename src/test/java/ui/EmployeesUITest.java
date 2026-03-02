package ui;

import base.BaseTest;
import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.EmployeesPage;
import ui.pages.LoginPage;

import java.time.Duration;
import java.util.UUID;

public class EmployeesUITest extends BaseTest {

    private final String BASE_URL =
            Config.BASE_URL + "/Benefits";

    private final String LOGIN_URL =
            Config.BASE_URL + "/Account/Login";

    private void waitForTableData() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d ->
                !d.findElement(By.cssSelector(
                        "#employeesTable tbody tr:first-child td"
                )).getText().trim().isEmpty()
        );
    }

    private void loginAndOpenBenefits() {

        driver.get(LOGIN_URL);
        new LoginPage(driver).login();

        driver.get(BASE_URL);
        waitForTableData();
    }

    // ================= ADD =================

    @Test
    public void test_add_employee_ui() {

        loginAndOpenBenefits();

        EmployeesPage page = new EmployeesPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        int initialCount = page.getRowCount();

        page.clickAdd();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("employeeModal")));

        page.fillForm(
                "UI_" + UUID.randomUUID().toString().substring(0, 5),
                "Test",
                "2"
        );

        page.submitAdd();

        wait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.id("employeeModal")));

        wait.until(d -> page.getRowCount() > initialCount);

        Assert.assertEquals(page.getRowCount(), initialCount + 1);
    }

    // ================= UPDATE =================

    @Test
    public void test_update_employee_ui() {

        loginAndOpenBenefits();

        EmployeesPage page = new EmployeesPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        page.clickFirstEdit();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("employeeModal")));

        String firstName =
                driver.findElement(By.id("firstName"))
                        .getAttribute("value");

        String lastName =
                driver.findElement(By.id("lastName"))
                        .getAttribute("value");

        page.fillForm(firstName, lastName, "5");

        page.submitUpdate();

        wait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.id("employeeModal")));

        wait.until(d ->
                d.findElement(By.cssSelector(
                        "#employeesTable tbody tr:first-child td:nth-child(4)"
                )).getText().trim().equals("5")
        );
    }

    // ================= DELETE =================

    @Test
    public void test_delete_employee_ui() {

        loginAndOpenBenefits();

        EmployeesPage page = new EmployeesPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        int initialCount = page.getRowCount();

        page.clickFirstDelete();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("deleteModal")));

        page.confirmDelete();

        wait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.id("deleteModal")));

        wait.until(d -> page.getRowCount() == initialCount - 1);

        Assert.assertEquals(page.getRowCount(), initialCount - 1);
    }
}
