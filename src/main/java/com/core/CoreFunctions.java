package com.core;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.browseHandle.BrowserHandle;
import com.logs.Logs;
import com.utils.ReadFromProperty;

public class CoreFunctions {
	
	public static String readConfig(String str) throws IOException 
	{
		ReadFromProperty readFromProperty;
		Properties prop;
		readFromProperty = new ReadFromProperty();
		prop = readFromProperty.property();
		String output = prop.getProperty(str);
		return output;
	}
	


	
	public static void click(WebElement element, String elementName) {
		Logs.logger.info("Clicking on " + elementName);
		try {
			element.click();
			Logs.logger.info("Pass:SuccessfuLogsy clicked on " + elementName);
		} catch (Exception e) {
			Logs.logger.info("Fail:Could not click on " + elementName);
			e.printStackTrace();
		}
	}

	public static void setText(WebElement element, String Text) {
//		click(element, Text);
		Logs.logger.info("Entering" + Text);
		try {
			element.sendKeys(Text);
			Logs.logger.info("Pass:" + Text + " is entered");
		} catch (Exception e) {
			Logs.logger.info("Fail:Unable to set text: " + Text);
			e.printStackTrace();
		}
	}

	public static void clearText(WebElement element) {
		try {element.clear();
		Logs.logger.info("Pass: is clear");
		} 
		catch (Exception e) 
		{
			Logs.logger.info("Fail:Unable to clear text: " );
			e.printStackTrace();
		}
	}
	public static String getElementText(WebElement element) {
		Logs.logger.info("Getting Element Text");
		String text = "";
		try {
			text = element.getText().trim();
			Logs.logger.info("Pass:Text is:" + text);
		} catch (Exception e) {
			Logs.logger.info("Could not get text");
			e.printStackTrace();
		}
		return text;
	}
	public static String getElementAttribute(WebElement element,String attrs) {
		Logs.logger.info("Getting Element Attribute");
		String text = "";
		try {
			text = element.getAttribute(attrs);
			Logs.logger.info("Pass: Attribute value is :" + text);
		} catch (Exception e) {
			Logs.logger.info("Could not found any such attribute");
			e.printStackTrace();
		}
		return text;
	}


	public static boolean waitUntilElementDisplayed(WebElement locator, int timeoutSec) {
        boolean elementVisible = locator.isDisplayed();
        int timer = 0;
        while (!elementVisible && timer < timeoutSec) {
            try {
            	Logs.logger.info("Wait for the element to be displayed");
                Thread.sleep(1000);
                elementVisible = locator.isDisplayed();
                timer++;

            } catch (Exception e) {
            	Logs.logger.info("Wait for the element to be displayed");
                System.out.println(locator + "was not visible.");
            }
        }
        return elementVisible;
    }
	public static String waitUntilAttrAvailable(WebElement locator, int timeoutSec,String attr) {
        boolean elementVisible = locator.isDisplayed();
        String value=locator.getAttribute(attr);
        int timer = 0;
        while (timer < timeoutSec && value.isEmpty()) {
            try {
            	Logs.logger.info("Wait for the element to be displayed");
                Thread.sleep(1000);
                elementVisible = locator.isDisplayed();
               value= locator.getAttribute(attr);
                timer++;

            } catch (Exception e) {
            	Logs.logger.info("Wait for the element to be displayed");
                System.out.println(locator + "was not visible.");
            }
        }
        return value;
    }
	


	
}
