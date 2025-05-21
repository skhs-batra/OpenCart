package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//h2[normalize-space()='My Account']") //myaccount page header
	WebElement myaccHeader;
	
	public boolean isMyaccountPageExist() {
		try {
		return myaccHeader.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
		
	}

}
