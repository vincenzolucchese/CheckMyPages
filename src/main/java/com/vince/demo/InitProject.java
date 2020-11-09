package com.vince.demo;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class InitProject {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		
		FileUtils.forceMkdir(new File("./webSites-1"));
		Collection<File> list = FileUtils.listFiles(new File("./webSites"), new String[] {"json"} , true);
		for (File file : list) {
			FileUtils.moveFileToDirectory(file, new File("./webSites-1"), false);

		}		
		FileUtils.deleteDirectory(new File("./webSites"));
		FileUtils.moveDirectory(new File("./webSites-1"), new File("./webSites"));
		FileUtils.deleteDirectory(new File("./webSites-1"));
		
		FileUtils.deleteDirectory(new File("./working"));
		FileUtils.forceMkdir(new File("./working"));
		
		FileUtils.deleteDirectory(new File("./history_page"));
		FileUtils.forceMkdir(new File("./history_page"));
		
		FileUtils.deleteDirectory(new File("./data"));
		FileUtils.forceMkdir(new File("./data"));
	

	}

}
