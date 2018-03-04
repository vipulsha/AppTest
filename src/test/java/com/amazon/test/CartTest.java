package com.amazon.test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import com.amazon.pages.*;
import com.common.utils.Assertion;
import com.common.utils.ParentTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends ParentTest {

    @Test
            public void testProductDetailsInCart() {
        try{
            HomePage homePage = new HomePage(driver);
            SignInPage signInPage = homePage.clickOnSignInOption();
            UserHomePage userHomePage = signInPage.signIn(getData("email"), getData("password"));

            //Clear Cart

            userHomePage.clearCart();

            userHomePage.enterSearchCriteria(getData("searchCriteria"));
            SearchResultPage searchResultPage = userHomePage.clickSearchIcon();
            List<WebElement> list = searchResultPage.getItemsInSearchResult(getData("title"));
            ProductDetailsPage productDetailsPage = null;
            String productTitle;
            String author;
            String editor;
            String publishDate;



            for(WebElement searchItem : list){
                if (searchItem.getAttribute("title").equals(getData("title"))){
                    searchItem.click();
                    productDetailsPage = new ProductDetailsPage(driver);
                }

            }

            // Verify price
            String price = productDetailsPage.getBookPrice().replace("$", "");
            Assertion.assertNotNull(price,"Verify price is not null");
            Assertion.assertTrue(Double.parseDouble(price) > 0, "Verify price is not 0");

            productTitle = productDetailsPage.getProductName();
            author = productDetailsPage.getAuthorName();
            editor = productDetailsPage.getBookBindingName();
            publishDate = productDetailsPage.getPublishDate().replace("–", "").trim();

            String qty = getData("quantity");
            productDetailsPage.selectQuantity(qty);
            productDetailsPage.clickAddToCartButton();

            ShoppingCartPage cartPage = productDetailsPage.gotoCart();

            // Verify item properties are same on the Cart Page.

            Assertion.assertEquals(productTitle, cartPage.getBookTitle(), "Product Title on Product Details page and" +
                    " Cart page is same.");
            Assertion.assertEquals(author, cartPage.getAuthor(), "Product Author on Product Details page and" +
                    " Cart page is same.");
            Assertion.assertEquals(editor, cartPage.getBookBinding(), "Product Title on Product Details page and" +
                    " Cart page is same.");

            //Verify that item total price is correctly calculated per qty.
            Double productPrice = Double.parseDouble(price);
            Double totalProductPrice = productPrice * (Integer.parseInt(qty));

            Assertion.assertEquals(totalProductPrice, cartPage.getTotalPriceOnCart(),"Verify Total Price on Shipping Options page");

        }catch (Exception e){
            e.printStackTrace();
            ATUReports.add("Exception: "+e.getMessage(), LogAs.FAILED,new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));

        }

    }





}
