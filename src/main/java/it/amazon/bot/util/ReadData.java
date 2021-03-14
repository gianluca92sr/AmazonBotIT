package it.amazon.bot.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ReadData {

	static Logger myLog = Logger.getLogger(ReadData.class.getName());
	
	public Map<String, Object> convertiDati (String [] dati) throws Exception{
		
		Map<String, Object> datiFormattati = new HashMap<String, Object>();
		try {
			datiFormattati.put("EMAIL-AMAZON", dati[0]);
			datiFormattati.put("PASS-AMAZON", dati[1]);
			datiFormattati.put("OGGETTO_URL", dati[2]);
			datiFormattati.put("PREZZO_MASSIMO", dati[3]);
		}catch (Exception e) {
			e.printStackTrace();
			myLog.info("Errore nella lettura del file" + e.toString());
			throw e;
		}

		
		return datiFormattati;
	}
	
	
}
