package Keystone_group;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MyJobPageBySelenide {

    private SelenideElement headerText = $(byText("My jobs"));
    private SelenideElement searchFieldLocator = $x("//div[@class='field-value with-icon']");
    private SelenideElement searchIcon;
    private SelenideElement searchPlaceholder;
    private SelenideElement searchClearButton;
    private SelenideElement searchField;
    private JobItem jobItem;


    public SelenideElement getHeaderText() {
        return headerText;
    }

    public MyJobPageBySelenide() {
        // Визначаємо елементи при створенні об'єкта класу
        this.searchIcon = searchFieldLocator.$x("span[@class='type-icon type-icon--search']");
        this.searchPlaceholder = searchFieldLocator.$("input");
        this.searchClearButton = searchFieldLocator.$x("span[@class='cursor-pointer type-icon type-icon--clear']");
        this.searchField = searchFieldLocator.$x("input");

    }


    public SelenideElement getSearchIcon() {
        return searchIcon;
    }

    public SelenideElement getSearchPlaceholder() {
        return searchPlaceholder;
    }

    public SelenideElement getSearchClearButton() {
        return searchClearButton;
    }
    public SelenideElement getSearchField() {
        return searchField;
    }
    public String selectRandomJobName() {
        ElementsCollection jobItems = $$(".job-item");
        if (jobItems.isEmpty()) {
            throw new IllegalStateException("No job items found");
        }

        List<String> jobNames = new JobItem().getAllJobNames(jobItems);
        if (jobNames.isEmpty()) {
            throw new IllegalStateException("The list of job names is empty");
        }

        Random random = new Random();
        String randomJobName = jobNames.get(random.nextInt(jobNames.size()));

        // Вставка вибраного jobName у поле пошуку
        searchField.setValue(randomJobName);

        // Натискання клавіші Enter для запуску пошуку
        actions().sendKeys(Keys.ENTER).perform();

        return randomJobName;
    }

}
