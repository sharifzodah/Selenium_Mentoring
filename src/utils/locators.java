package utils;

public class locators {

    static String menuXpath = "//ul[@data-ui='top-nav-category-list']//span";
    static String itemListXpath = "//h1[@id = 'search-results-top']/following::a/div[2]/h3";
    static String selectOpsXpath = "//div[@data-selector = 'listing-page-variations']//div[@class = 'wt-select']";

    public static String getMenuXpath() {
        return menuXpath;
    }
    public static String getItemListXpath() {
        return itemListXpath;
    }
    public static String getSelectOpsXpath() {
        return selectOpsXpath;
    }

}
