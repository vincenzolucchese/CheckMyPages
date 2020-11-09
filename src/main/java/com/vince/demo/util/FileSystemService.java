package com.vince.demo.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vince.demo.entity.WebSite;
import com.vince.demo.repository.WebSiteRepository;

@Service
public class FileSystemService {

	private static final Logger log = LoggerFactory.getLogger(FileSystemService.class);
	private static final String PATH_SITE = "./webSites/";
	private static final String FILE_SITE = "site.json";

	@Autowired
	private WebSiteRepository webSiteRepository;

	public void fillDbByJson(Date timeStamp) {
		List<WebSite> arraySite = new ArrayList<>();
		JSONParser parser = new JSONParser();
		try {
			File site = new File(PATH_SITE+FILE_SITE);
			if (site.exists()) {
				Reader reader = new FileReader(PATH_SITE+FILE_SITE);
				JSONObject jsonObject = (JSONObject) parser.parse(reader);
				log.debug(jsonObject.toString());
				// loop array
				JSONArray msg = (JSONArray) jsonObject.get("data");
				Iterator<JSONObject> iterator = msg.iterator();
				while (iterator.hasNext()) {
					JSONObject each = iterator.next();
					String name = (String) each.get("name");
					String url = (String) each.get("uri");
					if (webSiteRepository.findByUrl(url) == null) {
						WebSite siteEntity = new WebSite(name, url, true);
						arraySite.add(siteEntity);
					}
				}
				List<WebSite> newSite = (List<WebSite>) webSiteRepository.saveAll(arraySite);
				log.debug("SIZE: " + newSite.size());
				
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
				String destDir = PATH_SITE + dateFormat.format(timeStamp) +"/";
				FileUtils.moveFileToDirectory(site, new File(destDir), true);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	

}
