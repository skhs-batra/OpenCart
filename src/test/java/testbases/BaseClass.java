package testbases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@Parameters({"browser"})
	@BeforeClass
	public void setUp(String browser) throws IOException {
		FileReader file=new FileReader(System.getProperty("user.dir")+"/src/test/resources/config.properties");
	     p=new Properties();
	     p.load(file);
	     
		logger=LogManager.getLogger(this.getClass());
		
		switch(browser.toLowerCase()) {
		case "chrome" : driver=new ChromeDriver();
		break;
		case "edge"   : driver=new EdgeDriver();
		break;
		case "firefox": driver=new FirefoxDriver();
		break;
		default: System.out.println("Invalid browser name");
		return;
		
		}
		
		driver.get(p.getProperty("appUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	
	public String randomString() {
		String randomString=RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}
	
	public String randomNumber() {
		String randomNumber=RandomStringUtils.randomNumeric(5);
		return randomNumber;
	}
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	public String captureScreen(String tname) throws IOException {
		String timeStamp=new SimpleDateFormat("yyMMdHHmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"/screenshots/"+tname+"_"+timeStamp;
		File targetFile=new File(targetFilePath);
		
		source.renameTo(targetFile);
		return targetFilePath;
	}
		
	
	

}
