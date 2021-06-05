package it.amazon.bot;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import it.amazon.bot.util.DomActions;
import it.amazon.bot.util.ReadFile;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		
		Logger myLog = Logger.getLogger(Main.class.getName());
		
		//Take data from TXT file
		String filePath = args[0];
		Map<String, Object> amazonData = ReadFile.getDataFromFile(filePath);

		//Driver Chrome
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		//Optional Google account for Chrome
		ChromeOptions options = new ChromeOptions();
		if (amazonData.get("GOOGLE") != null && amazonData.get("GOOGLE").toString().equals("SI")) {
			options.addArguments("user-data-dir=" + amazonData.get("PATH_GOOGLE").toString());
		}
		
		options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		options.addArguments("--start-maximized");
		options.setCapability("chrome.switches", Arrays.asList("--incognito"));
		
		//Driver options
		WebDriver driver = new ChromeDriver(options);
		
		//Open link to track
		try {
			driver.get(amazonData.get("URL").toString());
		}catch (Exception e) {
			myLog.info("Error during Chrome opening " + e);
		}

		//Class with DOM methods
		DomActions dom = new DomActions();
		boolean isAmazonPriceOK = dom.isAmazonPriceOK(Integer.parseInt((String) amazonData.get("MAX_PRICE")), driver);
		
		if(isAmazonPriceOK)
			dom.addToBasket(driver);
		
		//Skip prime offer
		dom.skipPrime(driver);
		
		//Proced to order
		dom.makeOrder(driver, amazonData);
		myLog.info("Order Confirmed");
	}

}
