package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myAccLnk;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement loginLnk;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btn_login;
	
	@FindBy(id="input-email")
	WebElement txt_email;
	
	@FindBy(id="input-password")
	WebElement txt_password;
	
	public void clickMyaccount() {
		myAccLnk.click();
		
	}
	public void clickLoginLnk() {
		loginLnk.click();
		
	}
	public void setEmail(String email) {
		txt_email.sendKeys(email);
		
	}
	public void setPassword(String password) {
		txt_password.sendKeys(password);
		
	}
	public void submitLogin() {
		btn_login.click();
		
		
	}

}
