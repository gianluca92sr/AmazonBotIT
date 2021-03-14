package it.amazon.bot.validator;

import java.util.Map.Entry;

public class MyInputVerifier {

	public boolean verify(Entry<String, Object> datoAmazon) {
		if (datoAmazon.getKey().equals("EMAIL-AMAZON") && !datoAmazon.getValue().toString().trim().equals(
				"\\\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,4}\\\\b")) {
			
				return true;
				
		} else if (datoAmazon.getKey().equals("OGGETTO_URL") && !datoAmazon.getValue().equals("https?:\\/\\/(www\\.)"
				+ "?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)\r\n")) {
			
				return true;

		} 

		return false;
	}

}
