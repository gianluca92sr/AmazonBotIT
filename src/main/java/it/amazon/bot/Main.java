package it.amazon.bot;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import it.amazon.bot.util.AzioniDOM;
import it.amazon.bot.util.MailOrdine;
import it.amazon.bot.util.ReadFile;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		
		Logger myLog = Logger.getLogger(Main.class.getName());
		
		//Prendo dati necessari dal file txt
		String filePath = args[0];
		Map<String, Object> datiAmazon = ReadFile.getDatiAmazonDaFile(filePath);

		//Carico il driver per Chrome
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		//Carico il mio profilo per evitare email / sms di sicurezza
		ChromeOptions options = new ChromeOptions();
		if (datiAmazon.get("GOOGLE") != null && datiAmazon.get("GOOGLE").toString().equals("SI")) {
			options.addArguments("user-data-dir=" + datiAmazon.get("PATH_GOOGLE").toString());
		}
		
		//Incognito per limitare fenomeni di caching
		options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		options.setCapability("chrome.switches", Arrays.asList("--incognito"));
		
		//Apro un'istanza del driver con le opzioni desiderate
		WebDriver driver = new ChromeDriver(options);
		
		//Apro link dell'oggetto che si vuole monitorare
		try {
			driver.get(datiAmazon.get("OGGETTO_URL").toString());
		}catch (Exception e) {
			myLog.info("Errore nell'apertura del browser " + e);
		}

		//Classe che contiene tutte le azioni da effettuare sul DOM
		AzioniDOM dom = new AzioniDOM();
		boolean isAmazonPrezzoOK = dom.isPrezzoAmazonOK(Integer.parseInt((String) datiAmazon.get("PREZZO_MASSIMO")), driver);
		
		//Se il prezzo è giusto e il venditore/spedizioniere è Amazon allora lo aggiunge al carrello
		if(isAmazonPrezzoOK)
			dom.aggiungiAlCarrello(driver);
		
		//Invia una mail per far avvertire l'acquirente e per concludere la fase finale d'acquisto
		MailOrdine mail = new MailOrdine();
		mail.invioMailOrdine(datiAmazon);
		
		//Skippa offerta Prime se presente
		dom.skipPrime(driver);
		
		//Arriva alla fase di pagamento dell'ordine
		dom.procediOrdine(driver, datiAmazon);
//		System.out.println("Ordine effettuato");
	}

}
