package fr.insee.rmes.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtils {
	
	private static Logger logger = LogManager.getLogger(FileUtils.class);

	public static List<List<String>> readFile(String pathFile, String csvSplitBy)  {
		List<String> allLines = null ;
		List<List<String>> fileContents = new ArrayList<List<String>>();
		
        try {
			allLines = Files.readAllLines(Paths.get(pathFile));
		} catch (IOException e) {
				logger.error(e.getMessage());
				return fileContents;
		}
		

		for (String line : allLines) {
			List<String> lineContents = new ArrayList<String>();
			String[] parts = line.split(csvSplitBy);
			for (int i = 0; i < parts.length; i++) {
				lineContents.add(parts[i]);
			}
			fileContents.add(lineContents);
		}
		return fileContents;
	}
}
