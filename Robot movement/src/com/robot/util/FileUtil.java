package com.robot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {
	public static String[] readFileContent(String filePath, String expectedExt) {
		if (expectedExt != null && !expectedExt.isEmpty() && expectedExt.trim().length() > 0) {
			String fileExt = getFileExtsion(filePath);
			if (!fileExt.equalsIgnoreCase(expectedExt)) {
				return null;
			}
		}

		File file = new File(filePath);
		if (!file.exists())
			return null;
		// else start reading the file
		List<String> contentList = new ArrayList<String>();
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String content = reader.nextLine();
				if (content == null || content.isEmpty() || content.trim().length() == 0)
					continue;
				contentList.add(content);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			contentList = null;
		}

		if (contentList == null || contentList.size() == 0)
			return null;

		String[] finalContent = new String[contentList.size()];
		finalContent = contentList.toArray(finalContent);
		return finalContent;
	}

	private static String getFileExtsion(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}
}
