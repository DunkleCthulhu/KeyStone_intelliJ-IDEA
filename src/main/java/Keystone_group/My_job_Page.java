package Keystone_group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class My_job_Page {

    WebDriver driver;

    public My_job_Page(WebDriver driver) {
        this.driver = driver;
    }

    private By MyJobButton = By.xpath("//a[@class='router-link-active router-link-exact-active']");
    private By jobName = By.xpath("//span[@class='name']");

    public String getJobName() {
        driver.findElement(jobName).getText();
        return driver.findElement(jobName).getText();
    }


}
