import Keystone_group.Login_Page;
import Keystone_group.My_job_Page;
import Keystone_group.My_jobs_Page;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Login_Page_Test {

    private WebDriver driver;
    private Login_Page loginPage;

    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\scher\\IdeaProjects\\test-selenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("https://onix-systems-mykch-web-nginx.staging.onix.ua/");
        loginPage = new Login_Page(driver);

    }

    @Test
    public void checkSignInText() {
        String text = loginPage.getSignInText();
        Assert.assertEquals("Sign in", text);
    }

    @Test
    public void checkUsernameLable() {
        String text = loginPage.getUsernameLable();
        Assert.assertEquals("Username", text);
    }

    @Test
    public void checkPasswordLable() {
        String text = loginPage.getPasswordLable();
        Assert.assertEquals("Password", text);
    }

    @Test
    public void loginFailTest() {
        Login_Page login_page = loginPage.loginWithInvalidCreds("username", "password");
        String error = login_page.getInvalidCresText();
        Assert.assertEquals("Invalid username or password", error);
    }

    @Test
    public void checkHintUsernameText() {
        loginPage.typeUserName("test");
        loginPage.clearUsername();
        String hintText = loginPage.getHintUsernameText();
        Assert.assertEquals("Value is required", hintText);
    }

    @Test
    public void checkHintPasswordText() {
        loginPage.typePassword("test");
        loginPage.clearPassword();
        String hintText = loginPage.getHintPasswordText();
        Assert.assertEquals("Value is required", hintText);
    }

    @Test
    public void validLoginAsSuperAdmin() {
        My_jobs_Page myJobsPage = loginPage.noCustomerSignIn("Benr", "Bug#234");
        String header = myJobsPage.getHeaderText();
        Assert.assertEquals("My jobs", header);
    }

    @Test
    public void validLoginAsRCM() {
        My_jobs_Page myJobsPage = loginPage.noCustomerSignIn("granth", "ghtc#45");
        String header = myJobsPage.getHeaderText();
        Assert.assertEquals("My jobs", header);
    }

    @Test
    public void validLoginAsCM() {
        My_jobs_Page myJobsPage = loginPage.noCustomerSignIn("TestCM", "buildtest1");
        String header = myJobsPage.getHeaderText();
        Assert.assertEquals("My jobs", header);
    }

    @Test
    public void validLoginAsRSM() {
        My_jobs_Page myJobsPage = loginPage.noCustomerSignIn("bstern", "faster1!");
        String header = myJobsPage.getHeaderText();
        Assert.assertEquals("My jobs", header);
    }

    @Test
    public void validLoginAsDesigner() {
        My_jobs_Page myJobsPage = loginPage.noCustomerSignIn("mariank", "Jax24$");
        String header = myJobsPage.getHeaderText();
        Assert.assertEquals("My jobs", header);
    }

    @Test
    public void validLoginCustomer() {
        My_job_Page myJobPage = loginPage.customerSignIn("0000000076", "23326");
        String jobName = myJobPage.getJobName();
        Assert.assertEquals("DCs046 - Devon Creek - Sebastian Farmhouse", jobName);
    }

    @Test
    public void testLogoPresence() {
        boolean isLogoDisplayed = loginPage.isLogoDisplayed();
        Assert.assertTrue("Logo is not displayed", isLogoDisplayed);
    }

    @Test
    public void testShowHideButton() {
        loginPage.typePassword("password");
        boolean isPasswordVisible = loginPage.isPasswordVisible();
        Assert.assertFalse("Password should be hidden", isPasswordVisible);
        loginPage.togglePasswordVisibility();
        isPasswordVisible = loginPage.isPasswordVisible();
        Assert.assertTrue("Password should be visible", isPasswordVisible);
    }

    @Test
    public void testLoginButtonState() {
        boolean isLoginButtonActive = loginPage.isLoginButtonActive();
        Assert.assertFalse("Login button should be inactive", isLoginButtonActive);
        loginPage.typeUserName("username");
        loginPage.typePassword("password");
        isLoginButtonActive = loginPage.isLoginButtonActive();
        Assert.assertTrue("Login button should be active", isLoginButtonActive);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
