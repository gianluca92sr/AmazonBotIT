package it.amazon.bot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class ReadFile {

	static Logger myLog = Logger.getLogger(ReadFile.class.getName());
	
	private ReadFile() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Map<String, Object> getDataFromFile(String filePath) {
		
		Map<String, Object> amazonData = new HashMap<String, Object>();
		
	    try {
			File myObj = new File(filePath);
			String isFileExists= Boolean.toString(myObj.exists());
			myLog.info(isFileExists);
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          String key = data.split("=",2)[0];  // n=2 is the limit
	          String value = data.split("=",2)[1];
	          amazonData.put(key, value);
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        myLog.info("Error during file reading" + e.toString());
	        e.printStackTrace();
	      }
		
	    return amazonData;
	}

}
