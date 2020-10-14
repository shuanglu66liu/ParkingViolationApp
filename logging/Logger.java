package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logger {
	private PrintWriter out;

	private Logger(String fileName) {
		try {
			out = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	private static Logger instance = null;

	public static Logger getInstance(String logFile) {
		if (instance == null) {
			instance = new Logger(logFile);

		}
		return instance;
	}

	public void writTolog(String text) {
		out.write(text + "\n");
		out.flush();
	}

}

