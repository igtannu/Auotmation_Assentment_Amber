package com.logs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.utils.Constants;

public class Logs {

	public static Logger logger;

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-yy");
		System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	 public Logs() {
		 Logs.logger = logSetup();
	}
	 
	public  static Logger logSetup() {
	logger = Logger.getLogger("Logger_file");
	PropertyConfigurator.configure(Constants.LOG_PROPERTIES_PATH);
	return logger;
	}

}
