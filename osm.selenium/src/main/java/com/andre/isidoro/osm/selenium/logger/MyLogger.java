package com.andre.isidoro.osm.selenium.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class MyLogger implements IMyLogger{
	
	private static final String FILE_LOGGER_NAME = "twg.log";
	private SimpleDateFormat sdf; 
	private File fout;
	private String className;
	
	public MyLogger(Class<?> c) {
		fout =  new File(FILE_LOGGER_NAME);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		className  = c.getName();
	}
	
	
	public void error(String text) {
		write("ERROR", text);
	}
	
	public void error(String text, Exception e) {
		write("ERROR", text + "\n" + ExceptionUtils.getStackTrace(e));
	}
	
	public void info(String text) {
		write("INFO", text);
	}
	
	public void warn(String text) {
		write("WARN", text);
	}
	
	private void write(String type, String text) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fout, true))){
			Date now = new Date();
		    String strDate = sdf.format(now);
			bw.write(strDate + "\t" + type + "\t" + className + "\t" + text);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
