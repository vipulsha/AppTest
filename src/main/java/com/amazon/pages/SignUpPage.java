package com.amazon.pages;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends ParentPage {

    Element customerName = new Element(LocatorType.ID,
            "ap_customer_name", "Customer Name field");
    Element customerEmail = new Element(LocatorType.ID, "ap_email",
            "Customer Email Address To Signup");
    Element password = new Element(LocatorType.ID, "ap_password", "Password Field");
    Element reEnterPassword = new Element(LocatorType.ID, "ap_password_check", "Re-enter Password field");
    Element createAccountButton = new Element(LocatorType.ID, "continue", "Create Account Button");


    public SignUpPage(WebDriver driver) {
        super(driver);

    }

    public UserHomePage signUp(String name, String email, String pass, String rePass ){
        enterText(customerName, name);
        enterText(customerEmail, email);
        enterText(password, pass);
        enterText(reEnterPassword, rePass);
        click(createAccountButton);

        return new UserHomePage(driver);
    }


}
