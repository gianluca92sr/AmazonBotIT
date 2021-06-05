package it.amazon.bot.util;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DomActions {
	
	Logger myLog = Logger.getLogger(DomActions.class.getName());
	boolean priceFound = false;
	
	public boolean isAmazonPriceOK(Integer maxPrice, WebDriver driver) throws InterruptedException {
		
		String productPrice = null;

		while(true) {
			
			try {
				productPrice = driver.findElement(By.id("priceblock_ourprice")).getText();
				priceFound = true;
				break;
			} catch (Exception e) {
				myLog.info("Price 0 not found");
			}
			
			try {
				productPrice = driver.findElement(By.id("newBuyBoxPrice")).getText();
				priceFound = true;
				break;
			} catch (Exception e) {
				myLog.info("Price 1 not found");
			}
			
			try {
				productPrice = driver.findElement(By.id("price_inside_buybox")).getText();
				priceFound = true;
				break;
			} catch (Exception e) {
				myLog.info("Price 2 not found");
			}
			
			try {
				productPrice = driver.findElement(By.id("mbc-price-1")).getText();
				priceFound = true;
				break;
			} catch (Exception e) {
				myLog.info("No Price found");
			}

			hold(4);
			
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		}
		
		String seller = "";
				
				try {
					seller =	driver.findElement(By.id("merchant-info")).getText();
				}catch (Exception e) {
					try {
						seller =	driver.findElement(By.xpath("//span[@class='a-size-small mbcMerchantName']")).getText();
					} catch (Exception e2) {
						
					}
				}
				
		hold(4);
		
		while(!seller.contains("Amazon")) {
			myLog.info("Seller not Amazon");
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
			isAmazonPriceOK(maxPrice, driver);
		}
		
		while (Integer.parseInt(productPrice.split(",")[0]) > maxPrice) {
			myLog.info("Price too high");
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
			isAmazonPriceOK(maxPrice, driver);
		}

		
		return true;
		
	}
	
	public void addToBasket(WebDriver driver) throws InterruptedException {

		try {
			hold(1);
			driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();
			hold(1);
		}catch (Exception e) {
			
		}
		
		try {
			hold(1);
			driver.findElement(By.id("attach-view-cart-button-form")).click();
		}catch (Exception e) {

		} 
		
		try {
			hold(1);
			driver.findElement(By.xpath("//input[@aria-labelledby='attachSiNoCoverage-announce']")).click();
		} catch (Exception e) {

		}
		
		try {
			hold(1);
			driver.findElement(By.id("attachSiNoCoverage-announce")).click();
		} catch (Exception e) {

		}

		try {
			hold(1);
		driver.findElement(By.xpath("//input[@aria-labelledby='mbc-buybutton-addtocart-1-announce']")).click();
		}catch (Exception e) {
			
		}
		
		try {
			hold(1);
			driver.findElement(By.xpath("//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']")).click();
		}catch (Exception e) {
			
		}
		
		try {
			hold(2);
			driver.findElement(By.id("siNoCoverage-announce")).click();
		}catch (Exception e) {

		}
		
		try {
			hold(1);
			driver.findElement(By.id("hlb-view-cart-announce")).click();
		} catch (Exception e) {

		}
		

	}
	
	public void makeOrder(WebDriver driver, Map<String, Object> datiAmazon) throws InterruptedException {
		

		hold(1);
		driver.findElement(By.name("proceedToRetailCheckout")).click();
		
		try {
			hold(2);
			try {
				driver.findElement(By.id("ap_email")).sendKeys(datiAmazon.get("EMAIL-AMAZON").toString());
				driver.findElement(By.id("continue")).click();
			}catch (Exception e) {
				myLog.info("No Email requested");
			}
			
			try {
				hold(2);
				driver.findElement(By.id("ap_password")).sendKeys(datiAmazon.get("PASS-AMAZON").toString());
				driver.findElement(By.id("signInSubmit")).click();
			} catch (Exception e) {
				myLog.info("No Password requested");
			}
			
			//select gratis option if you don't have prime
			try {
				driver.findElement(By.xpath("//input[@value='std-it']")).click();
			}catch (Exception e) {
				
			}
			
			// confirm prime
			try {
				driver.findElement(By.xpath("//input[@value='next-it']")).click();
			} catch (Exception e) {
				
			}
			
			try {
				driver.findElement(By.xpath("//input[@name='placeYourOrder1']")).click();
			} catch (Exception e) {
				
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void skipPrime(WebDriver driver) {
		
		try {
			driver.findElement(By.id("prime-no-thanks")).click();
		} catch (Exception e) {
			myLog.info("No Prime page");
		}
		
		try {
			driver.findElement(By.id("prime-declineCTA")).click();
		} catch (Exception e) {
			myLog.info("No CTA");
		}
		
	}
	
	private void hold(Integer seconds) throws InterruptedException {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
