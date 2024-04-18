import Keystone_group.Login_Page;
import Keystone_group.My_jobs_Page;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class My_jobs_Page_Test {

    private WebDriver driver;
    private Login_Page loginPage;
    private My_jobs_Page myJobsPage;
    private By[] jobStatusLabels = {
            By.xpath("//div[text()='Settled']"),
            By.xpath("//div[text()='Pre-construction']"),
            By.xpath("//div[text()='Under construction']"),
            By.xpath("//div[text()='On hold']"),
            By.xpath("//div[text()='Pending revision']")
    };

    private void validLoginAsSuperAdmin() {
        loginPage.noCustomerSignIn("Benr", "Bug#234");
    }

    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\scher\\IdeaProjects\\test-selenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("https://onix-systems-mykch-web-nginx.staging.onix.ua/");
        loginPage = new Login_Page(driver);
        myJobsPage = new My_jobs_Page(driver);

        validLoginAsSuperAdmin();

    }



    @Test
    public void verifySelectedJobStatusTabAfterLogin() {

        myJobsPage.getJobStatusLocator(0);
        Assert.assertTrue(myJobsPage.isTabActive(0));


        for (By label : jobStatusLabels) {
            Assert.assertTrue(myJobsPage.isJobsDisplayedForStatus(label));
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

