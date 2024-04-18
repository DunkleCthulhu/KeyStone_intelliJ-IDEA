package Keystone_group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class My_jobs_Page {

    private WebDriver driver;

    public My_jobs_Page(WebDriver driver) {
        this.driver = driver;
    }
    private By headerText = By.xpath("//h1[text()='My jobs']");
    private By jobSearch = By.xpath("//input[@id='my-jobs-search']");
    private By[] jobStatusLocators = {
            By.xpath("//div[@class='jobs-layout--body--navigation']/ul/li[1]"),
            By.xpath("//div[@class='jobs-layout--body--navigation']/ul/li[2]"),
            By.xpath("//div[@class='jobs-layout--body--navigation']/ul/li[3]"),
            By.xpath("//div[@class='jobs-layout--body--navigation']/ul/li[4]"),
            By.xpath("//div[@class='jobs-layout--body--navigation']/ul/li[5]"),
            By.xpath("//div[@class='jobs-layout--body--navigation']/ul/li[6]")
    };

    private By jobItems = By.xpath("//li[@class=“job-item”]");
    private By[] jobStatusLabels = {
            By.xpath("//div[text()='Settled']"),
            By.xpath("//div[text()='Pre-construction']"),
            By.xpath("//div[text()='Under construction']"),
            By.xpath("//div[text()='On hold']"),
            By.xpath("//div[text()='Pending revision']")
    };



    public By getJobStatusLocator(int index) {
        return jobStatusLocators[index];
    }

    public boolean isTabActive(int index) {
        WebElement tab = driver.findElement(jobStatusLocators[index]);
        return tab.getAttribute("class").contains("active");
    }

    public boolean isJobsDisplayedForStatus(By jobStatusLocator) {
        List<WebElement> jobElements = driver.findElements(jobStatusLocator);
        return !jobElements.isEmpty();
    }


    public boolean areJobsDisplayedForAllStatuses() {
        boolean allJobsDisplayed = true;
        for (By statusLabel : jobStatusLabels) {
            if (!isJobsDisplayedForStatus(statusLabel)) {
                allJobsDisplayed = false;
                break;
            }
        }
        return allJobsDisplayed;
    }

    public By getjobStatusLabels(int index) {
        return jobStatusLabels[index];
    }

    public String getHeaderText() {
        driver.findElement(headerText).getText();
        return driver.findElement(headerText).getText();
    }
}
