package utils;

public class locators {

    public static String url = "https://www.etsy.com";
    public static String url1 = "https://www.etsy.com/listing/501125290/soccer-decal-personalized-soccer-ball?ga_order=most_relevant&ga_search_type=all&ga_view_type=gallery&ga_search_query=&ref=sr_gallery-1-16&pro=1&organic_search_click=1";
    final static String menuXpath = "//ul[@data-ui='top-nav-category-list']//span";
    final static String itemListXpath = "//h1[@id = 'search-results-top']/following::a/div[2]/h3";
    final static String selectOpsXpath = "//div[@data-selector = 'listing-page-variations' ]//select";
    final static String qtySelXpath = "//select[@id = 'listing-page-quantity-select' ]";
    final static String textInput = "listing-page-personalization-textarea";
    final static String addToCart = "//button[contains(text(), 'Add to cart')]";
    final static String viewCart = "//a[contains(text(), 'View cart & check out')]";
    final static String successMsg = "//h3[text() = 'Added to cart']";
    final static String goToCart = "//a[text() = 'Go to cart']";
    final static String checkOut = "//span[@class = 'submit-button-text']";

    ////div[@data-selector = 'listing-page-variations']//div[@class = 'wt-select']
    public static String getMenuXpath() {
        return menuXpath;
    }

    public static String getItemListXpath() {
        return itemListXpath;
    }

    public static String getSelectOpsXpath() {
        return selectOpsXpath;
    }

    public static String getQtySelXpath() {
        return qtySelXpath;
    }

    public static String getTextInput() {
        return textInput;
    }

    public static String getAddToCart() {
        return addToCart;
    }

    public static String getSuccessMsg() {
        return successMsg;
    }

    public static String getViewCart() {
        return viewCart;
    }

    public static String getGoToCart() {
        return goToCart;
    }
    public static String getCheckOut() {
        return checkOut;
    }

}
