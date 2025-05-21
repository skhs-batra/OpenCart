package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	// Locators
    @FindBy(xpath = "//input[@id='username']")
    WebElement txtUserName;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='terms']")
    WebElement chkTerms;

    @FindBy(xpath = "//input[@id='signInBtn']")
    WebElement btnSignIn;

    // Actions
    public void setUserName(String username) {
        txtUserName.sendKeys(username);
    }

    public void setPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void acceptTermsAndConditions() {
        chkTerms.click();
    }

    public void clickLogin() {
        btnSignIn.click();
    }
}

	


