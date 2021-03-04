package it.amazon.bot.util;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AzioniDOM {
	
	Logger myLog = Logger.getLogger(AzioniDOM.class.getName());
	boolean prezzoPresente = false;
	
	public boolean isPrezzoAmazonOK(Integer prezzoMassimo, WebDriver driver) throws InterruptedException {
		
		String prezzoProdotto = null;

		while(true) {
			
			try {
				prezzoProdotto = driver.findElement(By.id("mbc-price-1")).getText();
				prezzoPresente = true;
				break;
			} catch (Exception e) {
				myLog.info("Prezzo 0 non trovato");
			}
			
			try {
				prezzoProdotto = driver.findElement(By.id("newBuyBoxPrice")).getText();
				prezzoPresente = true;
				break;
			} catch (Exception e) {
				myLog.info("Prezzo 1 non trovato");
			}
			
			try {
				prezzoProdotto = driver.findElement(By.id("price_inside_buybox")).getText();
				prezzoPresente = true;
				break;
			} catch (Exception e) {
				myLog.info("Prezzo 2 non trovato");
			}
			
			try {
				prezzoProdotto = driver.findElement(By.id("priceblock_ourprice")).getText();
				prezzoPresente = true;
				break;
			} catch (Exception e) {
				myLog.info("Nessun prezzo trovato");
			}

			hold(4);
			
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		}
		
		String venditore = "";
				
				try {
					venditore =	driver.findElement(By.id("merchant-info")).getText();
				}catch (Exception e) {
					try {
						venditore =	driver.findElement(By.xpath("//span[@class='a-size-small mbcMerchantName']")).getText();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
		hold(4);
		
		while(!venditore.contains("Amazon")) {
			myLog.info("Venditore non Amazon");
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
			isPrezzoAmazonOK(prezzoMassimo, driver);
		}
		
		while (Integer.parseInt(prezzoProdotto.split(",")[0]) > prezzoMassimo) {
			myLog.info("Prezzo troppo alto");
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
			isPrezzoAmazonOK(prezzoMassimo, driver);
		}

		
		return true;
		
	}
	
	public void aggiungiAlCarrello(WebDriver driver) throws InterruptedException {

		try {
			driver.findElement(By.id("add-to-cart-button")).click();
		}catch (Exception e) {
			
		}
		
		try {
			driver.findElement(By.id("attach-view-cart-button-form")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			driver.findElement(By.xpath("//input[@aria-labelledby='mbc-buybutton-addtocart-1-announce']")).getText();
		}catch (Exception e) {
			
		}
		
		try {
			hold(1);
			driver.findElement(By.id("siNoCoverage-announce")).click();
			hold(1);
			driver.findElement(By.id("hlb-view-cart-announce")).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	public void procediOrdine(WebDriver driver, Map<String, Object> datiAmazon) throws InterruptedException {
		
		driver.findElement(By.name("proceedToRetailCheckout")).click();
		//gestisce il caso di utente non connesso
		try {
			hold(1);
			try {
				driver.findElement(By.id("ap_email")).sendKeys(datiAmazon.get("EMAIL-AMAZON").toString());
				driver.findElement(By.id("continue")).click();
			}catch (Exception e) {
				myLog.info("No Email richiesta");
			}
			
			try {
				hold(1);
				driver.findElement(By.id("ap_password")).sendKeys(datiAmazon.get("PASS-AMAZON").toString());
				driver.findElement(By.id("signInSubmit")).click();
			} catch (Exception e) {
				myLog.info("No Password richiesta");
			}
			
//			try {
//				hold(1);
//				driver.findElement(By.xpath("auth-mfa-otpcode"));
//			} catch (Exception e) {
//				myLog.info("OTP richiesto");
//				MailOrdine mail = new MailOrdine();
//				mail.invioMailOTP(datiAmazon);
//				//non utilizza gli ultimi due try in questo caso
//			}
			
			//se non hai prime seleziona il primo valore 
			try {
				driver.findElement(By.xpath("//input[@value='std-it']")).click();
			}catch (Exception e) {
				
			}
			
			// conferma il prime
			try {
				driver.findElement(By.xpath("//input[@value='next-it']")).click();
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
			myLog.info("No pagina Prime");
		}
		
	}
	
	private void hold(Integer secondi) throws InterruptedException {
		try {
			TimeUnit.SECONDS.sleep(secondi);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
