package com.amazon.test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import com.amazon.pages.*;
import com.common.utils.Assertion;
import com.common.utils.ParentTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class TC_001_AmazonProdCheckoutTest extends ParentTest {

	@Test
	public void amazonTest() {
		try {
			HomePage homePage = new HomePage(driver);
			SignInPage signInPage = homePage.clickOnSignInOption();
			signInPage.enterEmailId(getData("emailId"));
			signInPage.clickContinueButton();
			signInPage.enterPassword(getData("password"));
			UserHomePage userHomePage = signInPage.clickSignInButton();

			// Verify user is logged in
			String actUsername = userHomePage.getLoggedInUsername();
			String expUsername = getData("userName");
			Assertion.assertTrue(actUsername.contains(expUsername), "Verify User got login");
			
			// Clear cart if already has items
			if (! userHomePage.isCartEmpty()) {
				userHomePage.clearCart();	
			}

			// Search product
			userHomePage.enterSearchCriteria(getData("searchCriteria"));
			SearchResultPage searchResultPage = userHomePage.clickSearchIcon();
			List<WebElement> itemsList = searchResultPage.getItemsInSearchResult(getData("keyword"));
			ProductDetailsPage productDetailsPage = null;

			// Get available product from product search results
			for (WebElement webElement : itemsList) {
				searchResultPage.navigateTo(webElement.getAttribute("href"));
				try {
					productDetailsPage = new ProductDetailsPage(driver);
					productDetailsPage.selectCapacity(getData("capacity"));
					if (productDetailsPage.isProductAvailable()) {
						System.out.println("Product is available");
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			String productNamePDP = productDetailsPage.getProductName();

			// Verify price
			String price = productDetailsPage.getPrice().replace("$", "");
			Assertion.assertNotNull(price,"Verify price is not null");
			Assertion.assertTrue(Double.parseDouble(price) > 0, "Verify price is not 0");

			String qty = getData("quantity");
			productDetailsPage.selectQuantity(qty);
			productDetailsPage.clickAddToCartButton();
			CartDetailsPage cartDetailsPage = productDetailsPage.closeDataRecoveryPlanPopUp();
			Assertion.assertTrue(cartDetailsPage.isProductAddedToCart(), "Verify Product added to cart");

			// Verify no of items added in cart are correct
			String qtyOnCart = cartDetailsPage.getNoOfItemsAddedToCart();
			Assertion.assertEquals(qty, qtyOnCart,"Verify qty on Cart");

			// Verify total price is correct on Cart according to qty selected
			Double expTotalPrice = Integer.parseInt(qty) * Double.parseDouble(price);
			Double actTotalPrice = cartDetailsPage.getTotalPriceOnCart();
			Assertion.assertEquals(expTotalPrice.toString(), actTotalPrice.toString(),"Verify total price on Cart");

			ShippingAddressPage shippingAddressPage = cartDetailsPage.clickProceedToCheckout();
			ShippingOptionsPage shippingOptionsPage = shippingAddressPage.selectAddress(getData("addressName"));
			shippingOptionsPage.selectShippingOption(getData("shippingOption"));

			// Verify same Product name,price & qty are displayed on shipping options page.
			Assertion.assertEquals(productNamePDP, shippingOptionsPage.getProductName(),"Verify Product name on Shipping Options page");
			Assertion.assertEquals(price, shippingOptionsPage.getProductPrice(),"Verify Total Price on Shipping Options page");
			Assertion.assertEquals(qty, shippingOptionsPage.getQuantity(),"Verify Quantity on Shipping Options page");
			
			PaymentMethodPage paymentMethodPage = shippingOptionsPage.clickContinueButton();
			paymentMethodPage.clickContinueButton();
			paymentMethodPage.navigateTo(getData("baseURL"));
			signInPage = userHomePage.logOut();
			Assertion.assertTrue(signInPage.isUserLoggedOut(), "Verify User got logged out");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ATUReports.add("Exception: "+e.getMessage(),LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
		}
	}
}