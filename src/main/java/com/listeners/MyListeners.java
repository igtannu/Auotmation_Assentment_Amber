package com.listeners;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;

import com.utils.Constants;


public class MyListeners implements ITestListener {
	@Override
	public void onFinish(ITestContext context) {
		
			String pathOfExtentReport = Constants.EXTENT_REPORT_DIRECTORY;

		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

		
	}
	

