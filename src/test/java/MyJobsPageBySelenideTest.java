import Keystone_group.JobItem;
import Keystone_group.MyJobPageBySelenide;
import Keystone_group.TabItem;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class MyJobsPageBySelenideTest {

    private MyJobPageBySelenide myJobPageBySelenide;
    private JobItem jobItem;
    private TabItem tabItem;


    @Before
    public void setUp() {
        ChromeDriverManager.getInstance().setup();
        open("https://onix-systems-mykch-web-nginx.staging.onix.ua/");
        $("#username").setValue("Benr");
        $("#password").setValue("Bug#234");
        $(byText("Login")).click();
        myJobPageBySelenide = new MyJobPageBySelenide();
        jobItem = new JobItem($x("//li[@class='job-item']"));
        tabItem = new TabItem($x("//ul[@class='list-none tabs']"));

    }

    @Test
    public void checkMyJobTitle() {
        $(myJobPageBySelenide.getHeaderText()).shouldHave(text("My jobs"));
    }

    @Test
    public void checkJobStatusIsPresent() {
        jobItem.getJobStatus().shouldHave(text("Settled"));
    }

    @Test
    public void checkJobNameIsPresent() {
        jobItem.getJobName().shouldBe(visible);
    }

    @Test
    public void checkJobSummaryIsPresent() {
        jobItem.getJobSummary().shouldHave(text("Schedule Process"));
    }

    @Test
    public void checkJobStatusBarIsPresent() {
        jobItem.getJobStatusBar().shouldBe(visible);
    }

    @Test
    public void checkJobStatusPercentIsPresent() {
        jobItem.getJobStatusPercent().shouldBe(visible).shouldHave(text("%"));
    }

    @Test
    public void checkJobMuteIconIsPresent() {
        $$(".mute").filterBy(visible).shouldHave(sizeGreaterThan(0));
    }

    @Test
    public void testJobStatusPercentMatches(){
        SelenideElement jobStatusBar = jobItem.getJobStatusBar();
        SelenideElement jobStatusPercent = jobItem.getJobStatusPercent();

        String statusText = jobStatusBar.getText();
        String percentText = jobStatusPercent.getText().replace("%", "");

        String[] parts = statusText.split("\\(");
        if (parts.length > 1) {
            String[] numbers = parts[1].split(" of ");
            int current = Integer.parseInt(numbers[0].trim());
            int total = Integer.parseInt(numbers[1].replace(")", "").trim());
            double percent = Math.ceil((double) current / total * 100);

            int actualPercent = Integer.parseInt(percentText);

            assertEquals((int) percent, actualPercent);
        }

    }

    @Test
    public void checkJobStatusBarFilling () {
        SelenideElement progressBar = jobItem.getJobStatusBarPercent();
        SelenideElement percentTextElement = jobItem.getJobStatusPercent();

        int expectedPercent = Integer.parseInt(percentTextElement.getText().replace("%", ""));

        int actualWidth = progressBar.getSize().getWidth();
        int totalWidth = progressBar.parent().getSize().getWidth();

        int actualPercent = actualWidth * 100 / totalWidth;

        assertEquals(expectedPercent, actualPercent);

    }

    @Test
    public void checkJobStatusesOnTheAllTab() {
        String[] statuses = {"Settled", "Under construction", "Pre-construction", "On hold", "Pending revision"};
        for (String status : statuses) {
            $$x("//li[@class='job-item']//div[contains(@class, 'job-status') and text()='" + status + "']").shouldBe(sizeGreaterThan(0));
            while (!$x("//li[@class='job-item']//div[contains(@class, 'job-status') and text()='Pending revision']").is(exist)) {
                executeJavaScript("window.scrollBy(0,500)");
            }
            $$x("//li[@class='job-item']//div[contains(@class, 'job-status') and text()='Pending revision']")
                    .first().shouldBe(visible);

        }
    }
    @Test
    public void checkPreConstructionTab() {
        tabItem.clickPreConstructionTab();
        $$(".job-item").forEach(jobItem -> {
            JobItem job = new JobItem(jobItem);
            job.getJobStatus().shouldHave(text("Pre-construction"));
        });
    }

    @Test
    public void checkUnderConstructionTab() {
        tabItem.clickUnderConstructionTab();
        $$(".job-item").forEach(jobItem -> {
            JobItem job = new JobItem(jobItem);
            job.getJobStatus().shouldHave(text("Under construction"));
        });
    }

    @Test
    public void checkSettledTab() {
        tabItem.clickSettledTab();
        $$(".job-item").forEach(jobItem -> {
            JobItem job = new JobItem(jobItem);
            job.getJobStatus().shouldHave(text("Settled"));
        });
    }

    @Test
    public void checkOnHoldTab() {
        tabItem.clickOnHoldTab();
        $$(".job-item").forEach(jobItem -> {
            JobItem job = new JobItem(jobItem);
            job.getJobStatus().shouldHave(text("On hold"));
        });
    }

    @Test
    public void checkPendingRevisionTab() {
        tabItem.clickPendingRevisionTab();
        $$(".job-item").forEach(jobItem -> {
            JobItem job = new JobItem(jobItem);
            job.getJobStatus().shouldHave(text("Pending revision"));
        });
    }

    @Test
    public void checkTabsOrder() {
        String[] expectedTabsOrder = {"All", "Pre-construction", "Under construction", "Settled", "On hold", "Pending revision"};
        for (int i = 0; i < expectedTabsOrder.length; i++) {
            SelenideElement tab = $("li", i);
            tabItem.clickTabByText(expectedTabsOrder[i]);
            tab.shouldHave(text(expectedTabsOrder[i]));
        }
    }

    @Test
    public void checkSkeletonViewBeforeTabsAppear() {
        SelenideElement skeletonView = $(".skeleton--box-shadow");
        skeletonView.shouldBe(Condition.visible);
        SelenideElement tabs = $(".tabs");
        tabs.shouldNotBe(Condition.visible);
    }

    @Test
    public void checkSkeletonViewBeforeItemsAppear() {
        SelenideElement skeletonView = $(".skeleton--box-shadow");
        skeletonView.shouldBe(Condition.visible);
        SelenideElement jobItemsContainer = $(".job-items-container");
        jobItemsContainer.shouldNotBe(Condition.visible);
    }

    @Test
    public void testSearchFieldHasIcon() {
        myJobPageBySelenide.getSearchIcon().shouldBe(visible);
    }

    @Test
    public void testSearchPlaceholder() {
        myJobPageBySelenide.getSearchPlaceholder().shouldBe(visible);
    }

    @Test
    public void testSearchClearButton() {
        String searchText = "Some text";
        myJobPageBySelenide.getSearchField().click();
        myJobPageBySelenide.getSearchField().setValue(searchText);
        myJobPageBySelenide.getSearchClearButton().shouldBe(visible);
        myJobPageBySelenide.getSearchClearButton().click();
        myJobPageBySelenide.getSearchClearButton().shouldNotBe(visible);
    }

    @Test
    public void testJobSearch() {

        String randomJobName = myJobPageBySelenide.selectRandomJobName();

        // Перевірити, чи відображається шукана джоба
        jobItem.getJobName().shouldHave(Condition.text(randomJobName));
    }


}





