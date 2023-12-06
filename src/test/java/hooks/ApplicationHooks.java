package hooks;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.browseHandle.BrowserHandle;
import com.utils.ReadFromProperty;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private BrowserHandle browserHandle;
	private WebDriver driver;
	private ReadFromProperty readFromProperty;
	Properties prop;
//
//	@BeforeSuite
//	public void logSetup() {
//		Logs.logSetup();
//	}
//	
	@Before(order = 0)
	public void getDriver() throws IOException
	{
		readFromProperty = new ReadFromProperty();
		prop = readFromProperty.property();
	}
	
	@Before(order = 1)
	public void launchBrowser() throws IOException
	{
		String browserName = prop.getProperty("browser");
		browserHandle = new BrowserHandle();
//		public utilis() {
			// TODO Auto-generated constructor stub
//		Logs.logSetup();
		driver = browserHandle.settingBrowser(browserName);	
	}
	
	@After(order = 0)
	public void quitBrowser() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
		
	}
}
