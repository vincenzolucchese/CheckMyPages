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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vince.demo.entity.BlobEntity;
import com.vince.demo.entity.WebSiteEntity;
import com.vince.demo.repository.BlobRepository;
import com.vince.demo.repository.WebSiteRepository;

@Service
public class CheckSiteService {	
	
	private static final Logger log = LoggerFactory.getLogger(CheckSiteService.class);
	private static final String PATH_SITE = "./history_page/";
	private static final String PATH_WORK = "./working/";
	
	@Autowired
	private WebSiteRepository webSiteRepository;
	@Autowired
	private BlobRepository blobRepository;	
	
	public void checkAllSiteNow(Date timeStamp) throws IOException {
		List<WebSiteEntity> sites = webSiteRepository.findByIsActive(true);
		if(!CollectionUtils.isEmpty(sites)) {
			for (WebSiteEntity each : sites) {
				if(each.getLastCheck()==null) {
					String tempDir = this.createDirectoryFirsTime(each, timeStamp);
					File tmp = writeFile(tempDir, each, timeStamp);
					if(tmp!=null) {
						saveUpdate(each, tmp, timeStamp);
					}
				}else {
					String directoryTemp = this.getPathRelative(PATH_WORK, each.getUrl(), timeStamp, each.getId());
					FileUtils.forceMkdir(new File(directoryTemp));					
					File maybeNew = writeFile(directoryTemp, each, timeStamp);
					String whereSearch = getPathRelative(PATH_SITE, each.getUrl(), null, each.getId());
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
						FileUtils.copyDirectoryToDirectory(new File(directoryTemp), new File(whereSearch));
						saveUpdate(each, maybeNew, timeStamp);
					}					
				}
			}
		}
	}
	
	private WebSiteEntity saveUpdate(WebSiteEntity each, File tmp , Date timeStamp) throws IOException {
		BlobEntity blob = new BlobEntity();
		blob.setData(FileUtils.readFileToByteArray(tmp));
		blob.setCreateDate(timeStamp);
		blob = blobRepository.save(blob);
		List<BlobEntity> list = each.getBlob();
		if(CollectionUtils.isEmpty(list)) {
			list = new ArrayList<>();
		}
		list.add(blob);
		each.setLastCheck(timeStamp);
		each.setBlob(list);
		return webSiteRepository.save(each);
	}
	
	private String createDirectoryFirsTime(WebSiteEntity site, Date timeStamp) throws IOException {
		log.debug("create first check..." + site);
		String directoryTemp = this.getPathRelative(PATH_SITE, site.getUrl(), timeStamp, site.getId());
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
	
	private void writeDifference() throws Exception {
	    BufferedReader br1 = null;
	    BufferedReader br2 = null;
	    BufferedWriter bw3 = null;
	    String sCurrentLine;
	    int linelength;

	    HashMap<String, Integer> expectedrecords = new HashMap<String, Integer>();
	    HashMap<String, Integer> actualrecords = new HashMap<String, Integer>();

	    br1 = new BufferedReader(new FileReader("expected.txt"));
	    br2 = new BufferedReader(new FileReader("actual.txt"));

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

	    // expected is left with all records not present in actual
	    // actual is left with all records not present in expected
	    bw3 = new BufferedWriter(new FileWriter(new File("c.txt")));
	    bw3.write("Records which are not present in actual\n");
	    for (String key : expectedrecords.keySet()) {
	        for (int i = 0; i < expectedrecords.get(key); i++) {
	            bw3.write(key);
	            bw3.newLine();
	        }
	    }
	    bw3.write("Records which are in actual but not present in expected\n");
	    for (String key : actualrecords.keySet()) {
	        for (int i = 0; i < actualrecords.get(key); i++) {
	            bw3.write(key);
	            bw3.newLine();
	        }
	    }
	    bw3.flush();
	    bw3.close();
	}
	
}
