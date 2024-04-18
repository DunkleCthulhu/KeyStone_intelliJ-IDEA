package Keystone_group;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

public class JobItem {

    private SelenideElement item;
    private SelenideElement jobStatus;
    private SelenideElement jobName;
    private SelenideElement jobSummary;
    private SelenideElement jobStatusBar;
    private SelenideElement jobStatusPercent;
    private SelenideElement jobMuteIcon;
    private SelenideElement jobStatusBarPercent;



    public JobItem(SelenideElement jobItemElement) {
        this.item = jobItemElement;
        this.jobStatus = item.$x("a//div[contains(@class, 'job-status')]");
        this.jobName = item.$x("a//span[@class='name']");
        this.jobSummary = item.$x("a//div[@class='summary']");
        this.jobStatusBar = item.$x("a//div[@class='progress-bar-container']");
        this.jobStatusPercent = item.$x("a//span[@class='progress-bar-percent']");
        this.jobMuteIcon = item.$x("a//span[@class='mute']");
        this.jobStatusBarPercent = item.$x("a//div[@style]");
    }

    public SelenideElement getJobStatus() {
        return jobStatus;
    }

    public SelenideElement getJobName() {
        return jobName;
    }

    public SelenideElement getJobSummary() {
        return jobSummary;
    }

    public SelenideElement getJobStatusBar() {
        return jobStatusBar;
    }

    public SelenideElement getJobStatusPercent() {
        return jobStatusPercent;
    }

    public SelenideElement getJobMuteIcon() {
        return jobMuteIcon;
    }
    public SelenideElement getJobStatusBarPercent(){
        return jobStatusBarPercent;
    }
    public List<String> getAllJobNames(ElementsCollection jobItems) {
        List<String> jobNames = new ArrayList<>();
        for (SelenideElement jobItem : jobItems) {
            JobItem item = new JobItem(jobItem);
            jobNames.add(item.getJobName().text());
        }
        return jobNames;
    }


}

