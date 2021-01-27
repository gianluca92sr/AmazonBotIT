package it.amazon.bot.util;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.mail.HtmlEmail;

public class MailOrdine {
	
	Logger myLog = Logger.getLogger(MailOrdine.class.getName());
	
	private String to;
	private String from;
	private String host;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void invioMailOrdine(Map<String, Object> datiAmazon) {
		
		//Occorre usare una mail Gmail personale per inviare la mail
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setFrom(datiAmazon.get("EMAIL-GMAIL").toString(), "BOT-Amazon-IT");
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setStartTLSEnabled(false);
			email.setSubject("Email alert per acquisto BOT Amazon");
			email.setHtmlMsg("<p>Congratulazioni,<br><br>Il prodotto che stavi monitorando è nel carrello, accedi alla sessione del BOT per concludere l'acquisto.</p><br><br><p>BOT-Amazon-IT");			
			email.addTo(datiAmazon.get("EMAIL-AMAZON").toString());
			email.setAuthentication(datiAmazon.get("EMAIL-GMAIL").toString(), datiAmazon.get("PASS-GMAIL").toString());
			email.buildMimeMessage();
			email.sendMimeMessage();
			myLog.info("Email inviata");
		}catch (Exception e) {
			myLog.info(e.toString());
		}
		
	}
	
public void invioMailOTP(Map<String, Object> datiAmazon) {
		
		//Occorre usare una mail Gmail personale per inviare la mail
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setFrom(datiAmazon.get("EMAIL-GMAIL").toString(), "BOT-Amazon-IT");
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setStartTLSEnabled(false);
			email.setSubject("Email alert OTP per acquisto BOT Amazon");
			email.setHtmlMsg("<p>Ciao,<br><br>E' necessario inserire il codice OTP per concludere l'acquisto.</p><br><br><p>BOT-Amazon-IT");			
			email.addTo(datiAmazon.get("EMAIL-AMAZON").toString());
			email.setAuthentication(datiAmazon.get("EMAIL-GMAIL").toString(), datiAmazon.get("PASS-GMAIL").toString());
			email.buildMimeMessage();
			email.sendMimeMessage();
			myLog.info("Email inviata");
		}catch (Exception e) {
			myLog.info(e.toString());
		}
		
	}
    

	
}
