package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testbases.BaseClass;

public class TC_001_LoginNinjaTest extends BaseClass  {
	
	@Test
	public void login() {

		logger.info("***********Starting TC_001_LoginNinjaTest**********");
		try {
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.clickMyaccount();
		loginPage.clickLoginLnk();
		loginPage.setEmail(p.getProperty("email"));
		loginPage.setPassword(p.getProperty("password"));
		loginPage.submitLogin();
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetPage=myacc.isMyaccountPageExist();
		Assert.assertEquals(targetPage, true);
		}
		catch(Exception e) {
			logger.error("Exception oocured, testcase faile"+e);
			Assert.fail();
		}
		logger.info("**********Finished TC_001_LoginNinjaTest");
		
	}

}
