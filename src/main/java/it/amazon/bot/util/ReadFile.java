package it.amazon.bot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class ReadFile {

	static Logger myLog = Logger.getLogger(ReadFile.class.getName());
	
	public static Map<String, Object> getDatiAmazonDaFile(String filePath) {
		
		Map<String, Object> datiAmazon = new HashMap<String, Object>();
		
	    try {
			File myObj = new File(filePath);
			String isfileEsistente = Boolean.toString(myObj.exists());
			myLog.info(isfileEsistente);
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          String chiave = data.split("=",2)[0];  // n=2 indica il limit, questo split sarà effettuato una sola volta n-1
	          String valore = data.split("=",2)[1];
	          datiAmazon.put(chiave, valore);
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        myLog.info("Errore nella lettura del file" + e.toString());
	        e.printStackTrace();
	      }
		
	    return datiAmazon;
	}

}
