package Keystone_group;

import com.codeborne.selenide.SelenideElement;

public class TabItem {
    private SelenideElement tabItem;
    private SelenideElement preConstructionTab;
    private SelenideElement underConstructionTab;
    private SelenideElement settledTab;
    private SelenideElement onHoldTab;
    private SelenideElement pendingRevisionTab;

    public TabItem(SelenideElement tabItemElement) {
        this.tabItem = tabItemElement;
        this.preConstructionTab = tabItemElement.$x("li[text()='Pre-construction']");
        this.underConstructionTab = tabItemElement.$x("li[text()='Under construction']");
        this.settledTab = tabItemElement.$x("li[text()='Settled']");
        this.onHoldTab = tabItemElement.$x("li[text()='On hold']");
        this.pendingRevisionTab = tabItemElement.$x("li[text()='Pending revision']");
        this.pendingRevisionTab = tabItemElement.$x("li[text()='All']");

    }
    public void clickPreConstructionTab() {
        preConstructionTab.click();
    }
    public void clickUnderConstructionTab() {
        underConstructionTab.click();
    }
    public void clickSettledTab() {
        settledTab.click();
    }
    public void clickOnHoldTab() {
        onHoldTab.click();
    }
    public void clickPendingRevisionTab() {
        pendingRevisionTab.click();
    }

    public void clickTabByText(String tabText) {
        tabItem.$x(".//li[text()='" + tabText + "']").click();

    }
}
