package com.vince.demo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharSetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.vince.demo.entity.AttachForPageEntity;
import com.vince.demo.entity.BlobEntity;
import com.vince.demo.entity.WebSiteEntity;
import com.vince.demo.repository.AttachForPageRepository;
import com.vince.demo.repository.BlobRepository;
import com.vince.demo.repository.WebSiteRepository;

@Service
public class CheckSiteService {	
	
	private static final Logger log = LoggerFactory.getLogger(CheckSiteService.class);
	private static final String PATH_HISTORY_SITE = "./history_page/";
	private static final String PATH_WORKING = "./working/";
	private static final String DIV_RED = "<div style=\"border: 3px solid red; margin : 1em; padding: 1em;\">";
	private static final String DIV_GREEN = "<div style=\"border: 3px solid green; margin : 1em; padding: 1em;\">";

	@Autowired
	private WebSiteRepository webSiteRepository;
	@Autowired
	private BlobRepository blobRepository;	
	@Autowired
	private AttachForPageRepository attachmentRepository;
	
	public void checkAllSiteNow(Date timeStamp) throws Exception {
		List<WebSiteEntity> sites = webSiteRepository.findByIsActive(true);
		if(!CollectionUtils.isEmpty(sites)) {
			for (WebSiteEntity each : sites) {
				if(each.getLastCheck()==null) {
					String tempDir = this.createDirectoryFirsTime(each, timeStamp);
					File tmp = writeFile(tempDir, each, timeStamp);
					if(tmp!=null) {
						saveUpdate(each, tmp, null, timeStamp);
					}
				}else { 
					String directoryTemp = this.getPathRelative(PATH_WORKING, each.getUrl(), timeStamp, each.getId());
					FileUtils.forceMkdir(new File(directoryTemp));					
					File maybeNew = writeFile(directoryTemp, each, timeStamp);
					String whereSearch = getPathRelative(PATH_HISTORY_SITE, each.getUrl(), null, each.getId());
					Collection<File> listFiles = FileUtils.listFiles(new File(whereSearch), new String[]{"html"}, true);
					File current = null;
					for (File eachFile : listFiles) {
						if(current == null) {
							current = eachFile;
						}else {
							if(FileUtils.isFileNewer(eachFile, current)) {
								current = eachFile;
							}
						}						
					}
					
					if(!FileUtils.contentEqualsIgnoreEOL(maybeNew, current, "utf-8")) {
						File delta = writeDifference(current, maybeNew, new File(directoryTemp), timeStamp);
						saveUpdate(each, maybeNew, delta, timeStamp);
						FileUtils.copyDirectoryToDirectory(new File(directoryTemp), new File(whereSearch));
					}					
				}
			}
		}
	} 
	
	private WebSiteEntity saveUpdate(WebSiteEntity each, File tmp, File delta, Date timeStamp) throws IOException {
		BlobEntity blob = new BlobEntity();
		blob.setData(FileUtils.readFileToByteArray(tmp));
		blob.setCreateDate(timeStamp);
		blob.setDesc(each.getId()+"_"+getDateFormatted(timeStamp));
		blob = blobRepository.save(blob); 
		
		BlobEntity blob1 = null;
		if(delta!=null) {
			blob1 = new BlobEntity();
			blob1.setData(FileUtils.readFileToByteArray(delta));
			blob1.setCreateDate(timeStamp);
			blob1.setDesc(each.getId()+"_"+getDateFormatted(timeStamp)+"_DELTA");
			blob1 = blobRepository.save(blob1);
		}
		
		List<AttachForPageEntity> list = each.getAttachment();
		if(CollectionUtils.isEmpty(list)) {
			list = new ArrayList<>();
		}
		AttachForPageEntity attachs = new AttachForPageEntity();
		attachs.setCreateDate(timeStamp);
		attachs.setPageHtml(blob);
		attachs.setPageHtmlDelta(blob1);
		attachs = attachmentRepository.save(attachs);
		list.add(attachs);
		each.setLastCheck(timeStamp);
		each.setAttachment(list);
		return webSiteRepository.save(each);
	}
	
	private String createDirectoryFirsTime(WebSiteEntity site, Date timeStamp) throws IOException {
		log.debug("create first check..." + site);
		String directoryTemp = this.getPathRelative(PATH_HISTORY_SITE, site.getUrl(), timeStamp, site.getId());
		FileUtils.forceMkdir(new File(directoryTemp));		
		return directoryTemp;
	}
	
	private File writeFile(String destDir, WebSiteEntity webSite, Date timeStamp) {
		File fileReturn = null;
		String inputLine;
		try {
			URL data = new URL(webSite.getUrl());
			HttpURLConnection con = (HttpURLConnection) data.openConnection();
			
			con.setRequestProperty("User-Agent", "Mozilla/5.0 Firefox/26.0");
			con.connect();
			
			/* Read webpage coontent */
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String fileame = destDir + "/" + webSite.getId() + ".html";
			FileWriter myWriter = new FileWriter(fileame);
			// myWriter.write("Files in Java might be tricky, but it is fun enough!");

			// BufferedWriter writer = Files.newBufferedWriter(dst, StandardCharsets.UTF_8);
			/* Read line by line */
			while ((inputLine = in.readLine()) != null) {
				log.debug(inputLine);
				myWriter.write(inputLine);
				myWriter.write("\n");
			}

			myWriter.close();
			/* close BufferedReader */
			in.close();
			/* close HttpURLConnection */
			con.disconnect();
			fileReturn = new File(fileame);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileReturn;
	}
	
	private String getDateFormatted(Date timeStamp) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
		return dateFormat.format(timeStamp);
	}
	
	private String getPathRelative(String rootPath, String url, Date timeStamp, Long id) {
		String directoryTemp = url;
		int inizio = directoryTemp.indexOf(":")+3;
		int fine = directoryTemp.indexOf("/", inizio);
		directoryTemp = directoryTemp.substring(inizio, fine);	
		directoryTemp = directoryTemp.replaceAll(":", "");
		directoryTemp = rootPath + directoryTemp + "/" + id + "/";
		if(timeStamp!=null) {
			directoryTemp = directoryTemp + this.getDateFormatted(timeStamp);
		}		
		return directoryTemp;
	}
	
	
	
	private File writeDifference(File current, File newer, File whereToWrite, Date timeStamp ) throws Exception {
	    BufferedReader br1 = null;
	    BufferedReader br2 = null;
	    String sCurrentLine;
	    int linelength;

	    HashMap<String, Integer> expectedrecords = new HashMap<String, Integer>();
	    HashMap<String, Integer> actualrecords = new HashMap<String, Integer>();

	    br1 = new BufferedReader(new FileReader(current));
	    br2 = new BufferedReader(new FileReader(newer));

	    while ((sCurrentLine = br1.readLine()) != null) {
	        if (expectedrecords.containsKey(sCurrentLine)) {
	            expectedrecords.put(sCurrentLine, expectedrecords.get(sCurrentLine) + 1);
	        } else {
	            expectedrecords.put(sCurrentLine, 1);
	        }
	    }
	    while ((sCurrentLine = br2.readLine()) != null) {
	        if (expectedrecords.containsKey(sCurrentLine)) {
	            int expectedCount = expectedrecords.get(sCurrentLine) - 1;
	            if (expectedCount == 0) {
	                expectedrecords.remove(sCurrentLine);
	            } else {
	                expectedrecords.put(sCurrentLine, expectedCount);
	            }
	        } else {
	            if (actualrecords.containsKey(sCurrentLine)) {
	                actualrecords.put(sCurrentLine, actualrecords.get(sCurrentLine) + 1);
	            } else {
	                actualrecords.put(sCurrentLine, 1);
	            }
	        }
	    }
	    
	    String refer = "<body";
	    String calculated=DIV_RED;
	    List<String> listTxt = new ArrayList<String>();
	    List<String> lines = FileUtils.readLines(newer, Charset.defaultCharset());
	    for (String each : lines) {
	    	if(!StringUtils.isEmpty(each) && each.contains(refer)) {
	    		calculated=calculated.concat("<p><b>Records which are not present in actual</b></p>");
	    		for (String key : expectedrecords.keySet()) {
	    	        for (int i = 0; i < expectedrecords.get(key); i++) {
	    	        	calculated=calculated.concat("<p>"+key+"</p>");
	    	        }
	    	    }
	    		calculated=calculated.concat("<p><b>Records which are in actual but not present in expected</b></p>");
	    	    for (String key : actualrecords.keySet()) {
	    	        for (int i = 0; i < actualrecords.get(key); i++) {
	    	        	calculated=calculated.concat("<p>"+key+"</p>");
	    	        }
	    	    }
	    	    calculated=calculated.concat("</div>");
	    		int start = each.indexOf(refer, 0);
	    		int end = each.indexOf(">", start+refer.length());	    	
	    	    each=each.substring(0, end+1)+calculated+each.substring(end+1, each.length());
	    	}	
	    	listTxt.add(each);
		}
	    File delta = new File(whereToWrite.getAbsoluteFile()+"/delta.htm");
	    FileUtils.writeLines(delta, listTxt);

	    // expected is left with all records not present in actual
	    // actual is left with all records not present in expected
	    listTxt = new ArrayList<String>();
	    listTxt.add("Records which are not present in actual");
	    for (String key : expectedrecords.keySet()) {
	        for (int i = 0; i < expectedrecords.get(key); i++) {
	        	listTxt.add(key);
	        }
	    }
	    listTxt.add("Records which are in actual but not present in expected\n");
	    for (String key : actualrecords.keySet()) {
	        for (int i = 0; i < actualrecords.get(key); i++) {
	        	listTxt.add(key);
	        }
	    }
	    FileUtils.writeLines(new File(whereToWrite.getAbsoluteFile()+"/delta.txt"), listTxt);
	    
	    br1.close();
	    br2.close();
	    return delta;
	}
	
}
