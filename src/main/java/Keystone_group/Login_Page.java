package Keystone_group;

import org.openqa.selenium.*;

public class Login_Page {
    private WebDriver driver;
    public Login_Page(WebDriver driver) {
        this.driver = driver;
    }

    private By loginButton = By.xpath("//button[text()='Login']");
    private By userNameField = By.xpath("//input[@id='username']");
    private By passwordField = By.xpath("//input[@id='password']");
    private By logo = By.xpath("//div[@class='header--logo']");
    private By signInText = By.xpath("//h1[text()='Sign in']");
    private By hintUsernameText = By.xpath("//li[@class='error']");
    private By hintPasswordText = By.xpath("//li[@class='error']");
    private By invalidCredsText = By.xpath("//li[text()='Invalid username or password']");
    private By usernameLable = By.xpath("//label[normalize-space()='Username']");
    private By passwordLable = By.xpath("//label[normalize-space()='Password']");
    private By showHideButton = By.xpath("//span[@class='button-toggle']");

    public My_jobs_Page clickLogin() {
        driver.findElement(loginButton).click();
        return new My_jobs_Page(driver);
    }

    public Login_Page typeUserName(String username) {
        driver.findElement(userNameField).sendKeys(username);
        return this;
    }
    public String getUsernameLable() {
        return driver.findElement(usernameLable).getText();
    }
    public Login_Page clearUsername() {
        WebElement usernameElement = driver.findElement(userNameField);
        usernameElement.click();
        String select = Keys.chord(Keys.CONTROL, "a");
        String delete = Keys.chord(Keys.DELETE);
        usernameElement.sendKeys(select);
        usernameElement.sendKeys(delete);
        return this;
    }
    public String getHintUsernameText() {
        driver.findElement(hintUsernameText).getText();
        return driver.findElement(hintUsernameText).getText();
    }

    public Login_Page typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public String getPasswordLable() {
        return driver.findElement(passwordLable).getText();
    }
    public Login_Page clearPassword() {
        WebElement passwordElement =  driver.findElement(passwordField);
        passwordElement.click();
        String select = Keys.chord(Keys.CONTROL, "a");
        String delete = Keys.chord(Keys.DELETE);
        passwordElement.sendKeys(select);
        passwordElement.sendKeys(delete);
        return this;
    }

    public String getHintPasswordText() {
        driver.findElement(hintPasswordText).getText();
        return driver.findElement(hintPasswordText).getText();
    }


    public My_jobs_Page noCustomerSignIn (String username, String password) {
        this.typeUserName(username);
        this.typePassword(password);
        this.clickLogin();
        return new My_jobs_Page(driver);
    }

    public My_job_Page customerSignIn (String username, String password) {
        this.typeUserName(username);
        this.typePassword(password);
        this.clickLogin();
        return new My_job_Page(driver);
    }

    public Login_Page loginWithInvalidCreds(String username, String password) {
        this.typeUserName(username);
        this.typePassword(password);
        this.clickLogin();
        return new Login_Page(driver);
    }

    public String getSignInText () {
        return driver.findElement(signInText).getText();
    }

    public String getInvalidCresText() {
        return driver.findElement(invalidCredsText).getText();
    }

    public boolean isLogoDisplayed() {
        try {
            WebElement logoElement = driver.findElement(logo);
            return logoElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPasswordVisible() {
        WebElement passField = driver.findElement(passwordField);
        return "text".equals(passField.getAttribute("type"));
    }

    public void togglePasswordVisibility() {
        driver.findElement(showHideButton).click();
    }

    public boolean isLoginButtonActive() {
        return driver.findElement(loginButton).isEnabled();
    }














}
